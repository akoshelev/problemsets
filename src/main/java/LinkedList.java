import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private LinkedListNode head;
    private LinkedListNode tail;

    public void insert(T val) {
        if (head == null) {
            head = new LinkedListNode(val, null);
            tail = head;
        }
        else {
            LinkedListNode n = new LinkedListNode(val, null);
            tail.next = n;
            tail = n;
        }
    }

    /**
     * Reverses current linked list.
     */
    public void reverse() {
        if (head == null) return;

        final LinkedListNode oldHead = head;
//        head = iterativeReverse();
        head = recursiveReverse(head, head.next);
        tail = oldHead;
        tail.next = null;
    }

    private LinkedListNode recursiveReverse(LinkedListNode a, LinkedListNode b) {
        if (b == null) return a;

        final LinkedListNode c = b.next;
        b.next = a;
        return recursiveReverse(b, c);
    }

    private LinkedListNode iterativeReverse() {
        // given a linked list 1 (head) -> 2 -> 3 (tail)
        // 1. takes current node a and next node b (as a.next)
        // 2. remembers c = b.next
        // 3. sets b.next = a
        // continues steps 1->3 with a = b, b = c
        // termination when b = null
        // corner cases:
        // 1. empty list
        // 2. list with one element head = tail
        // things to watch out
        // 1. head, tail variables
        // 2. head.next should become null (or tail.next after algorithm terminates)

        LinkedListNode a = head;
        LinkedListNode b = a.next;

        while (b != null) {
            LinkedListNode c = b.next;
            b.next = a;
            a = b;
            b = c;
        }

        return a;
    }



    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            LinkedListNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) throw new NoSuchElementException();

                final T val = current.val;
                current = current.next;

                return val;
            }
        };
    }

    private class LinkedListNode {
        public final T val;
        public LinkedListNode next;

        public LinkedListNode(T val, LinkedListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
