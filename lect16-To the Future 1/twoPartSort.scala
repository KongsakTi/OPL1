object TwoPartSort extends App {
  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  def sort(xs: Array[Int]) = {
    import scala.concurrent.Await
    import scala.concurrent.duration._
    def merge(xs: Array[Int], ys: Array[Int]): Array[Int] = {
      val sorted = scala.collection.mutable.ArrayBuffer.empty[Int]
      def slide(xi: Int, yi: Int): Unit = {
        (xi < xs.length, yi < ys.length) match {
          case (true, _) => sorted ++= ys.slice(yi, ys.length)
          case (_, true) => sorted ++= xs.slice(xi, xs.length)
          case _ => if (xs(xi) < ys(yi)) { sorted += xs(xi); slide(xi+1, yi) }
                    else { sorted += ys(yi); slide(xi, yi+1) }
        }
      }
      slide(0, 0)
      sorted.toArray
    }

    val (left, right) = xs.splitAt(xs.length/2)
    val sortedLeft = Future { left.sorted }
    val sortedRight = Future { right.sorted }
    val combined = for { la <- sortedLeft
                         ra <- sortedRight }
                    yield merge(la, ra)

    Await.result(combined, Duration.Inf)
  }

  def randomIntArray(n: Int) = {
    import scala.util.Random

    Array.tabulate(n) { case _ => Random.nextInt(10000) }
  }

  import System.nanoTime
  def profile[R](code: => R, t: Long = nanoTime) = (code, (nanoTime - t)/1e6)

  val lgArray = randomIntArray(20000000)

  val (_, stdSortTime) = profile { lgArray.sorted }
  val (_, parSort) = profile { sort(lgArray) }

  println(s"stdSort: $stdSortTime")
  println(s"parSort: $parSort")
}