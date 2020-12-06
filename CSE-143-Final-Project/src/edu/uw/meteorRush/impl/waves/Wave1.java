package edu.uw.meteorRush.impl.waves;

import java.awt.Graphics;

import edu.uw.meteorRush.common.Game;
import edu.uw.meteorRush.common.Vector2;
import edu.uw.meteorRush.impl.Main;
import edu.uw.meteorRush.impl.entities.AsteroidLarge;
import edu.uw.meteorRush.impl.scenes.GameScene;

public class Wave1 extends Wave {

	private static final double ENEMY_SPAWN_PERIOD = 0.5 / Main.difficulty;
	private static final int MAX_ENEMY_COUNT = (int) (20 * Main.difficulty);

	private int enemyCount;
	private double startTime;
	private double nextSpawnTime;

	public Wave1() {
		enemyCount = 0;
		startTime = Game.getInstance().getTime();
		nextSpawnTime = startTime + GameScene.FIRST_WAVE_WAIT_TIME;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void tick() {
		double currentTime = Game.getInstance().getTime();
		if (currentTime >= nextSpawnTime) {
			nextSpawnTime = nextSpawnTime + ENEMY_SPAWN_PERIOD;
			spawnEnemy();
		}
	}

	private void spawnEnemy() {
		enemyCount++;
		spawnLargeAsteroid();
		GameScene scene = (GameScene) Game.getInstance().getOpenScene();
		if (enemyCount > MAX_ENEMY_COUNT) {
			scene.removeObject(this);
			scene.addObject(new Wave2());
		}
	}

	private void spawnLargeAsteroid() {
		GameScene scene = (GameScene) Game.getInstance().getOpenScene();
		Vector2 position = new Vector2(Main.WIDTH + 100, Main.HEIGHT * Math.random());
		Vector2 direction = new Vector2(-1, Math.random() - 0.5);
		AsteroidLarge enemy = new AsteroidLarge(position, direction);
		scene.addObject(enemy);
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public void dispose() {
	}

}
