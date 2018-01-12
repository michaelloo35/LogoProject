package pl.edu.agh.to2.dziki.utils;

import java.util.ArrayList;
import java.util.List;

public final class EvictingList<E> {

    private final int maxCapacity;
    private final List<E> container;

    public EvictingList(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.container = new ArrayList<>();
    }

    public EvictingList(int maxCapacity, int initialCapacity) {
        if (maxCapacity < initialCapacity) {
            throw new IllegalArgumentException("Illegal Capacity, maxCapacity must be greater than or equal initialCapacity");
        }
        this.maxCapacity = maxCapacity;
        this.container = new ArrayList<>(initialCapacity);
    }

    /**
     * Adds the given element to this List. If the list is currently full, the element at the head
     * of the list is evicted to make room.
     *
     * @return index of element
     */
    public int add(E element) {
        if (container.size() >= maxCapacity)
            container.remove(0);

        container.add(element);
        return container.size() - 1;
    }

    public int size() {
        return container.size();
    }

    /**
     * @param index
     * @return null if index out of bounds
     */
    public E get(int index) {
        try {
            return container.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean isEmpty() {
        return container.isEmpty();
    }

}

