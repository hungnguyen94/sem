package com.sem.btrouble;

import java.util.List;

import com.sem.btrouble.model.Bubble;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class which tests the Bubble class.
 *
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BubbleTest {

    private Bubble bubble1 = new Bubble(1, 1, 1);
    private Bubble bubble2 = new Bubble(2, 2, 2, 2, 2);

    /**
     * Test the getSize method of a bubble made from the constructor with three
     * arguments.
     */
    @Test
    public void getSize1Test() {
        assertEquals(1, bubble1.getSize());
    }

    /**
     * Test the getSize method of a bubble made from the constructor with five
     * arguments.
     */
    @Test
    public void getSize2Test() {
        assertEquals(2, bubble2.getSize());
    }

    /**
     * Test the getVelocityX method of a bubble made from the constructor with three
     * arguments.
     */
    @Test
    public void getVelocityX1Test() {
        assertEquals(3f, bubble1.getVelocityX(), 0);
    }

    /**
     * Test the getVelocityX method of a bubble made from the constructor with five
     * arguments.
     */
    @Test
    public void getVelocityX2Test() {
        assertEquals(2, bubble2.getVelocityX(), 0);
    }

    /**
     * Test the getVelocityY method of a bubble made from the constructor with three
     * arguments.
     */
    @Test
    public void getVelocityY1Test() {
        assertEquals(0, bubble1.getVelocityY(), 0);
    }

    /**
     * Test the getVelocityY method of a bubble made from the constructor with five
     * arguments.
     */
    @Test
    public void getVelocityY2Test() {
        assertEquals(2, bubble2.getVelocityY(), 0);
    }

    /**
     * Tests the equals method with two equal objects.
     */
    @Test
    public void equalsTrueTest() {
        assertTrue(bubble1.equals(bubble1));
    }

    /**
     * Tests the equals method with a wrong size.
     */
    @Test
    public void equalsFalseSizeTest() {
        assertFalse(bubble1.equals(new Bubble(2, 1, 1)));
    }

    /**
     * Tests the equals method with a wrong x.
     */
    @Test
    public void equalsFalseXTest() {
        assertFalse(bubble1.equals(new Bubble(1, 2, 1)));
    }

    /**
     * Tests the equals method with a wrong y.
     */
    @Test
    public void equalsFalseYTest() {
        assertFalse(bubble1.equals(new Bubble(1, 1, 2)));
    }

    /**
     * Tests the equals method with a wrong vx.
     */
    @Test
    public void equalsFalseVelocityXTest() {
        assertFalse(bubble2.equals(new Bubble(2, 2, 2, 1, 2)));
    }

    /**
     * Tests the equals method with a wrong vy.
     */
    @Test
    public void equalsFalseVelocityYTest() {
        assertFalse(bubble2.equals(new Bubble(2, 2, 2, 2, 1)));
    }

    /**
     * Tests the equals method with another type.
     */
    @Test
    public void equalsOtherTest() {
        String string = new String("bubble");
        assertFalse(bubble1.equals(string));
    }

    /**
     * Test the setAy method.
     */
    @Test
    public void setAccelerationYTest() {
        bubble1.setAccelerationY(1);
        assertEquals(1, bubble1.getAccelerationY(), 0);
    }

    /**
     * Test the bounceY method.
     */
    @Test
    public void bounceYTest() {
        double vy = bubble1.getVelocityY();
        bubble1.bounceY();
        assertEquals(-vy, bubble1.getVelocityY(), 0);
    }

    /**
     * Tests the bounceX method.
     */
    @Test
    public void bounceXTest() {
        double vx = bubble1.getVelocityX();
        bubble1.bounceX();
        assertEquals(-vx, bubble1.getVelocityX(), 0);
    }

    /**
     * Test the bounceX method with parameter true.
     */
    @Test
    public void bounceXTrueTest() {
        double vx = bubble1.getVelocityX();
        bubble1.bounceXLeft();
        assertEquals(-vx, bubble1.getVelocityX(), 0);
    }

    /**
     * Test the bounceX method with parameter false.
     */
    @Test
    public void bounceXFalseTest() {
        double vx = bubble1.getVelocityX();
        bubble1.bounceXRight();
        assertEquals(vx, bubble1.getVelocityX(), 0);
    }

    /**
     * Test the bounceY method with parameter true.
     */
    @Test
    public void bounceYTrueTest() {
        double vy = bubble1.getVelocityY();
        bubble1.bounceYUp();
        assertEquals(-vy, bubble1.getVelocityY(), 0);
    }

    /**
     * Test the bounceY method with parameter false.
     */
    @Test
    public void bounceYFalseTest() {
        double vy = bubble1.getVelocityY();
        bubble1.bounceYDown();
        assertEquals(vy, bubble1.getVelocityY(), 0);
    }

    /**
     * Tests the bounceYFloor method.
     */
    @Test
    public void bounceYFloorTest() {
        bubble1.bounceYFloor();
        assertEquals(-Math.abs(11 + 2 * (bubble1.getSize())), bubble1.getVelocityY(), 0);
    }

    /**
     * Tests the toString method.
     */
    @Test
    public void toStringTest() {
        assertEquals("Bubble{size=1, x=-9.0, y=-9.0, velocityX=3.0, velocityY=0.0, accelerationY=0.4}", bubble1.toString());
    }

    /**
     * Tests the moveX method.
     */
    @Test
    public void moveXTest() {
        double x = bubble1.getCenterX() + bubble1.getVelocityX();
        bubble1.move();
        assertEquals(x - 10, bubble1.getX(), 0);
    }

    /**
     * Tests the moveY method.
     */
    @Test
    public void moveYTest() {
        double y = bubble1.getCenterY() + bubble1.getVelocityY() + .4f;
        bubble1.move();
        assertEquals(y - 10, bubble1.getY(), 0.000001);
    }
    
    @Test
    public void splitZeroTest() {
    	List<Bubble> list = bubble1.split();
    	assertTrue(list.isEmpty());
    }
    
    @Test
    public void splitTest() {
    	double velocityX = bubble2.getVelocityX();
    	List<Bubble> list = bubble2.split();
    	assertEquals(-Math.abs(bubble2.getAccelerationY()) * 30, bubble2.getVelocityY(), 0);
    	assertEquals(Math.abs(velocityX), bubble2.getVelocityX(), 0);
    	Bubble leftBubble = new Bubble(bubble2.getSize(), bubble2.getX() - 20, bubble2.getY(), 
    			(float) -bubble2.getVelocityX(), (float) bubble2.getVelocityY());
    	Bubble rightBubble = new Bubble(bubble2.getSize(), bubble2.getX() + 20, bubble2.getY(), 
    			(float) bubble2.getVelocityX(), (float) bubble2.getVelocityY());
    	assertTrue(list.contains(leftBubble) && list.contains(rightBubble));
    }

}
