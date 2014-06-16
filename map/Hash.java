import java.util.*;
import java.io.*;

// Note that readWords uses ".//words3.txt" as the dictionary file.
// Also note that readWords is called statically.
// Run the 'evaluate' method with this file

public class Hash {

    private static Random rand = new Random();

    // Dictionary of lower-case words
    // As the static instance of this class is created,
    // words will be instantiated with whatever readWords returns
    private static ArrayList<String> words = readWords();


    // This one is not used, but shows an idea of how
    // a hash function might be created.
    // Add up the character codes (0 through 25)
    public static int hash1 (String s) {
        int code = 0;
        for (int i = 0; i < s.length(); i++) {
            code = code + (s.charAt(i) - 'a');
        }
        return code;
    }

    // Another example like hash1.
    // View the string as a base 26 number
    public static int hash2 (String s) {
        int code = 0;
        for (int i = 0; i < s.length(); i++) {
            code = (code * 26) + (s.charAt(i) - 'a');
        }
        return code;
    }

    // Java's built-in hash code.  hashCode() is from Object:
    // convert the internal memory address of the object into
    // an integer.
    public static int hash3 (String s) {
        return s.hashCode();
    }


    // Calculate the distribution of keys to buckets
    public static void evaluate (int bsize, int N) {

        // This buckets array will be used as a hash table
        int[] buckets = new int[bsize];

        for (int i = 0; i < N; i++) {
            String s = randomString();
            int index = Math.abs(hash3(s)) % bsize;

            // Rather than actually inserting it into the table, just
            // keep track of how many times this particular bucket
            // has been hit.  If you decide to actually add them into
            // the hash table (buckets), then you would have to worry
            // about what to do when a collision occurs.
            buckets[index]++;
        }

        // The count in index i (say 3) in counts is the number
        // of buckets that got i (say 3) hits.
        int[] counts = new int[N];
        for (int i = 0; i < bsize; i++) {
            counts[buckets[i]]++;
        }

        System.out.println("---------------------------------------");
        for (int i = 0; i < N; i++) {
            if (counts[i] > 0) {
                System.out.println(counts[i] + " buckets contain " + 
                                   i + " keys, i.e., " + counts[i] +
                                   " buckets were hit " + i + " times.");
            }
        }
    }


    // Pick a random string from the dictionary
    public static String randomString () {
        // Note ArrayList has 'get'
        return (String) words.get(rand.nextInt(words.size()));
    }


    // Read in the dictionary
    public static ArrayList<String> readWords () {
        try {
            BufferedReader input =
                new BufferedReader(new FileReader(".//words3.txt")); // Unix
            //  new BufferedReader(new FileReader(".\\words3.txt")); // Windows
            ArrayList<String> dict = new ArrayList<String>();
            String line;
            while ((line = input.readLine()) != null) {
                dict.add(line);  // ArrayList has 'add'
            }
            return dict;
        }
        catch (IOException e) {
            System.out.println("File open failed...");
            return null;
        }
    }

    public static void main(String[] args) {
        evaluate(1000, 1000);
    }
}
