package deque;

/**
 * @author Yijing Zhou
 */
public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty LinkedListDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /** Adds an item to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length - 1) {
            resize((items.length * 3) / 2 + 1, nextFirst);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    /** Adds an item to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length - 1) {
            resize((items.length * 3) / 2 + 1, nextLast);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
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

        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast; i += 1) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < items.length; i += 1) {
                System.out.print(items[i] + " ");
            }
            for (int j = 0; j < nextLast; j += 1) {
                System.out.print(items[j] + " ");
            }
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
        if ((double) size / (double) items.length < 0.25 && items.length >= 16) {
            resize((size * 3) / 2 + 1, nextFirst);
        }
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
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
        if ((double) size / (double) items.length < 0.25 && items.length >= 16) {
            resize((size * 3) / 2 + 1, nextFirst);
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        T t = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return t;
    }

    /**
     * Gets the item at the given index.
     * If no such item exists, returns null.
     */
    public T get(int index) {
        if (size == 0 || index >= size || index < 0) {
            return null;
        }

        if (nextFirst < nextLast) {
            return items[nextFirst + index + 1];
        } else {
            if (nextFirst + index + 1 < items.length) {
                return items[nextFirst + index + 1];
            } else {
                return items[nextFirst + index - items.length + 1];
            }
        }
    }

    /** Returns the resized ArrayDeque */
    public void resize(int capacity, int index) {
        T[] newItems = (T[]) new Object[capacity];

        // increase
        if (capacity > items.length) {
            System.arraycopy(items, index + 1, newItems, capacity / 4, size - index);
            System.arraycopy(items, 0, newItems, capacity / 4 + size - index, index);
        } else {
            // decrease
            System.arraycopy(items, index + 1, newItems, capacity / 4, size);
        }

        nextFirst = capacity / 4 - 1;
        nextLast = capacity / 4 + size;
        items = newItems;
    }
}
