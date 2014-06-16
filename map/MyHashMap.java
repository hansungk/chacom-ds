import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class MyHashMap {

    private transient LinkedList table[];
    private int initCapacity;
    private int size;

    /*
     * Initialize bucket array
     */
    public MyHashMap(int initialCapacity) {
        size = 0;
        initCapacity = initialCapacity;
        table = new LinkedList[initCapacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList();
        }
    }

    /*
     * Custom hash code generate function
     * It says 'hash code', but in real sense
     * it contains all the hash function functionality
     * (including hash code -> bucket array index conversion)
     */
    public int myHashCode (Object o) {
        return Math.abs(o.hashCode()) % initCapacity;
    }

    /*
     * Clear bucket array
     */
    public void clear () {
        for (int i = 0; i < table.length; i++) {
            table[i].clear();
        }
        size = 0;
    }

    /*
     * Determine whether the value the key is pointing
     * exists in the bucket array
     */
    public boolean containsKey (Object key) {
        int hcode = myHashCode(key);
        LinkedList list = table[hcode];
        ListIterator itr = list.listIterator(0);
        // Loop through a linked list at a time
        while (itr.hasNext()) {
            Entry e = (Entry)itr.next();
            if ((e.key).equals(key)) {
                return true;
            }
        }
        return false;
    }

    /*
     * Determine whether the value we are looking for
     * exists in the bucket array
     */
    // Running time complexity is Theta(n). In the worst case it would
    // need to go all the way through the table to find the value.
    public boolean containsValue (Object value) {
        // Loop through the hash table array
        for (int i = 0; i < table.length; i++) {
            LinkedList list = table[i];
            ListIterator itr = list.listIterator(0);
            // Loop through a linked list at a time
            while (itr.hasNext()) {
                Entry e = (Entry)itr.next();
                if ((e.value.equals(value))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Old one replaced by the new one below.
     *
     public void put (Object key, Object value) {
     int hcode = myHashCode(key);     // Theta(1)
     LinkedList list = table[hcode];  // Theta(1)
     Entry e = new Entry(key,value);  // Theta(1)
     if (list.add(e)) size++;         // Theta(1)
     }
     */

    // This one conforms to the Java documentation
    // Duplicate keys are not allowed!
    public Object put (Object key, Object value) {
        int hcode = myHashCode(key);
        LinkedList list = table[hcode];
        ListIterator itr = list.listIterator(0);
        Object result = null;
        while (itr.hasNext()) { // Check collision
            Entry e = (Entry)itr.next();
            if ((e.key).equals(key)) {  // Collision occurred (key)
                result = e.value;   // Return old value
                e.value = value;    // ??? Update to new value
                break;
            }
        }
        if (result == null) {
            Entry e = new Entry(key,value);
            if (list.add(e))
                size++;
        }
        return result; // Only return non-null when no collision occurred
    }

    public Object get (Object key) {
        int hcode = myHashCode(key);
        LinkedList list = table[hcode];
        ListIterator itr = list.listIterator(0);
        while (itr.hasNext()) {
            Entry e = (Entry)itr.next();
            if ((e.key).equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    // Running time complexity is Theta(1). The number of iteration that the
    // while loop goes through is constant compared to the size of the table.
    public Object remove (Object key) {
        int hcode = myHashCode(key);
        LinkedList list = table[hcode];
        ListIterator itr = list.listIterator(0);
        while (itr.hasNext()) {
            Entry e = (Entry)itr.next();
            if ((e.key).equals(key)) {
                if (list.remove(e)) {
                    size--;
                    return e.value;
                }
                else {
                    return null;
                }
            }
        }
        return null;
    }

    public boolean isEmpty () {
        return size == 0;
    }

    public int size () {
        return size;
    }


    public void display () {
        LinkedList list;
        ListIterator itr;
        System.out.println("Display the table. . .");
        for (int i = 0; i < table.length; i++) {
            list = table[i];
            itr = list.listIterator(0);
            System.out.print("    Bucket " + i + ": ");
            while (itr.hasNext()) {
                Entry e = (Entry)itr.next();
                System.out.print(e.key + ":" + e.value + "  ");
            }
            System.out.println();
        }
    }

    private static class Entry {
        Object key;
        Object value;

        public Entry (Object newKey, Object newValue) {
            key = newKey;
            value = newValue;
        }
    }

    public static void main (String[] args) {
        MyHashMap hm = new MyHashMap(7);

        String k1 = new String("apple");
        String v1 = new String("appleVal");
        System.out.println("1. size = " + hm.size());
        System.out.println(hm.put(k1, v1));
        System.out.println("2. size = " + hm.size());
        System.out.println(hm.put(k1, v1));
        System.out.println("3. size = " + hm.size());

        System.out.println("4. " + hm.put("CS", "11"));
        System.out.println(hm.put("CS", "22"));
        System.out.println(hm.put("Econ", "23"));
        System.out.println(hm.put("Chem", "33"));
        System.out.println(hm.put("Bio", "44"));
        System.out.println(hm.put("EE", "55"));
        System.out.println(hm.put("EE", "77"));
        System.out.println(hm.put("Maa", "77")); // ??? Value collision

        hm.display();

        System.out.println("5. size = " + hm.size());

        System.out.println("before remove Bio " + hm.get("Bio"));
        hm.remove("Bio");
        System.out.println("after remove  Bio " + hm.get("Bio"));

        System.out.println("6. size = " + hm.size());

        System.out.println("containsKey Bio = " + hm.containsKey("Bio"));
        System.out.println("containsKey EE = " + hm.containsKey("EE"));

        System.out.println("containsValue 44 = " + hm.containsValue("44"));
        System.out.println("containsValue 77 = " + hm.containsValue("77"));

        System.out.println("7. size = " + hm.size());
        System.out.println("hm is empty (T or F) " + hm.isEmpty());
        hm.clear();
        System.out.println("8. size = " + hm.size());
        System.out.println("hm is empty (T or F) " + hm.isEmpty());
    }
}
