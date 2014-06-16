/* BinarySearchTree exercise code
 *
 * You need to do two exercises on this code
 *
 * Work1: Maintaining the size of tree
 *
 *        Add a few lines to maintain the size of the tree.
 *        Implement below function and check if it works.
 *
 *          // return the number of elements of this tree
 *          public int size();
 *
 *          // return the number of elements of sub tree with its root x
 *          private int size(Node x);
 *
 * Work2: Additional functions to use tree sort
 *
 *        Implement below and use this bst for "tree sort".
 *        You can either create your own TreeIterator class, or just methods
 *        that are needed.
 *
 *          // if nd has a right child, successor is the leftmost node in
 *             right subtree of nd.
 *          // if nd has no right child, go up the tree to the left as far
 *             as possible, then go up to the right once.
 *          protected Node successor(Node nd);
 *
 *          // check it has next element
 *          public boolean hasNext();
 *
 *          // get next element
 *          public int next();
 */


public class BinarySearchTree {
    private Node root;

    private class Node {
        public int value;
        public Node left, right, parent;

        // Work1
        public int size;

        public Node(int element) {
            this.value = element;
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if(x == null) return 0;
        return x.size + size(x.left) + size(x.right) + 1;
    }

    public boolean add(int element) {
        root = add(root, element);
        if(root != null)
            return true;
        else
            return false;

    }
    private Node add(Node x, int element) {
        if(x == null) return new Node(element);

        if(element < x.value)
            x.left = add(x.left, element);
        else if(element > x.value)
            x.right = add(x.right, element);
        // Work1: increase the size
        else {
            // At this point, element is already in the tree
            // and duplication has ocurred
            System.out.println("Duplicate");
            x.size++;
        }
        return x;
    }

    public boolean contains(int n) {
        return contains(root, n);
    }

    private boolean contains(Node x, int n) {
        if(x == null) return false;
        if(n < x.value)
            return contains(x.left, n);
        else if(n > x.value)
            return contains(x.right, n);
        else
            return true;
    }

    public boolean remove(int n) {
        Node rtn = remove(root, n);
        if(rtn != null)
            return true;
        else
            return false;
    }
    private Node remove(Node x, int n) {
        if(x == null) return null;

        if(n < x.value)
            x.left = remove(x.left, n);
        else if(n > x.value)
            x.right = remove(x.right, n);
        else {
            // Work1: reduce the size
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            x.value = findMin(x.right).value;
            x.right = removeMin(x.right);
            if(x.size > 0) {    // This means x has a duplicate
                System.out.println("Removing duplicate");
                x.size--;
            }
        }
        return x;
    }

    protected Node findMin(Node x) {
        if(x != null)
            while(x.left != null)
                x = x.left;
        return x;
    }

    protected Node removeMin(Node x) {
        if(x == null) return null;
        if(x.left != null) {
            x.left = removeMin(x.left);
            return x;
        } else
            return x.right;
    }

    // Work2
    // Search for n in the tree
    public boolean search(int n) {
        return false;
    }

    private Node parent(Node x) {
        Node p = root;
        int n = x.value;

        if (p.value == x.value) // root has no parent
            return null;

        while(p != null) {
            if(p.left.value == n || p.right.value == n) {
                return p;
            }
            else if(n > p.value)
                p = p.right;
            else if(n < p.value)
                p = p.left;
        }
        // If reaches the parent search is failed
        return null;
    }
    
    /*public int checkParent(int n) {
        return parent(n).value;
    }*/

    protected Node successor(Node nd) { 
        if(nd.right != null) { // Has right child
            //return findMin(nd.right);
            nd = nd.right;
            while(nd.left != null) {
                nd = nd.left;
            }
            return nd;
        }
        else { // Has no right child, works good
            while(parent(nd) != null && parent(nd).value < nd.value) { // Go up leftmost
                nd = parent(nd);
            }
            return parent(nd); // Go up right
        }
    }
    
    public void successorTest() {
        Node test = root;
        System.out.println(test.value);
        System.out.println(successor(test).value);
    }

    private class TreeIterator {
        private Node current;

        public TreeIterator() {
            current = findMin(root);
        }

        public Node getCurrent() {
            return current;
        }

        public boolean hasNext() { return successor(current) != null; }
        public int next() {
            current = successor(current);
            return current.value;
        }
    }

    public void iteratorTest() {
        TreeIterator it = new TreeIterator();

        System.out.println(it.getCurrent().value);

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
