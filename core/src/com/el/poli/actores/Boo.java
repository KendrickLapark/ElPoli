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


public class Boo extends Personaje {


    private float posOriginal;

    public Boo(World w, String rutaTextura) {
        super(w, rutaTextura);

    }


    public Boo(World w, String rutaTextura, float x, float y) {
        super(w, rutaTextura, x, y);
        posOriginal = this.getCuerpo().getPosition().x;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    public void patrullar() {


        System.out.println("la equis de los cojones: "+);

        /*if(this.getCuerpo().getPosition().x>10 && this.getCuerpo().getPosition().x<13 && ida == true){
            this.getCuerpo().setLinearVelocity(3,0);
        }
        if(this.getCuerpo().getPosition().x>13){
            ida = false;
            this.getCuerpo().setLinearVelocity(-3,0);
        }*/

        if(this.getCuerpo().getPosition().x==posOriginal){
            this.getCuerpo().setLinearVelocity(3,0);
        }

        if(this.getCuerpo().getPosition().x>20){
            this.getCuerpo().setLinearVelocity(-3,0);
        }


    }





}


