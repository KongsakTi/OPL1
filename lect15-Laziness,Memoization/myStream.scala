trait Stream[+A] extends Seq[A] {
    def isEmpty: Boolean
    def head:A
    def tail:Stream[A]
    ...    
}

def cons[A](hd: A, tl: => Stream[A]) = new Stream[A]{
    def isEmpty = false
    def head =hd
    def tail = tl
}

val empty = new Stream[Nothing] {
    def isEmpty = true
    def head = throw new NoSuchElementException("NOOOOOO")
    def tail = throw new NoSuchElementException("NOOOOOO")
}













