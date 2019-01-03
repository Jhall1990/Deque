import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

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

        for (int i = 0; i < randomDeque.length; i++) {
            copy[i] = randomDeque[i];
        }

        randomDeque = copy;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] randomQueueCopy;
        int index;

        RandomizedQueueIterator() {
            randomQueueCopy = (Item[]) new Object[count];

            for (int i = 0; i < count; i++) {
                int randIndex = StdRandom.uniform(count);
                while (randomQueueCopy[randIndex] != null) {
                    randIndex = StdRandom.uniform(count);
                }
                randomQueueCopy[randIndex] = randomDeque[randIndex];
            }
            index = 0;
        }

        public boolean hasNext() {
            return index != count;

        }

        public Item next() {
            if (index == count) {
                throw new NoSuchElementException("no more elements");
            }

            return randomQueueCopy[index++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> d = new RandomizedQueue<>();

        d.enqueue(0);
        d.enqueue(1);
        d.enqueue(2);
        d.enqueue(3);
        d.enqueue(4);
        d.enqueue(5);
        d.enqueue(6);
        d.enqueue(7);
        d.enqueue(8);
        d.enqueue(9);

        Iterator<Integer> i1 = d.iterator();
        Iterator<Integer> i2 = d.iterator();

        while (i1.hasNext()) {
            System.out.println("Iterator it " + i1.next());
        }

        while (i2.hasNext()) {
            System.out.println("Iterator it2 " + i2.next());
        }
    }
}