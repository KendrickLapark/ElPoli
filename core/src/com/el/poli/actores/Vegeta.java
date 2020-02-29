package com.el.poli.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.el.poli.pantallas.PantallaJuego;

public class Vegeta extends Actor {

    public World world;
    public Body body;
    public Sprite sprite;

    public Vegeta(World w){
        this.world = w;
        BodyDef bDef = new BodyDef();

        sprite = new Sprite(new Texture("personajes/vegira.png"));

        bDef.position.set(32,32);
        bDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bDef);

        FixtureDef fixDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5) ;

        fixDef.shape = shape;
        sprite.setBounds(this.body.getPosition().x/2,this.body.getPosition().y/2,1,1);
        body.createFixture(fixDef);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
