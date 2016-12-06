def fib(n: Int):Long = {
    if(n<2) 1 else fib(n-1)+fib(n-2)
}

val f43 = Future{fib(43)}
val f44 = Future{fib(44)}
val f45Promise = Promise[Long]()
val f45 = f45Promise.future

f45.onSuccess{case f45 =>
    println("The val is: "+f45)
}


// f43.onSuccess { case f43=>
//     f44.onSuccess{case f44 =>
//         f45Promise.success(f43+f44)
//     }
// }

// Future.sequence(f43,f44) => Future(Seq[Long])

val alof = (1 to 20).map{ n => Future{fib(n)} } //lots of future, Vector(Future[Long],Future[Long],Future[Long])

val fos = Future.sequence(alof)

fos.onSuccess{case fos=> println(fos.sum)}


//////////////////////// Breaking Promise /////////////////////////

case class TaxCut(reduction: Int)
case class Excuse(msg: String) extends Exception(msg)

def redeemPledge():Future[TaxCut] = {
    val p = Promise[TaxCut]
    Future {
        println("Beginning")
        Thread.sleep(2000)
        p.failure(Excuse("Global econ"))
        println("Ooops")
    }
    println("HEREE")

    p.future
}
redeemPledge
















