package com.el.poli;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.Screen;
import com.el.poli.basededatos.BaseDeDatos;
import com.el.poli.pantallas.PantallaJuego;


public class Juego extends Game{

	Screen pantallaActual;
	public BaseDeDatos baseDeDatos;

	public Juego(BaseDeDatos bd){
		baseDeDatos = bd;
	}

	@Override
	public void create() {

		setScreen(new PantallaJuego(this, baseDeDatos));
	}

	@Override
	public void render() {

		super.render();
	}

	public void setPantallaActual(Screen pa){
		pantallaActual=pa;
		setScreen(pantallaActual);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}

