package com.el.poli.objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class ObjetoJuego extends Actor {

    protected Sprite sprite; //Sprite que simboliza al actor
    private World mundo; //Mundo de nuestro juego
    private BodyDef propiedadesCuerpo; //Definidor de las propiedades del body
    private Body cuerpo; //Cuerpo del objeto
    private FixtureDef propiedadesFisicasCuerpo;//Definidor de las propiedades físicas del body


    public ObjetoJuego (World m,String ruta, float x, float y){
        mundo=m;
        sprite=new Sprite(new Texture(ruta));
        int anchuraSprite=1; //Anchura y altura
        int alturaSprite=1;//Anchura y altura
        sprite.setBounds(x,y,anchuraSprite,alturaSprite); //establecemos la posicion inicial y las dimensiones del sprite

        this.propiedadesCuerpo= new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.StaticBody; //Cuerpo de tipo estatico seleccionado
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo);//Creamos el cuerpo

        propiedadesFisicasCuerpo = new FixtureDef(); //Definimos la forma del cuerpo
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        propiedadesFisicasCuerpo.isSensor=true;
        ((PolygonShape)propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite/2f, alturaSprite/2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);

        sprite.setOrigin(this.sprite.getWidth()/2, this.sprite.getHeight()/2); //posicion original

    }

    public Rectangle getHitBox(){
        return sprite.getBoundingRectangle();
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.setPosition(cuerpo.getPosition().x-sprite.getWidth()/2,cuerpo.getPosition().y-sprite.getHeight()/2);
        sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());
        sprite.draw(batch);
    }

    public float getX(){
        return this.cuerpo.getPosition().x;
    }

    public float getY(){
        return this.cuerpo.getPosition().y;
    }

    public Body getCuerpo(){
        return cuerpo;
    }

}
