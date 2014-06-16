Stack
=====

LIFO(Last in first out)

Parenthesis matching

### Implementation
* void push(E element)
* E pop()
* E top()

* boolean isEmpty, int size


Queue
=====

FIFO(First in first out)

Bus stop

### Implementation
* void enqueue(E element)
* E dequeue()
* E front()


Map
===

### Implementation
* V get(key)
* V put(key, value)
* V remove(key)
* class Entry
    * FIXME

### Methods
1. Sorted array
2. Balanced binary search tree
3. Hash table


Trees
=====

* Binary trees
* BST

Binary trees
------------

* Height

Height = maximum depth

* Level

Level = depth

* Traversal
    * Depth-first-traversal
        * In-order traversal : left-root-right
        * Post-order traversal : left-right-root
        * Pre-order traversal : root-left-right
    * Breadth-first-traversal

Binary search trees
-------------------

* Average
    * insert: log n
    * search: log n
    * remove: log n
* Worst
    * insert: n
    * search: n
    * remove: n

### Successor
* Has right child : **leftmost node** of **right subtree**
* No right child : **go leftmost** then **go right once**

### Implementation

* boolean add(Node x, E element) *Element and n seems equivalent*
* boolean remove(Node x, int n)
* contains(int n)

Remove : using in-order successor  
??? How to build a spell checker  
??? What is a set exactly  


Hash Tables
===========

Hash Code
---------

* For numeric keys 
    * Integer casting
    * Component summation

* For string keys
    * Polynomial accumulation
    * Cyclic shift

* For object keys
    * Memory address as integer

Collision handling methods
-------------------------

1. Separate chaining
    * Use external memory space
    * Map operation delegated to lists in bucket

2. Open addressing
    * Linear probing
        * May result to longer probe seqs

??? Load factor  

### Rehashing
Good strategy is doubling.

1. Make double-sized array
2. Make new hash function
3. Copy elements unto new
