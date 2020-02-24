package com.el.poli.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Vegeta extends Actor {

    private Texture vegeta;

    public Vegeta(Texture vegeta){
        this.vegeta = vegeta;
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(vegeta, getX(), getY());
    }
}
