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

    private boolean ida;//Boolean para controlar el pathing del enemigo

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

    /*
    Método para que el actor patrulle una zona en el eje x
     */

    public void patrullar() {

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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(this.getCuerpo().getLinearVelocity().x>0){
            ida = true;
            this.sprite = new Sprite(new Texture("personajes/boo2.png"));
            this.sprite.setBounds(20,4,2,2);
        }else if(this.getCuerpo().getLinearVelocity().x<0){
            ida = false;
            this.sprite = new Sprite(new Texture("personajes/boo.png"));
            this.sprite.setBounds(20,4,2,2);
        }
    }
}


