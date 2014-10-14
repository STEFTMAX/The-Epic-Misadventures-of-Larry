package com.sessionstraps.larrys_epic_misadventures;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashSet;

import com.sessionstraps.game_engine.entity.Entity;
import com.sessionstraps.game_engine.render.Drawable;
import com.sessionstraps.game_engine.render.GameWindow;

public class GameDrawer {
	
	private GameWindow gs;
	
	private HashSet<Entity> tempEnts;
/*
	private JFrame frame;
	private Canvas canvas;

	private Graphics offgc;
	private Image offscreen = null;*/

	public GameDrawer() {
		
		
		gs = new GameWindow(Game.WIDTH, Game.HEIGHT, Game.NAME);
		/*
		canvas = new Canvas() {
			@Override
			public void update(Graphics g) {

				offgc = offscreen.getGraphics();
				offgc.setColor(canvas.getBackground());
				offgc.fillRect(0, 0, canvas.getSize().width,
						canvas.getSize().height);
				offgc.setColor(canvas.getForeground());
				paint(offgc);
				g.drawImage(offscreen, 0, 0, canvas);
			}

			@Override
			public void paint(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				g2.translate(0, 0);
				if (tempEnts != null) {
					for (MovingEntity ent : tempEnts) {
						if (ent instanceof Drawable) {
							((Drawable) ent).draw(g2);
						}
					}
				}
			}
		};

		Dimension size = new Dimension(Game.WIDTH, Game.HEIGHT); // works for
																	// some
																	// reason
		canvas.setMaximumSize(size);
		canvas.setMinimumSize(size);
		canvas.setPreferredSize(size);

		frame = new JFrame(Game.NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout(1, 1));

		frame.add(canvas, BorderLayout.CENTER);
		frame.setUndecorated(true);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().setFullScreenWindow(frame);
		System.out.println(canvas.getWidth() + " " + canvas.getHeight());
		System.out.println(frame.getWidth() + " " + frame.getHeight());
		System.out.println(Game.WIDTH + " " + Game.HEIGHT);

		offscreen = canvas.createImage(canvas.getSize().width,
				canvas.getSize().height);
		*/
	}

	public void draw(HashSet<Entity> entities) {
		tempEnts = entities;
		//canvas.repaint();
		Graphics2D g2 = (Graphics2D) gs.getDrawingGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.translate(0, 0);
		if (tempEnts != null) {
			for (Entity ent : tempEnts) {
				if (ent instanceof Drawable) {
					((Drawable) ent).draw(g2);
				}
			}
		}
		gs.updateScreen(g2);
	}

	public Component getCanvas() {
		return gs;
	}

}
