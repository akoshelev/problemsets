import java.util
import collection.JavaConverters._

/**
  * This spec contains tests for the following problem:
  *
  * Given a linked list, implement operation reverse() that reverses the given linked list.
  * Example input: LinkedList(1, 2, 3)
  * Example output: LinkedList(3, 2, 1)
  */

class ReverseLinkedListSpec extends UnitSpec {

  "LinkedList reverser" should "reverse (1, 2, 3) to (3, 2, 1)" in {
    // Arrange
    val ll = new LinkedList[Int]()
    ll insert 1
    ll insert 2
    ll insert 3

    // Act
    ll.reverse()

    // Assert
    assert(ll.asScala.toList == List(3, 2, 1))
  }

  it should "reverse (1) to (1)" in {
    val ll = new LinkedList[Int]()
    ll insert 1

    // Act
    ll.reverse()

    // Assert
    assert(ll.asScala.toList == List(1))
  }

  it should "reverse (1, 2) to (2, 1)" in {
    val ll = new LinkedList[Int]()
    ll insert 1
    ll insert 2

    // Act
    ll.reverse()

    // Assert
    assert(ll.asScala.toList == List(2, 1))
  }

  it should "reverse empty list to empty list" in {
    val ll = new LinkedList[Int]()

    // Act
    ll.reverse()

    // Assert
    assert(ll.asScala.toList == List())
  }
}
