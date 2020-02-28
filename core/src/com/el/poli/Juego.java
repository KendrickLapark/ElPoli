package com.el.poli;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.el.poli.pantallas.PantallaJuego;

public class Juego extends Game {


    public SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new PantallaJuego(this));

    }

    @Override
    public void dispose () {
        super.render();
    }

}

