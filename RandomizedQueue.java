import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int count;
    private Item[] randomDeque;

    public RandomizedQueue() {
        // construct an empty randomized queue
        count = 0;
        randomDeque = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        // is the randomized queue empty?
        return count == 0;
    }

    public int size() {
        // return the number of items on the randomized queue
        return count;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }

        randomDeque[count++] = item;

        if (count == randomDeque.length) {
            grow(2 * count);
        }
    }

    public Item dequeue() {
        // remove and return a random item
        if (size() == 0) {
            throw new NoSuchElementException("Deque is empty");
        }

        int randInt = StdRandom.uniform(count);
        Item returnItem = randomDeque[randInt];
        randomDeque[randInt] = randomDeque[count - 1];
        randomDeque[--count] = null;

        // See if the array usage is less than a quarter of the array.
        // If it is reduce it's size by half.
        if (count == randomDeque.length / 4) {
            grow(randomDeque.length / 2);
        }

        return returnItem;
    }

    public Item sample() {
        // return a random item (but do not remove it)
        if (size() == 0) {
            throw new NoSuchElementException("Deque is empty");
        }

        int randInt = StdRandom.uniform(0, count);
        return randomDeque[randInt];
    }

    private void grow(int capicity) {
        Item[] copy = (Item[]) new Object[capicity];

        for (int i = 0; i < count; i++) {
            copy[i] = randomDeque[i];
        }

        randomDeque = copy;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] randomDequeCopy;
        int count;

        RandomizedQueueIterator() {
            count = RandomizedQueue.this.count;
            randomDequeCopy = (Item[]) new Object[count];

            for (int i = 0; i < count; i++) {
                randomDequeCopy[i] = randomDeque[i];
            }
        }

        public boolean hasNext() {
            return count != 0;

        }

        public Item next() {
            if (count == 0) {
                throw new NoSuchElementException("no more elements");
            }

            int randInt = StdRandom.uniform(count);
            Item returnItem = randomDequeCopy[randInt];
            randomDequeCopy[randInt] = randomDequeCopy[--count];
            return returnItem;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> d = new RandomizedQueue<>();

        for (int i = 0; i < 64; i++) {
            d.enqueue(i);
        }

        Iterator<Integer> i1 = d.iterator();

        while (i1.hasNext()) {
            System.out.println("Iterator it " + i1.next());
        }
    }
}