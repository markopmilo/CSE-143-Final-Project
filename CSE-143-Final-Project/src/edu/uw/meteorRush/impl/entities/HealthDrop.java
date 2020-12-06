package edu.uw.meteorRush.impl.entities;

import java.awt.Graphics;
import java.awt.Image;

import edu.uw.meteorRush.common.Game;
import edu.uw.meteorRush.common.ResourceLoader;
import edu.uw.meteorRush.common.Vector2;
import edu.uw.meteorRush.impl.Main;
import edu.uw.meteorRush.impl.PlayerFollowingText;

public class HealthDrop extends Drop {

	private static final double HEAL_AMOUNT = (1 / Main.difficulty);
	private static final double LIFETIME = 10.0;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private static final Image SPRITE = ResourceLoader.loadImage("res/images/entities/drops/HealthDrop.png")
			.getScaledInstance(WIDTH, HEIGHT, 0);

	public HealthDrop(Vector2 position) {
		super(position, new Vector2(WIDTH, HEIGHT), LIFETIME);
	}

	@Override
	public void onPickup(PlayerShip player) {
		player.heal(HEAL_AMOUNT);
		PlayerFollowingText text = new PlayerFollowingText("+" + (int) HEAL_AMOUNT + " HP");
		Game.getInstance().getOpenScene().addObject(text);
	}

	@Override
	public void render(Graphics g) {
		Vector2 position = getPosition();
		g.drawImage(SPRITE, (int) (position.getX() - WIDTH / 2.0), (int) (position.getY() - HEIGHT / 2.0), null);
	}

}
