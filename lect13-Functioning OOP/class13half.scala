object class13half extends App {
    

    abstract class Expr{
        def toVal(implicit ctx: Map[String,Double]):Double
        def +(that: Expr) = Sum(this,that)
        def +(that: Double) = Sum(this,Const(that))
        def *(that: Expr) = Prod(this,that)
        def unary_- = Neg(this)
        // isConstant(x): if we want to implement this method, we need to add and fix every class
    }
        
    case class Var(name: String) extends Expr{
        def toVal(implicit ctx: Map[String,Double]):Double = ctx(name)
    }
    case class Const(n: Double) extends Expr{
        def toVal(implicit ctx: Map[String,Double]):Double = n

    }
    case class Neg(e: Expr) extends Expr{
        def toVal(implicit ctx: Map[String,Double]):Double = -e.toVal
    }
    case class Sum(e1: Expr,e2: Expr) extends Expr{
        def toVal(implicit ctx: Map[String,Double]):Double = e1.toVal + e2.toVal    
    }
    case class Prod(e1: Expr,e2: Expr) extends Expr{
        def toVal(implicit ctx: Map[String,Double]):Double = e1.toVal * e2.toVal
    }



    val x = Var("x")
    val e = (x+5.0)*x+Const(11)*x
    e.toVal(Map("x"->11.0))

    implicit val ctx = Map("x"->2.0)
    // e.toVal

}


