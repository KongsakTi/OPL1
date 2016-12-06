// // Rational Number

// // type Rational = (Int,Int)

// // def add(p: Rational,q: Rational) = (p,q) match {
// //     case ((a,b),(c,d)) => (a*d + c*b,b*d) 
// // }

// // def toString(p: Rational) = p._1.toString + "/" +p._2.toString

// // // bad because we can't enforcing anything, like denominator must != 0, etc.

// case class Rational(n:Int, d:Int){
//     require(d>0," denominator must be > 0")


//     override def toString = n.toString + "/" + d.toString
// }

// def add(p: Rational,q: Rational) = (p,q) match {
//     case (Rational(a,b),Rational(c,d)) => Rational(a*d + c*b,b*d) 
// }

// val f = Rational(1,2)
// val h = Rational(1,3)
// val g = Rational(1,6)

class Rational(n: Int,d:Int){
// class Rational(val n: Int,val d:Int){            will make object call .n, .d possible
    require(d>0," denominator must be > 0")
    private val g = gcd(n,d).abs
    val numer = n/g //val will evaluate now, def will leave the expr like that (by value / by name)
    val denom = d/g
    def this(n: Int) = this(n,1)        // overload the constructor, same as 
    override def toString = numer + "/" + denom

    private def gcd(a: Int, b: Int):Int = {
        if(b==0) a else gcd(b,a%b)
    }

    //def add(that: Rational): Rational = {
    def +(that: Rational): Rational = {
        new Rational(this.numer*that.denom + this.denom*that.numer , this.denom * that.denom)
    }
    //def mult(that: Rational): Rational = {
    def *(that: Rational): Rational = {  
        new Rational(this.numer*that.numer , this.denom * that.denom)
    }
    def unary_- = {
    // def neg(): Rational = {
        new Rational( -numer,denom)
    }

    def less(that: Rational):Boolean = {
        this.numer*that.denom < that.numer*this.denom
    }


}

// val f = new Rational(1,3)
// // def Rational(n: Int,d: Int = 1) = new Rational(n,d) //to eliminate the instantiate part (new)

// f.numer // not f.n


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


abstract class IntList {
    def add(x: Int): IntList = new Cons(x,this)
    def contains(x: Int): Boolean
}

// Empty 

object Empty extends IntList {
    // def add(x: Int): IntList = new Cons(x,this)
    def contains(x: Int): Boolean = false
}

// Cons (Non-empty)

class Cons(elt: Int, tail: IntList) extends IntList {
    // def add(x: Int): IntList = new Cons(x,this)

    def contains(x: Int): Boolean = {
        (x==this.elt) || (tail contains x)
    }

    
}


















