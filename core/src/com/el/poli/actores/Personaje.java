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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.el.poli.objetos.ObjetoJuego;

import java.util.HashSet;

public abstract class Personaje extends Actor {

    protected Sprite sprite;
    private World mundo;
    protected int fuerza;
    private OrthographicCamera camara;
    private BodyDef propiedadesCuerpo;
    private Body cuerpo;
    protected Stage pantalla;
    private FixtureDef propiedadesFisicasCuerpo;
    protected boolean colliding;
    private Vegeta vegeta;
    private int velocidad;
    private HashSet<Integer> moving;
    private boolean colision;

    public Personaje(World w, String rutaTextura){

        mundo = w;
        sprite = new Sprite(new Texture(rutaTextura));
        int anchuraSprite = 1; //Anchura y altura se expresan ahora en metros
        int alturaSprite = 1;//Anchura y altura se expresan ahora en metros
        sprite.setBounds(20, 4,anchuraSprite, alturaSprite); //La posición inicial también debe estar en metros


        this.propiedadesCuerpo = new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo);

        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape) propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite / 2f, alturaSprite / 2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);

        sprite.setOrigin(this.sprite.getWidth() / 2,this.sprite.getHeight() / 2);
    }

    public Personaje(World w, String rutaTextura,float x, float y){

        mundo = w;
        sprite = new Sprite(new Texture(rutaTextura));
        int anchuraSprite = 1; //Anchura y altura se expresan ahora en metros
        int alturaSprite = 1;//Anchura y altura se expresan ahora en metros
        sprite.setBounds(x, y,anchuraSprite, alturaSprite); //La posición inicial también debe estar en metros


        this.propiedadesCuerpo = new BodyDef(); //Establecemos las propiedades del cuerpo
        propiedadesCuerpo.type = BodyDef.BodyType.DynamicBody;
        propiedadesCuerpo.position.set(sprite.getX(), sprite.getY());

        cuerpo = mundo.createBody(propiedadesCuerpo);

        propiedadesFisicasCuerpo = new FixtureDef();
        propiedadesFisicasCuerpo.shape = new PolygonShape();
        ((PolygonShape) propiedadesFisicasCuerpo.shape).setAsBox(anchuraSprite / 2f, alturaSprite / 2f);
        propiedadesFisicasCuerpo.density = 1f;
        cuerpo.createFixture(propiedadesFisicasCuerpo);

        sprite.setOrigin(this.sprite.getWidth() / 2,this.sprite.getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (sprite != null) {
            sprite.setPosition(cuerpo.getPosition().x - sprite.getWidth() / 2, cuerpo.getPosition().y - sprite.getHeight() / 2);
            //Sprite quiere la rotación en grados, el cuerpo la da en radianes. Esta constante convierte de uno a otro.
            //sprite.setRotation(MathUtils.radiansToDegrees*cuerpo.getAngle());


            sprite.draw(batch);
        }

    }

    public Body getCuerpo(){
        return cuerpo;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void seguir(OrthographicCamera camara){
        camara.position.x=this.cuerpo.getPosition().x;
        camara.position.y=this.cuerpo.getPosition().y;
    }

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


}
