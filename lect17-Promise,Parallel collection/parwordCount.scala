import scala.io.Source
import scala.concurrent.{Future,Await}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object parwordCount extends App {
    def countWords(lines: Seq[String]) = {
        val countPerLine = lines.map( l => l.split("""\W+""").length)

        countPerLine.sum







    }
    // countWords(Source.fromFile("big.txt").getLines()) => slow

    val groupSize = 1<<12 // the group has size 2^12
    
    val blockedCountFuture = Source.fromFile("big.txt")
          .getLines()
          .grouped(groupSize)
          .map(lineGroup => Future{countWords(lineGroup)})

    val totalF = Future.sequence(blockedCountFuture).map{counts => counts.sum}

    val total = Await.result(totalF,Duration.Inf)

    println(s"total word = $total")

    // totalF.onSuccess(case c => println(s"total word = $c")) => it goes out of the app as we don't wait for anything



}