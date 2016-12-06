def isPrime(n:Int) = (2 until n).forall(d => n%d != 0)

// (1000 to 10000) filter isPrime)(1) // too long, compute too much

def fib(n: Int): Long = if (n<2) 1 else fib(n-1) + fib(n-2)

lazy val ff = fib(45)
val suspFib = () => fib(45) // same effect but doesn't cache the answer.


/*

Stream[T] i similar to List but:   
    the tail part is lazy 
    Stream.empty = List.Nil
*/

def toN(n: Int): Stream[Int] = {
    def next(i: Int): Stream[Int] = {
        if (i>n) Stream.empty
        // else Stream.cons(i,next(i+1))
        else i #:: next(i+1)
    }
    next(1)
}


// scala> val s = toN(7)
// s: Stream[Int] = Stream(1, ?)
// scala> s.tail
// res2: scala.collection.immutable.Stream[Int] = Stream(2, ?)

def from(n: Int): Stream[Int] = n #:: from(n+1)

// scala> val z0 = from(0)
// z0: Stream[Int] = Stream(0, ?)

// scala> val evenNumber = z0.filter(_%2 ==0)
// evenNumber: scala.collection.immutable.Stream[Int] = Stream(0, ?)

// scala> evenNumber.tail
// res6: scala.collection.immutable.Stream[Int] = Stream(2, ?)

def between(a:Int , b:Int): Stream[Int] = {
    def next(i: Int): Stream[Int] = {
        if (i>b) Stream.empty
        else i #:: next(i+1)
    }
    next(a)
}

def secondPrime(a:Int,b:Int): Int = {
    between(a,b).filter(isPrime)(1)
}

def fib: Stream[Long] = {
    def next(prev:Long,twoPrev:Long): Stream[Long] = {
        val f = prev+twoPrev
        f #:: next(f,prev)
    }
    1 #:: next(1,0)
}



// Eratosthenes

def primes(upto: Int) = {
    val primesList = (2 to upto).foldLeft(Nil: List[Int]) { (primesSofar,num)=>{
        val numIsPrime = primesSofar.forall{p=>num%p != 0}
        if (numIsPrime) num::primesSofar
        else primesSofar
        }
    }
    primesList.reverse
}

def infprimes: Stream[Long] = {
    def from(n: Long): Stream[Long] = n #:: from(n+1)
    def next(psf: List[Long],start: Long): Stream[Long] = {
        def primeWrtPSF(num:Long) = psf.forall(d=> num%d !=0)

        (from(start) find primeWrtPSF) match {
            case Some(p) => p #:: next(p::psf,p+1)
            case None => throw new Exception("Infinitely prime") 
        }
        
    }
    next(List(),2)
}







