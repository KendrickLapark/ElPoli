package com.el.poli.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.el.poli.objetos.ObjetoJuego;

import java.util.HashSet;

public class Vegeta extends Personaje {

    private OrthographicCamera camara; //Cámara que renderiza al jugado
    private boolean colision;
    private int vidas;

    public Vegeta(World w, String rutaTextura) {
        super(w, rutaTextura);
    }

    public Vegeta(World w, String rutaTextura, float x, float y) {
        super(w, rutaTextura, x, y);
        vidas = 5;
    }

    public OrthographicCamera getCamara(){
        return this.camara;
    }



}
