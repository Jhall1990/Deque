import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    public RandomizedQueue() {
        // construct an empty randomized queue
    }

    public boolean isEmpty() {
        // is the randomized queue empty?
        return true;
    }

    public int size() {
        // return the number of items on the randomized queue
        return 0;
    }

    public void enqueue(Item item) {
        // add the item
    }

    public Item dequeue() {
        // remove and return a random item
        return null;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        return null;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        @Override
        public boolean hasNext() {
            return false;
        }

        public Item next() {
            return null;
        }
    }

    public static void main(String[] args) {
        // unit testing (optional)
    }
}