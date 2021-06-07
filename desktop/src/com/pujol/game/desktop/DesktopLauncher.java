package com.pujol.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pujol.game.RecuGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 640; //Medidas de pantalla predeterminadas
		config.width = 360;
		new LwjglApplication(new RecuGame(), config);
	}
}
