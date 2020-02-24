package com.el.poli.pantallas;

import com.badlogic.gdx.Screen;
import com.el.poli.Juego;

public abstract class BasePantalla implements Screen {

    protected Juego game;

    public BasePantalla(Juego game){
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
