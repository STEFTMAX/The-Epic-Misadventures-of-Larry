package com.sessionstraps.larrys_epic_misadventures;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.HashSet;

import javax.swing.JFrame;

import com.sessionstraps.game_engine.entity.MovingEntity;

public class GameDrawer extends Canvas {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private HashSet<MovingEntity> tempEnts;

	private Graphics offgc;
	private Image offscreen = null;

	public GameDrawer() {

		Dimension size = new Dimension(Game.WIDTH, Game.HEIGHT);
		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);

		frame = new JFrame(Game.NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		offscreen = createImage(getSize().width, getSize().height);
	}

	public void draw(HashSet<MovingEntity> entities) {
		tempEnts = entities;
		repaint();

	}

	public void update(Graphics g) {

		offgc = offscreen.getGraphics();
		offgc.setColor(getBackground());
		offgc.fillRect(0, 0, getSize().width, getSize().height);
		offgc.setColor(getForeground());
		paint(offgc);
		g.drawImage(offscreen, 0, 0, this);
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

}
