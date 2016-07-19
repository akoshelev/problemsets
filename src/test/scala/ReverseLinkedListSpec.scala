import java.util
import collection.JavaConverters._

class ReverseLinkedListSpec extends UnitSpec {

  "LinkedList reverser" should "reverse items from tail to head" in {
    // Arrange
    val ll = new util.LinkedList[Int](List(1, 2, 3).asJava)
    val reverser = new LinkedListReverser(ll)

    // Act
    val result = reverser.reverse

    // Assert
    assert(result.asScala == List(3, 2, 1))
  }
}
