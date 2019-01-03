import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private TwoLinkNode head;
    private TwoLinkNode tail;
    private int count;

    /*
    Double linked node class. Keeps track of both the next
    and previous nodes. Accepts a generic item on construction.
     */
    private class TwoLinkNode {
        TwoLinkNode next;
        TwoLinkNode prev;
        private final Item data;

        TwoLinkNode(Item i) {
            data = i;
        }
    }

    public Deque() {
        // construct an empty deque
        count = 0;
    }

    public boolean isEmpty() {
        // Check if the deque is empty by seeing if both head and tail
        // are null.
        return head == null && tail == null;
    }

    public int size() {
        // return the number of items on the deque
        return count;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }

        count++;

        TwoLinkNode newNode = new TwoLinkNode(item);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            TwoLinkNode origHead = head;
            head = newNode;
            newNode.next = origHead;
            origHead.prev = newNode;
        }
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }

        count++;

        TwoLinkNode newNode = new TwoLinkNode(item);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            TwoLinkNode origTail = tail;
            tail = newNode;
            newNode.prev = origTail;
            origTail.next = newNode;
        }
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        count--;
        TwoLinkNode origHead = head;
        head = origHead.next;

        // Check if the new head is null, If it is we know
        // the deque is empty, set the tail to null as well.
        if (head == null) {
            tail = null;
        } else {
            // Otherwise set the new head node's prev pointer to null.
            head.prev = null;
        }

        return origHead.data;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        count--;
        TwoLinkNode origTail = tail;
        tail = origTail.prev;

        // Check if the new tail is null. If it is we know
        // the deque is empty, set the head to null as well.
        if (tail == null) {
            head = null;
        } else {
            // Otherwise set the new tail node's next pointer to null.
            tail.next = null;
        }

        return origTail.data;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private TwoLinkNode current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("Deque is empty");
            }

            Item data = current.data;
            current = current.next;
            return data;
        }
    }
}
