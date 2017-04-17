package warmup

/*
 * Warm-up exercises. Helpful to get more comfortable with scala before the heavy stuff.
 */
object Warmup {

  /*
   *
   * The following examples are all based upon the 'List'
   * data structure.
   *
   * In scala this data structure this looks like:
   *
   * {{{
   *   sealed trait List[+A]
   *   case object Nil extends List[Nothing]
   *   case class ::[A](h: A, t: List[A]) extends List[A]
   * }}}
   *
   * We call this a "sum-type", where "List" is a type
   * constructor that has two data constructors, "Nil",
   * and "::" (pronounced ~cons~). We can declare values
   * of type List using either the data constructors or
   * via the convenience function `List`.
   *
   * {{{
   *   val xs = "goodbye" :: "cruel" :: "world" :: Nil
   *   val ys = List("we", "have", "the", "technology")
   * }}}
   *
   * Lists can be worked with via pattern matching or via
   * the standard library methods foldRight & foldLeft
   * that have are defined as:
   *
   * {{{
   *   List[A]#foldRight[B](z: B)(f: (A, B) => B)
   *   List[A]#foldLeft[B](z: B)(f: (B, A) => A)
   * }}}
   *
   */


  /*
   * Example 0.1:
   *
   * Implement length using pattern matching.
   *
   * scala> import warmup.Warmup._
   * scala> length(List(1, 2, 3, 4))
   * resX: Int = 4
   */
  def length[A](xs: List[A]): Int =
    xs match {
      case Nil =>
        0
      case y :: ys =>
        1 + length(ys)
    }


  /*
   * Example 0.2:
   *
   * Implement length using foldRight.
   *
   * scala> import warmup.Warmup._
   * scala> lengthX(List(1, 2, 3, 4))
   * resX: Int = 4
   */
  def lengthX[A](xs: List[A]): Int =
    xs.foldRight(0)((_, acc) => acc + 1)

  /*
   * Exercise: 0.1:
   *
   * Append two lists to produce a new list.
   *
   * scala> import warmup.Warmup._
   * scala> append(List(1, 2, 3, 4), List(5, 6, 7, 8))
   * resX: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
   */
  def append[A](x: List[A], y: List[A]): List[A] =
    ???

  /*
   * Exercise: 0.2:
   *
   * Map the given function across each element of the list.
   *
   * scala> import warmup.Warmup._
   * scala> map(List(1, 2, 3, 4))(x => x + 1)
   * resX: List[Int] = List(2, 3, 4, 5)
   *
   * ~~~ Syntax hint: type annotations
   *
   *     (Nil : List[A]) // Nil _is of type_ List[A]
   *
   *     Type annotations are required when scala can
   *     not infer what you mean.
   */
  def map[A, B](xs: List[A])(f: A => B): List[B] =
    ???

  /*
   * Exercise: 0.3:
   *
   * Return elements satisfying the given predicate.
   *
   * scala> import warmup.Warmup._
   * scala> filter(List(1, 2, 3, 4))(i => i < 3)
   * resX: List[Int] = List(1, 2)
   */
  def filter[A](xs: List[A])(p: A => Boolean): List[A] =
    ???

  /*
   * Exercise: 0.4:
   *
   * Reverse a list to produce a new list.
   *
   * scala> import warmup.Warmup._
   * scala> reverse(List(1, 2, 3, 4))
   * resX: List[Int] = List(4, 3, 2, 1)
   * scala> reverse((1 to 50000).toList) take 5
   * resX: List[Int] = List(50000, 49999, 49998, 49997, 49996)
   *
   * ~~~ Syntax hint: type annotations
   *
   *     (Nil : List[A]) // Nil _is of type_ List[A]
   *
   *     Type annotations are required when scala can
   *     not infer what you mean.
   */
  def reverse[A](xs: List[A]): List[A] = {
    // def loop[A](old: List[A], rev: List[A]): List[A] = {
    //   old match {
    //     case Nil => rev
    //     case head :: tail => loop(tail, head :: rev)
    //   }
    //   loop(xs, Nil)
    // }
    // Foldleft
    xs.foldLeft(List[A]())((rev, a) => a :: rev)
  }


  /*
   * *Challenge* Exercise: 0.5:
   *
   * Return a list of ranges. A range is a pair of values for which each intermediate 
   * value exists in the list. 
   *
   * scala> import warmup.Warmup._
   * scala> ranges(List(1, 1))
   * resX: List[(Int, Int)] = List((1, 1))
   * scala> ranges(List(1, 2, 3, 4))
   * resX: List[(Int, Int)] = List((1, 4))
   * scala> ranges(List(1, 2, 4))
   * resX: List[(Int, Int)] = List((1, 2), List(4, 4))
   * scala> ranges(List(2, 1, 3, 4, 9, 7, 8, 10, 30, 30, 4, 41))
   * resX: List[(Int, Int)] = List((1, 4), (7, 10), (30, 30), (40, 41))
   *
   * ~~~ library hint: use can just use List[A]#sorted to sort the list before you start.
   * ~~~ library hint: List[A]#min and List#max exist.
   */
  def ranges(xs: List[Int]): List[(Int, Int)] = {
    def loop(xs: List[Int], tuples: List[(Int, Int)]): List[(Int, Int)] =
      xs match {
        case Nil => tuples
        case head :: tail => {
          if (!tuples.isEmpty && (head == tuples.last._2 + 1))
            loop(tail, tuples.updated(tuples.length - 1, (tuples.last._1, head)))
          else
            loop(tail, tuples :+ (head, head))
        }
      }
    loop(xs.sorted, List[(Int, Int)]())
  }

  // def ranges(xs: List[Int]): List[(Int, Int)] = {
  //   xs.foldLeft(List[(Int, Int)]())((tuples, num) => {
  //     if (!tuples.isEmpty && (num == tuples.last._2 + 1))
  //       tuples.updated(tuples.length - 1, (tuples.last._1, num))
  //     else
  //       tuples :+ (num, num)
  //   })
  // }
}
