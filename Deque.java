import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int head;
    private int tail;
    private int count;
    private Item[] deque;

    public Deque() {
        // construct an empty deque
        count = 0;

        // This is needed because we cannot create a generic array in java
        deque = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        // return the number of items on the deque
        return count;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if (count == 0) {
            deque[0] = item;
        } else {
            if (head == 0) {
                resizeHead(deque.length * 2);
            }
            deque[--head] = item;
        }

        // Increment the count.
        count++;
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }

        if (count == 0) {
            deque[0] = item;
        } else {
            if (tail + 1 == deque.length) {
                resizeTail(deque.length * 2);
            }
            deque[++tail] = item;

        }

        // Increment the count.
        count++;
    }

    public Item removeFirst() {
        // remove and return the item from the front
        Item returnItem = deque[head];
        deque[head] = null;
        head++;
        count--;

        if (returnItem == null) {
            throw new NoSuchElementException();
        }
        return returnItem;
    }

    public Item removeLast() {
        // remove and return the item from the end
        Item returnItem = deque[tail];
        deque[tail] = null;
        tail--;
        count--;

        if (returnItem == null) {
            throw new NoSuchElementException();
        }
        return returnItem;
    }

    private void resizeHead(int capacity) {
        // Needed because you cannot create a generic array in java.
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = deque.length; i > 0; i--) {
            copy[copy.length - i] = deque[deque.length - i];
        }

        head = copy.length - deque.length;
        tail = head + count - 1;
        deque = copy;
    }

    private void resizeTail(int capacity) {
        // Needed because you cannot create a generic array in java.
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < deque.length; i++) {
            copy[i] = deque[i];
        }

        deque = copy;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int current = head;

        public boolean hasNext() {
            return deque[current] != null;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove is not supported");
        }

        public Item next() {
            Item returnItem = deque[++current];

            if (returnItem == null) {
                throw new NoSuchElementException();
            }
            return deque[++current];
        }
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();

        d.addFirst(0);
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        d.addFirst(4);
        d.addLast(-1);
        d.addLast(-2);
        d.addLast(-3);
        d.addLast(-4);
        d.addLast(-5);
        d.addLast(-6);
        d.addLast(-7);
        d.addLast(-8);
        d.addLast(-9);
        d.addFirst(5);
        d.addFirst(6);
        d.addFirst(7);
        d.addFirst(8);

        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());
        System.out.println(d.removeFirst());

        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());

        System.out.println("done");
    }
}

//import java.util.Iterator;
//
//public class Deque<Item> implements Iterable<Item> {
//    private class TwoLinkNode {
//        private Item data;
//        TwoLinkNode next;
//        TwoLinkNode prev;
//
//        TwoLinkNode(Item i) {
//            data = i;
//        }
//    }
//
//    private TwoLinkNode head;
//    private TwoLinkNode tail;
//    private int count;
//
//    public Deque() {
//        // construct an empty deque
//        count = 0;
//    }
//
//    public boolean isEmpty() {
//        return head == null && tail == null;
//    }
//
//    public int size() {
//        // return the number of items on the deque
//        return count;
//    }
//
//    public void addFirst(Item item) {
//        // add the item to the front
//        // Increment the count.
//        count++;
//
//        TwoLinkNode newNode = new TwoLinkNode(item);
//
//        if (head == null && tail == null) {
//            head = newNode;
//            tail = newNode;
//        } else {
//            TwoLinkNode origHead = head;
//            head = newNode;
//            newNode.next = origHead;
//            origHead.prev = newNode;
//        }
//    }
//
//    public void addLast(Item item) {
//        // add the item to the end
//        // Decrement the count.
//        count++;
//
//        TwoLinkNode newNode = new TwoLinkNode(item);
//
//        if (head == null && tail == null) {
//            head = newNode;
//            tail = newNode;
//        } else {
//            TwoLinkNode origTail = tail;
//            tail = newNode;
//            newNode.prev = origTail;
//            origTail.next = newNode;
//        }
//    }
//
//    public Item removeFirst() {
//        // remove and return the item from the front
//        // Move the first pointer to the original first item's next pointer.
//        count--;
//        TwoLinkNode origHead = head;
//        head = origHead.prev;
//        return origHead.data;
//    }
//
//    public Item removeLast() {
//        // remove and return the item from the end
//        count--;
//        TwoLinkNode origTail = tail;
//        tail = origTail.next;
//        return origTail.data;
//    }
//
//    public Iterator<Item> iterator() {
//        // return an iterator over items in order from front to end
//        return new DequeIterator();
//    }
//
//    private class DequeIterator implements Iterator<Item> {
//        private TwoLinkNode current = head;
//
//        @Override
//        public boolean hasNext() {
//            return current != null;
//        }
//
//        public Item next() {
//            Item data = current.data;
//            current = current.next;
//            return data;
//        }
//    }
//}
