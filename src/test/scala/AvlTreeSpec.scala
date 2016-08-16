import AvlTree.Node
import org.scalacheck._
import org.scalatest._
import prop._
import org.scalacheck.Arbitrary.arbitrary

/**
  * This spec contains tests for the following problem:
  * Given a set of integers, provide an implementation of AVL tree with the following methods:
  * insert - adds an integer to the tree and balances it if needed
  * exists - checks whether the specified element presents in the tree.
  *
  * Note: implementation could easily be extended to support all types, not only integers,
  * by implementing a generic version of AVL tree and providing a comparer for such types of elements.
  */
class AvlTreeSpec extends PropSpec with PropertyChecks with Matchers {

  def generateSetOf(n: Int): Gen[Set[Int]] = {
    Gen.containerOfN[Set, Int](n, arbitrary[Int])
  }

  def inOrderTraversal(node: AvlTree.Node): List[Int] =
    node match {
      case null => List()
      case n => inOrderTraversal(n.left) ++ List(n.data) ++ inOrderTraversal(n.right)
    }

  def verifyHeights(node: AvlTree.Node)(max: Int): Unit = {

    node match {
      case null =>
      case n =>
        n.height should be < {
          max
        }
        verifyHeights(n.left)(max - 1)
        verifyHeights(n.right)(max - 1)
    }
  }

  property("insert keeps the Avl tree sorted") {
    forAll { (l: List[Int]) => whenever(l.nonEmpty) {
      val tree = new AvlTree()

      l foreach tree.insert

      inOrderTraversal(tree.getRoot) should equal {
        l.distinct.sorted
      }
    }
    }
  }

  property("parent nodes have greater height than child") {

    forAll { l: List[Int] => whenever(l.nonEmpty) {
      val tree = new AvlTree

      l foreach tree.insert

      verifyHeights(tree.getRoot)(tree.getRoot.height + 1)
    }
    }
  }

  property("inserting sorted list keeps tree balanced") {
    forAll(generateSetOf(3), Gen.oneOf(true, false)) { (s, reverse: Boolean) => whenever(s.size == 3) {
      // Arrange
      val tree = new AvlTree
      val l = if (reverse) s.toList.sorted.reverse else s.toList.sorted

      // Act
      l foreach tree.insert

      // Assert
      val root = tree.getRoot
      (root.left, root.right) shouldNot be (null, null)
      (root.left.height, root.right.height) should be (1, 1)
      root.height should be (2)
    }}
  }

  property("inserting three arbitrary items keeps tree balanced") {
    forAll { (a: Int, b: Int, c: Int) => whenever(a != b && b != c && a != c) {
      // Arrange
      val tree = new AvlTree

      // Act
      List(a, b, c) foreach tree.insert

      // Assert
      val root = tree.getRoot
      (root.left, root.right) shouldNot be (null, null)
      (root.left.height, root.right.height) should be (1, 1)
      root.height should be (2)
    }}
  }

  property("inserting any amount of items keeps height of the tree less than log2(n)") {

    def log2(v: Int): Double = Math.log(v) / Math.log(2)

    forAll { l: List[Int] => whenever(l.nonEmpty) {
      // Arrange
      val tree = new AvlTree
      val maxHeight = Math.round(log2(l.length)).toInt + 1

      // Act
      l foreach tree.insert

      // Assert
      tree.getRoot.height should be <= { maxHeight }
    }}
  }

  property("inserting any amount of items keeps tree balanced") {

    def log2(v: Int): Double = Math.log(v) / Math.log(2)

    def traverse(root: Node, depth: Int)(implicit maxDepth: Int): Unit = {
      root match {
        case null =>
        case n =>
          depth should be <= { maxDepth }
          traverse(root.left, depth + 1)
          traverse(root.right, depth + 1)
      }

    }

    forAll { l: List[Int] => whenever(l.nonEmpty) {
      // Arrange
      val tree = new AvlTree
      implicit val maxDepth = Math.round(log2(l.length)).toInt + 1

      // Act
      l foreach tree.insert

      // Assert
      traverse(tree.getRoot, 1)
    }}
  }

}
