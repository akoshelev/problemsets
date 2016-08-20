import org.scalatest.{Matchers, PropSpec}
import org.scalatest.prop.PropertyChecks

/**
  * This spec contains tests for the following problem:
  * Provide an implementation for heap-based priority queue with support of the following
  * operations:
  * - insert element
  * - return minimum
  * - remove minimum
  */
class MinPQSpec extends PropSpec with PropertyChecks with Matchers {

  property("inserting one element into an empty queue sets it as a minimum") {
    forAll { i: Int =>
      val heap = new MinPQ(1)

      heap insert i

      heap.min() should be {i}
    }
  }

  property("inserting 2 items returns smallest one as min") {
    forAll { (a: Int, b: Int) =>
      val heap = new MinPQ(2)

      heap.insert(b)
      heap.insert(a)

      heap.min() should be { Math.min(a, b)}
    }
  }

  property("inserting arbitrary number of elements and dequeuing them returns sorted list") {
    forAll { l: List[Int] =>

      val l2 = List(0, 0, -1, -1)
//      val l2 = l
      var result = List[Int]()
      val heap = new MinPQ(l2.length)

      l2 foreach heap.insert

      while (heap.size() > 0) {
        result = heap.delMin() :: result
      }

      result.reverse should be { l2.sorted }

    }
  }

  property("inserting arbitrary number of items increases heap's size") {
    forAll { l: List[Int] =>
      val heap = new MinPQ(l.length)

      l foreach heap.insert

      heap.size() should be { l.length }
    }
  }


}
