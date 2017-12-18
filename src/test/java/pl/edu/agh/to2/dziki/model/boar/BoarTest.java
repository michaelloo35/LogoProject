package pl.edu.agh.to2.dziki.model.boar;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.mockito.Mockito.mock;

public class BoarTest {

    private static final double EPSILON = 0.000001;
    private Boar boar;

    @Before
    public void setUp() throws Exception {

        boar = new Boar(mock(ViewUpdater.class));
    }

    @Test
    public void shouldInitializeCorrectly() {
        // given

        // when
        boar.initialize();

        //then
        assertThat(boar.getPosition().getX()).isEqualTo(0.0, offset(EPSILON));
        assertThat(boar.getPosition().getY()).isEqualTo(0.0, offset(EPSILON));
        assertThat(boar.getPosition().getRotation()).isEqualTo(0.0, offset(EPSILON));
    }

    @Test
    public void shouldRotateCorrectly() {
        // given
        boar.initialize();

        // when
        boar.rotate(360);   // 0
        boar.rotate(360);   // 0
        boar.rotate(30);    // 30
        boar.rotate(30.5);  // 60.5
        boar.rotate(10.3);  // 70.8
        boar.rotate(99.999);// 170.799
        boar.rotate(99.1);  // 269.899
        boar.rotate(-19.1); // 250.799
        boar.rotate(-360);  // 250.799
        boar.rotate(730);   // 260.799

        // then
        assertThat(boar.getPosition().getRotation()).isEqualTo(260.799, offset(EPSILON));

    }

    @Test
    public void shouldMoveForward() {
        // given
        boar.initialize();

        // when
        boar.moveForward(0);        // 0
        boar.moveForward(0);        // 0
        boar.moveForward(0.99999);  // 0.99999
        boar.moveForward(00.3232);  // 1.32319
        boar.moveForward(54);       // 55.32319
        boar.moveForward(100);      // 155.32319
        boar.moveForward(345.321);  // 500.64419
        boar.moveForward(43.2);     // 543.84419
        boar.moveForward(0);        // 543.84419
        boar.moveForward(1);        // 544.84419
        boar.moveForward(1);        // 545.84419

        // then
        assertThat(boar.getPosition().getX()).isEqualTo(545.84419, offset(EPSILON));

    }

    @Test
    public void moveBackward() {
    }

    @Test
    public void setPosition() {
    }

    @Test
    public void getPosition() {
    }

    @Test
    public void lift() {
    }

    @Test
    public void lower() {
    }
}