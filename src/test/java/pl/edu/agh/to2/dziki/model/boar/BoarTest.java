package pl.edu.agh.to2.dziki.model.boar;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class BoarTest {

    private static final double EPSILON = 0.000001;
    private Boar boar;

    @Before
    public void setUp() throws Exception {

        boar = new Boar();
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
        // given
        boar.initialize();

        // when
        boar.moveBackward(0);           // 0
        boar.moveBackward(0);           // 0
        boar.moveBackward(0.4032);      // -0.4032
        boar.moveBackward(000.111139);  // -0.514339
        boar.moveBackward(22);          // -22.514339
        boar.moveBackward(100);         // -122.514339
        boar.moveBackward(49.321);      // -171.835339
        boar.moveBackward(12.2);        // -184.035339
        boar.moveBackward(0);           // -184.035339
        boar.moveBackward(1);           // -185.035339
        boar.moveBackward(1);           // -186.035339

        // then
        assertThat(boar.getPosition().getX()).isEqualTo(-186.035339, offset(EPSILON));

    }

    @Test
    public void shouldSetCorrectPosition() {

        // given
        boar.initialize();

        // when

        double x = 134.3;
        double y = -0.234;
        int angle = -50;

        boar.setCurrentPosition(x, y, angle);

        // normalization
        angle %= 360;
        if (angle < 0)
            angle = 360 + angle;

        // then
        assertThat(boar.getPosition().getX()).isEqualTo(x, offset(EPSILON));
        assertThat(boar.getPosition().getY()).isEqualTo(y, offset(EPSILON));
        assertThat(boar.getPosition().getRotation()).isEqualTo(angle, offset(EPSILON));

    }


    @Test
    public void shouldLift() {

        // given
        boar.initialize();

        // when
        boar.lower();
        boar.lower();
        boar.lift();
        boar.lower();
        boar.lift();

        // then
        assertThat(boar.isLift()).isTrue();

    }

    @Test
    public void shouldLower() {

        // given
        boar.initialize();

        // when
        boar.lower();
        boar.lower();
        boar.lift();
        boar.lower();
        boar.lift();
        boar.lift();
        boar.lower();

        // then
        assertThat(boar.isLift()).isFalse();

    }

    @Test
    public void shouldBeLoweredOnInitialize() {

        // given
        boar.initialize();

        // when

        // then
        assertThat(boar.isLift()).isFalse();

    }

    @Test
    public void shouldPerformCorrectComplexMovement() {

        // given
        boar.initialize();
        double x = 0.0;
        double y = 0.0;
        double rotation = 0.0;

        // when
        boar.moveForward(100);
        x += 100;
        boar.moveBackward(50);
        x -= 50;
        boar.rotate(90);
        rotation += 90;
        boar.moveForward(100);
        y += 100;
        boar.moveBackward(50);
        y -= 50;
        boar.rotate(-45);
        rotation -= 45;
        boar.moveForward(100);
        x += 100 * Math.cos(Math.toRadians(45));
        y += 100 * Math.sin(Math.toRadians(45));
        boar.moveBackward(50);
        x -= 50 * Math.cos(Math.toRadians(45));
        y -= 50 * Math.sin(Math.toRadians(45));

        // then
        assertThat(boar.getPosition().getX()).isEqualTo(x, offset(EPSILON));
        assertThat(boar.getPosition().getY()).isEqualTo(y, offset(EPSILON));
        assertThat(boar.getPosition().getRotation()).isEqualTo(rotation, offset(EPSILON));

    }
}