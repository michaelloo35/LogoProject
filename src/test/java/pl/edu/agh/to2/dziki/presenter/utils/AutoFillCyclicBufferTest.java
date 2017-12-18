package pl.edu.agh.to2.dziki.presenter.utils;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class AutoFillCyclicBufferTest {

    private AutoFillCyclicBuffer buffer;

    @Before
    public void setUp() throws Exception {
        buffer = new AutoFillCyclicBuffer();
    }

    @Test
    public void getNext() {

        // given

        buffer.setBuffer(asList("0", "1", "2", "3", "4", "5", "6", "7"));

        // when

        int i;
        for (i = 0; i < 10; i++) {
            buffer.getNext();
        }

        // after n-th call we should receive n% buffer size element in buffer therefore we have to decrement i by one to get matching index
        String expectedValue = String.valueOf((i-1) % buffer.size());

        // then

        assertThat(buffer.getNext()).isEqualTo(expectedValue);
    }

}