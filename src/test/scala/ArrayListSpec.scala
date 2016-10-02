import org.scalatest.prop.PropertyChecks
import collection.JavaConverters._
import org.scalatest.{PropSpec, Matchers}

class ArrayListUnitSpec extends UnitSpec {
  "ArrayList of strings" should "allow null elements to be added and removed" in {
    // Arrange
    val al = new ArrayList[String]()
    al.add(null)
    al.add("abc")
    al.add(null)
    al.add("bde")

    // Act
    al.remove("abc")
    al.remove("bde")
    while (al.length() > 0) al.remove(null)

    // Assert
    assert(al.length() === 0)
  }
}

class ArrayListSpec extends PropSpec with Matchers with PropertyChecks {

  property("allows to add arbitrary number of elements") {
    forAll { l: List[Int] =>

      val al = new ArrayList[Int]()

      l foreach al.add

      al.asScala.toList should be { l }
    }
  }

  property("after removing all elements arraylist does not contain elements") {
    forAll { l: List[String] =>
      val al = new ArrayList[String]()

      l foreach al.add
      l foreach al.remove

      al.asScala.toList should be { List() }
    }
  }
}
