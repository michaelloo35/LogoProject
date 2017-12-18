package pl.edu.agh.to2.dziki.model.boar;

import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.dziki.presenter.ViewUpdater;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.*;
import static org.mockito.Mockito.mock;

public class BoarTest {

    private static final double EPSILON = 0.001;
    private Boar boar;

    @Before
    public void setUp() throws Exception {

        boar = new Boar(mock(ViewUpdater.class));
    }

    @Test
    public void initialize() {
        // given

        // when
        boar.initialize();

        //then

        assertThat(boar.getPosition().getX()).isEqualTo(0.0, offset(EPSILON));
        assertThat(boar.getPosition().getY()).isEqualTo(0.0, offset(EPSILON));
        assertThat(boar.getPosition().getRotation()).isEqualTo(0.0, offset(EPSILON));
    }

    @Test
    public void rotate() {
    }

    @Test
    public void hide() {
    }

    @Test
    public void show() {
    }

    @Test
    public void moveForward() {
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