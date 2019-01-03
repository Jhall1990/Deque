import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> d = new RandomizedQueue<>();
        int returnCount = Integer.parseInt(args[0]);

        while (!StdIn.isEmpty()) {
            d.enqueue(StdIn.readString());
        }

        for (int i = 0; i < returnCount; i++) {
            System.out.println(d.dequeue());
        }
    }
}
