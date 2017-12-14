package pl.edu.agh.to2.dziki.presenter;

import pl.edu.agh.to2.dziki.utils.EvictingList;

public class InputHistory {

    private final EvictingList<String> historyStack;

    // This is the index of message that would be returned on getPrevious call
    private int currentIndex;


    public InputHistory(int size) {
        historyStack = new EvictingList<>(size);
    }

    /**
     * Adds the given message to the history. If the history is currently full, the oldest message
     * is removed to make a room.
     */
    public void add(String message) {
        // no need for same message that appeared 10 times in a row as a 10 messages in a history
        String lastMessage = historyStack.get(historyStack.size() - 1);
        if (lastMessage == null || !lastMessage.equals(message))
            currentIndex = historyStack.add(message);
    }

    /**
     * Will return previous element and move element pointer down
     *
     * @return null if empty or there is no previous element
     */
    public String getPrevious() {
        if (historyStack.isEmpty() || currentIndex < 0)
            return null;
        else
            return historyStack.get(currentIndex--);
    }

    /**
     * Will return next element and move element pointer up
     *
     * @return null if empty or no next element available
     */
    public String getNext() {

        if (historyStack.isEmpty())
            return null;

        String nextElement;
        if ((nextElement = historyStack.get(currentIndex + 2)) != null)
            currentIndex++;

        return nextElement;
    }
}
