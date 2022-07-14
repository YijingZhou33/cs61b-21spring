package deque;

/**
 * @author Yijing Zhou
 */
public class ArrayDeque<T> {
    private int size;
    private int capacity;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        capacity = items.length;
        nextFirst = capacity - 1;
        nextLast = 0;
        size = 0;
    }

    /**
     * Returns the index after adding one.
     * Used for addLast() and removeFirst().
     * */
    private int plusOne(int index) {
        if (index == capacity - 1) {
            index = 0;
        } else {
            index += 1;
        }
        return index;
    }

    /**
     * Returns the index after subtracting one.
     * Used for addFirst() and removeLast().
     * */
    private int minusOne(int index) {
        if (index == 0) {
            index = capacity - 1;
        } else {
            index -= 1;
        }
        return index;
    }

    /** Adds an item to the front of the deque. */
    public void addFirst(T item) {
        if (size == capacity - 1) {
            resize(capacity * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /** Adds an item to the back of the deque. */
    public void addLast(T item) {
        if (size == capacity - 1) {
            resize(capacity * 2);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
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
        if (isEmpty()) {
            return;
        }
        for (int i = nextFirst + 1; i < capacity; i += 1) {
            System.out.print(items[i] + " ");
        }
        for (int j = 0; j < nextLast; j += 1) {
            System.out.print(items[j] + " ");
        }
        System.out.println();
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (((double) size / (double) capacity) < 0.25 && capacity >= 16) {
            resize(capacity / 4);
        }
        nextFirst = plusOne(nextFirst);
        T t = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return t;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (((double) size / (double) capacity) < 0.25 && capacity >= 16) {
            resize(capacity / 4);
        }
        nextLast = minusOne(nextLast);
        T t = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return t;
    }

    /** Returns the index after circling. */
    private int modIndex(int index) {
        return (nextFirst + 1 + index) % this.capacity;
    }

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (size == 0 || index > size || index < 0) {
            return null;
        }
        int i = modIndex(index);
        return items[i];
    }

    /** Returns the resized ArrayDeque */
    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            newItems[i] = items[modIndex(i)];
        }
        nextFirst = capacity - 1;
        nextLast = size;
        this.capacity = capacity;
        items = newItems;
    }
}
