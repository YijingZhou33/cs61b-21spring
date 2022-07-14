package deque;

/**
 * @author Yijing Zhou
 */
public class LinkedListDeque<T> {
    private class LinkedNode {
        private LinkedNode prev;
        private T item;
        private LinkedNode next;

        LinkedNode(T t, LinkedNode p, LinkedNode n) {
            item = t;
            prev = p;
            next = n;
        }
    }

    private int size;
    private LinkedNode sentinel;

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new LinkedNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds an item to the front of the deque. */
    public void addFirst(T item) {
        LinkedNode oldFirst = sentinel.next;
        LinkedNode newFirst = new LinkedNode(item, sentinel, oldFirst);
        sentinel.next = newFirst;
        oldFirst.prev = newFirst;
        size += 1;
    }

    /** Adds an item to the back of the deque. */
    public void addLast(T item) {
        LinkedNode oldLast = sentinel.prev;
        LinkedNode newLast = new LinkedNode(item, oldLast, sentinel);
        sentinel.prev = newLast;
        oldLast.next = newLast;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space.
     */
    public void printDeque() {
        LinkedNode p = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        LinkedNode oldFirst = sentinel.next;
        LinkedNode newFirst = oldFirst.next;
        sentinel.next = newFirst;
        newFirst.prev = sentinel;
        oldFirst.prev = oldFirst.next = null;
        size -= 1;
        return oldFirst.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (sentinel.next == sentinel) {
            return null;
        }

        LinkedNode oldLast = sentinel.prev;
        LinkedNode newLast = oldLast.prev;
        sentinel.prev = newLast;
        newLast.next = sentinel;
        oldLast.prev = oldLast.next = null;
        size -= 1;
        return oldLast.item;
    }

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }

        LinkedNode p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Returns the item in the ith position. */
    private T getRecursive(LinkedNode p, int i) {
        if (i == 0) {
            return p.item;
        }
        return getRecursive(p.next, i - 1);
    }

    /** Gets the item at the given index using recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }
}
