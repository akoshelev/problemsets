import org.scalatest.{Matchers, PropSpec}
import org.scalatest.prop.PropertyChecks
import collection.JavaConverters._

class PeakValuesSortSpec extends PropSpec with PropertyChecks with Matchers {
  property("peak values correctly sorts items") {

    forAll { l: List[Int] =>

      val ja = l.toArray map java.lang.Integer.valueOf

      PeakValuesSort.sort(ja)

    }
  }

}

