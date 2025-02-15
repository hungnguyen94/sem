package com.sem.btrouble.view;

import com.sem.btrouble.model.PlayerPowerUp;
import com.sem.btrouble.model.Player;
import com.sem.btrouble.model.PlayerInfo;
import com.sem.btrouble.model.StayRopePowerUp;
import com.sem.btrouble.model.LifePowerUp;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

import java.io.InputStream;
import java.util.List;

/**
 * Created by rubenwiersma on 22-09-15.
 */
public class ShopView extends BasicGameState {
    private Image background;
    private TrueTypeFont font;
    private int receiptBubbles = 0;
    private int receiptTime = 0;
    private int receiptLife = 0;
    private MouseOverArea ropeButton;
    private MouseOverArea timeButton;
    private MouseOverArea lifeButton;

    /**
     * Initialize method of the slick2d library.
     *
     * @param gc
     *            should be the GameContainer containing the game.
     * @param sbg
     *            the reference to the StateBasedGame.
     * @throws SlickException
     *             when the game could not be initialized.
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("Sprites/store1280x720.png");

        ropeButton = new MouseOverArea(gc, new Image("Sprites/bubbles_button.jpg"), 170, 80);
        timeButton = new MouseOverArea(gc, new Image("Sprites/time_button.jpg"), 187, 230);
        lifeButton = new MouseOverArea(gc, new Image("Sprites/life_button.jpg"), 154, 391);

        loadFont();
    }

    /**
     * Update method of the slick2d library.
     *
     * @param gc
     *            should be the GameContainer containing the game
     * @param sbg
     *            the reference to the StateBasedGame.
     * @param delta
     *            should be an integer representing the speed of the player
     * @throws SlickException
     *             when the controller could not be updated
     */
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_RETURN)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }

        int sum = 0;
        List<Player> players = PlayerInfo.getInstance().getPlayers();
        for(Player player: players) {
            sum += player.getWallet().getValue();
        }

        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (ropeButton.isMouseOver() && sum >= 2500) {
                int amount = (sum - 2500)/players.size();
                giveValue(amount);
                PlayerPowerUp powerup = new StayRopePowerUp(10000);
                for(Player player: players) {
                    powerup.activate(player);
                }
                receiptBubbles++;
            } else if (timeButton.isMouseOver() && sum >= 2500) {
                int amount = (sum - 2500)/players.size();
                giveValue(amount);
                receiptTime++;
            } else if (lifeButton.isMouseOver() && sum >= 10000) {
                int amount = (sum - 10000)/players.size();
                giveValue(amount);
                PlayerPowerUp powerup = new LifePowerUp();
                for(Player player: players) {
                    powerup.activate(player);
                }
                receiptLife++;
            }
        }
    }
    
    /**
     * Give the value to the players equally spread.
     * @param value The value to be given
     */
    public void giveValue(int value) {
        List<Player> players = PlayerInfo.getInstance().getPlayers();
        for(Player player: players) {
            player.getWallet().setValue(value);
        }
    }

    /**
     * Render method of the slick2d library.
     *
     * @param gc
     *            should be the GameContainer containing the game
     * @param sbg
     *            the reference to the StateBasedGame.
     * @param graphics
     *            should be the graphics handler of the game
     * @throws SlickException
     *             when an item could not be drawn.
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics graphics)
            throws SlickException {
        graphics.setFont(font);
        background.draw(0f, 0f);

        int sum = 0;
        List<Player> players = PlayerInfo.getInstance().getPlayers();
        for(Player player: players) {
            sum += player.getWallet().getValue();
        }
        graphics.drawString("" + sum, 70, 660);
        graphics.drawString("Press enter", 1000, 660);
        graphics.drawString("" + receiptBubbles, 1175, 520);
        graphics.drawString("" + receiptTime, 1175, 570);
        graphics.drawString("" + receiptLife, 1175, 620);
    }

    /**
     * Loads the game font into a TrueTypeFont object to be used by the setFont method.
     */
    private void loadFont() {
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Sprites/IndieFlower.ttf");

            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
                    inputStream);
            awtFont = awtFont.deriveFont(24f);
            font = new TrueTypeFont(awtFont, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the id of the view.
     * @return the id
     */
    public int getID() {
        return 3;
    }
}
