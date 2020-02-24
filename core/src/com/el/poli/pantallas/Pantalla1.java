package com.el.poli.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.el.poli.Juego;
import com.el.poli.actores.Vegeta;

public class Pantalla1 extends BasePantalla {

    private Stage stage;

    private Vegeta vegeta;

    private Texture texturaVegeta;



    public Pantalla1(Juego game) {
        super(game);
        texturaVegeta = new Texture("personajes/vegira.png");
    }

    @Override
    public void show() {

        stage = new Stage();
        vegeta = new Vegeta(texturaVegeta);
        stage.addActor(vegeta);

        vegeta.setPosition(0,0);
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        texturaVegeta.dispose();
    }
}
