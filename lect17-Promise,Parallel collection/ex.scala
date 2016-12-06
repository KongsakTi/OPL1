import scala.io.Source
import scala.concurrent.{Future,Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object medianCSV extends App {
    def findMed(lines: Seq[String]) = {
        val sNum = lines.map( l => l.split(",").map(n=>n.toInt).sorted)

        val med = sNum.map(a=>a(a.length/2))

        med

        // countPerLine.sum




    }
    // countWords(Source.fromFile("big.txt").getLines()) => slow

    val groupSize = 1<<12 // the group has size 2^12
    
    val blockedCountFuture = Source.fromFile("test.csv")
          .getLines()
          .grouped(groupSize)
          .map(lineGroup => Future{medianCSV(lineGroup)})

    val totalF = Future.sequence(blockedCountFuture).map{x => x.flatten}

    val total = Await.result(totalF,Duration.Inf)


    println(s"total word = $total")

    // totalF.onSuccess(case c => println(s"total word = $c")) => it goes out of the app as we don't wait for anything



}