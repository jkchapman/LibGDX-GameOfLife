package com.jacobkchapman.gameoflife.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jacobkchapman.gameoflife.GameOfLife;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Conway's Game of Life";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new GameOfLife(), config);
	}
}
