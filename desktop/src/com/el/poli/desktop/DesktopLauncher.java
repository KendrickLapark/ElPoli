package com.el.poli.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.el.poli.Juego;
import com.el.poli.basededatos.BaseDeDatos;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Juego(new BaseDeDatos() {
			@Override
			public int cargar() {
				return 0;
			}

			@Override
			public void guardar(int puntuacion) {

			}
		}), config);
	}
}
