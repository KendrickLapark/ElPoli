package com.el.poli.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.el.poli.objetos.ObjetoJuego;

public abstract class Personaje extends Actor {

    protected Sprite sprite; //Sprite que representa al personaje
    private World mundo; //Mundo de nuestro juego
    private BodyDef propiedadesCuerpo;//Propiedades del cuerpo
    private Body cuerpo;//Cuerpo del personaje
    private FixtureDef propiedadesFisicasCuerpo;//Propiedades físicas del personaje
    private boolean colision;//Boolean para detectar colision

    /**
     *
     * @param w mundo de nuestro del juego
     * @param rutaTextura
     * @param x posicion en el eje x del personaje
     * @param y posición en el eje y del personaje
     */

    public Personaje(World w, String rutaTextura,float x, float y){

        mundo = w;
        sprite = new Sprite(new Texture(rutaTextura));
        int anchuraSprite = 1; //Anchura y altura
        int alturaSprite = 1;//Anchura y altura
        sprite.setBounds(x, y,alturaSprite * 2, anchuraSprite * 2); // determinamos la posición inicial y la dimensión del sprite del personaje
        creaFisica();
        sprite.setOrigin(this.sprite.getWidth() / 2,this.sprite.getHeight() / 2);

    }

    /**
     * Método draw para dibujar el sprite del personaje
     */

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (sprite != null) {
            sprite.setPosition(cuerpo.getPosition().x - sprite.getWidth() / 2, cuerpo.getPosition().y - sprite.getHeight() / 2);
            sprite.draw(batch);
        }

    }

    public void creaFisica(){
        this.propiedadesCuerpo = new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody; // Establecemos el tipo de cuerpo del personaje como dinámico
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo); //Creamos el cuerpo en el mundo de nuestro juego

        /*
        Creamos las propiedades físicas del cuerpo del personaje
         */
        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape) propiedadesFisicasCuerpo.shape).setAsBox(1 / 2f, 1 / 2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);
    }

    public Body getCuerpo(){
        return cuerpo;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    //Método para que la cámara siga al personaje

    public void seguir(OrthographicCamera camara){
        camara.position.x=this.cuerpo.getPosition().x;

        if(this.cuerpo.getPosition().y>8){
            camara.position.y=this.cuerpo.getPosition().y+2;
        }

    }

    /*
    Método para comprobar la colisión entre el personaje y los objetos
     */

    public boolean compruebaColision(ObjetoJuego c){
        boolean overlaps=getHitBox().overlaps(c.getHitBox());
        if(overlaps&&colision==false){
            colision=true;
            Gdx.app.log("Colisionando","con "+c.getClass().getName());
        }else if(!overlaps){
            colision=false;
        }
        return colision;
    }

    public Rectangle getHitBox(){
        return sprite.getBoundingRectangle();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
