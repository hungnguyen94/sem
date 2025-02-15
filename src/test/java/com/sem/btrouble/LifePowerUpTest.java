package com.sem.btrouble;

import com.sem.btrouble.model.LifePowerUp;
import com.sem.btrouble.model.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Test the Life Powerup.
 * @author Martin
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LifePowerUpTest extends PowerUpTest {

    @Mock private Player player;
    private LifePowerUp power;
    
    /**
     * Set up the power up.
     */
    @Before
    public void setUp() {
        power = new LifePowerUp(1, 1);
        setPower(power);
    }
    
    /**
     * Test the activate method.
     */
    @Test
    public void activateTest() {
        power.activate(player);
        verify(player).addLife();
    }
    
    /**
     * Test the reset method.
     */
    @Test
    public void resetTest() {
        power.reset();
        verifyNoMoreInteractions(player);
    }
    
    /**
     * Test the other constructor.
     * Used suppressWarning because life has to made to pass the test.
     */
    @Test
    public void constructTest() {
        @SuppressWarnings("unused")
        LifePowerUp life = new LifePowerUp();
//        verify(player).addLife();
    }
    
    @Test
    public void equalsTrueTest() {
        assertTrue(power.equals(power));
    }
    
    @Test
    public void equalsOtherTest() {
        assertFalse(power.equals(new String("power")));
    }
    
    @Test
    public void equalsFalseFallingTest() {
        LifePowerUp power2 = new LifePowerUp(1, 1);
        power2.setFalling(false);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseXTest() {
        LifePowerUp power2 = new LifePowerUp(2, 1);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseYTest() {
        LifePowerUp power2 = new LifePowerUp(1, 2);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseVelocityYTest() {
        LifePowerUp power2 = new LifePowerUp(1, 1);
        power2.setVelocityY(10);
        assertFalse(power.equals(power2));
    }
    
    @Test
    public void equalsFalseAccelerationYTest() {
        LifePowerUp power2 = new LifePowerUp(1, 1);
        power2.setAccelerationY(10);
        assertFalse(power.equals(power2));
    }

}
