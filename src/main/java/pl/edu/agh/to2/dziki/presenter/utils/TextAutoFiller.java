package pl.edu.agh.to2.dziki.presenter.utils;

import java.util.Set;
import java.util.stream.Collectors;

public class TextAutoFiller {

    private final Set<String> dictionary;
    private final AutoFillCyclicBuffer buffer;

    public TextAutoFiller(Set<String> dictionary) {
        this.dictionary = dictionary
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toSet());

        buffer = new AutoFillCyclicBuffer();
    }

    /**
     * If buffer contains elements returns next match else fills the buffer and returns first match
     *
     * @return null if there are no matches
     */
    public String fillOrGetNextMatch(String word) {

        word = word.toUpperCase();
        if (buffer.contains(word))
            return buffer.getNext();
        else {
            matchPrefix(word);
            return buffer.getNext();
        }
    }

    /**
     * Updates buffer with elements from dictionary matching prefix
     */
    private void matchPrefix(String prefix) {
        buffer.setBuffer(
                dictionary
                        .parallelStream()
                        .filter(s -> s.startsWith(prefix))
                        .collect(Collectors.toList()));
    }


}
