package com.el.poli;

import com.badlogic.gdx.Game;

import com.el.poli.pantallas.PantallaJuego;


public class Juego extends Game{

	@Override
	public void create() {

		setScreen(new PantallaJuego(this));
	}

	@Override
	public void render() {

		super.render();

	}

}

