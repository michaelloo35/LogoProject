package pl.edu.agh.to2.dziki.utils;

import java.util.*;
import java.util.function.Consumer;

public class UnmodifiableList<E> implements Iterable<E> {

    private final List<E> data;


    public UnmodifiableList(List<E> list) {
        this.data = new ArrayList<>(list);
    }

    public UnmodifiableList(Stack<E> stack) {
        this.data = new ArrayList<>(stack);
    }

    public E get(int i) {
        return data.get(i);
    }

    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    public int size() {
        return this.data.size();
    }

    @Override
    public Iterator iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        data.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return data.spliterator();
    }
}
