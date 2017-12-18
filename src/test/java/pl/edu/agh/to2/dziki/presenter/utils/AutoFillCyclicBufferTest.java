package pl.edu.agh.to2.dziki.presenter.utils;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class AutoFillCyclicBufferTest {

    private AutoFillCyclicBuffer buffer;

    @Before
    public void setUp() throws Exception {
        buffer = new AutoFillCyclicBuffer();
    }

    @Test
    public void shouldReturnFirstElement() {

        // given

        buffer.setBuffer(asList("0", "1", "2", "3", "4", "5", "6", "7"));

        // when

        int i = 0;
        String expectedValue = String.valueOf(i % buffer.size());

        // then

        assertThat(buffer.getNext()).isEqualTo(expectedValue);
    }

    @Test
    public void shouldReturnProperElement() {

        // given

        buffer.setBuffer(asList("0", "1", "2", "3", "4", "5", "6", "7"));

        // when

        int i;
        for (i = 0; i < 433; i++)
            buffer.getNext();

        String expectedValue = String.valueOf(i % buffer.size());

        // then

        assertThat(buffer.getNext()).isEqualTo(expectedValue);
    }

    @Test
    public void shouldReturnProperElementAfterNTimesSizeCalls() {

        // given

        buffer.setBuffer(asList("0", "1", "2", "3", "4", "5", "6", "7"));

        // when

        int i;
        int max = 111 * buffer.size();
        for (i = 0; i < max; i++)
            buffer.getNext();

        String expectedValue = String.valueOf(i % buffer.size());

        // then

        assertThat(buffer.getNext()).isEqualTo(expectedValue);
    }

    @Test
    public void shouldReturnProperElementAfterHighNumberOfCalls() {

        // given

        buffer.setBuffer(asList("0", "1", "2", "3", "4", "5", "6", "7"));

        // when

        int i;
        for (i = 0; i < 48585384; i++)
            buffer.getNext();

        String expectedValue = String.valueOf(i % buffer.size());

        // then

        assertThat(buffer.getNext()).isEqualTo(expectedValue);
    }

    @Test
    public void shouldReturnProperElementOnEmptyBuffer() {
        // given

        buffer.setBuffer(emptyList());

        // when

        String result = buffer.getNext();

        // then
        assertThat(result).isEqualTo(null);
    }
}