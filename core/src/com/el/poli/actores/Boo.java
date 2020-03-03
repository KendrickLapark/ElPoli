package com.el.poli.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

import java.sql.Time;


public class Boo extends Personaje {

    private float posOriginal;
    private int cont;
    private boolean ida;

    public Boo(World w, String rutaTextura) {
        super(w, rutaTextura);

    }

    public Boo(World w, String rutaTextura, float x, float y) {
        super(w, rutaTextura, x, y);
        ida = true;

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(this.getCuerpo().getPosition().x>10){
            this.getCuerpo().setLinearVelocity(-3,0);
        }

    }

    public void patrullar() {

        System.out.println("la equis de boo: "+this.getCuerpo().getPosition().x+" Ida: "+ida);

        if(this.getCuerpo().getPosition().x<150 && ida == true){
            this.getCuerpo().setLinearVelocity(3,0);
        }else{
            ida = false;
        }

        if(this.getCuerpo().getPosition().x>140 && ida == false){
            this.getCuerpo().setLinearVelocity(-3,0);
        }else{
            ida = true;
        }

    }

}


