package com.sessionstraps.larrys_epic_misadventures;

import com.sessionstraps.larrys_epic_misadventures.manager.EntityManager;

public class GameUpdater implements Runnable {

	private GameRenderer renderer;
	private GameDrawer drawer;
	private EntityManager entityManager;
	private boolean stop = false;
	private double timeScale = 1;

	public GameUpdater(GameRenderer gameRenderer, GameDrawer gameDrawer,
			EntityManager entityManager) {
		this.renderer = gameRenderer;
		this.drawer = gameDrawer;
		this.entityManager = entityManager;
	}

	@Override
	public void run() {
		long lastNanoTime = System.nanoTime();
		long thisTime = lastNanoTime;
		long delta = 0;
		long sleepTime = 0;

		while (!stop) {

			renderer.render(delta, entityManager.getEntities());
			drawer.draw(entityManager.getEntities());

			thisTime = System.nanoTime();

			delta = (long) ((thisTime - lastNanoTime) * timeScale);
			lastNanoTime = thisTime;
			sleepTime = (long) ((1000f / Game.MAXFPS) - ((float) delta / 1000000f));
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		stop = false;
	}

	public synchronized void stop() {
		stop = true;
	}

}
