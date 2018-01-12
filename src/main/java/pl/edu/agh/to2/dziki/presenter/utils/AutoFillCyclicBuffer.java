package pl.edu.agh.to2.dziki.presenter.utils;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AutoFillCyclicBuffer {

    private List<String> buffer;
    private int currentIndex = 0;

    public AutoFillCyclicBuffer() {
        buffer = new ArrayList<>();
    }

    /**
     * @return null if buffer is empty
     */
    public String getNext() {
        if (buffer.size() == 0)
            return null;
        String element = buffer.get(currentIndex);
        currentIndex = (currentIndex + 1) % buffer.size();
        return element;
    }

    public void setBuffer(@NotNull List<String> elements) {
        this.buffer = elements;
        this.currentIndex = 0;
    }

    public boolean contains(String element) {
        return buffer.contains(element);
    }

    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    public int size() {
        return buffer.size();
    }
}
