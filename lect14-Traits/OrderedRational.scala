package rmath

class OrderedRational(n:Int,d:Int) extends Rational(n,d) with Ordered[OrderedRational]{ // from trait Ordered with type OrderedRational 

        def compare(that: OrderedRational) = numer*that.denom - that.numer*denom

}