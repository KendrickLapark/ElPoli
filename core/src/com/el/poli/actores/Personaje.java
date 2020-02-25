package com.el.poli.actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

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

    public void startMoving(Integer direccion){
        this.moving.add(direccion);
    }

    public void stopMoving(int direccion){
        this.moving.remove(direccion);
    }

    public HashSet<Integer> getMoving(){
        return moving;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(moving.contains(0)){moveUp(delta);}
        if(moving.contains(1)){moveRight(delta);}
        if(moving.contains(2)){moveDown(delta);}
        if(moving.contains(3)){moveLeft(delta);}
    }

    public void seguir(OrthographicCamera camara){
        camara.position.x=this.cuerpo.getPosition().x;
        camara.position.y=this.cuerpo.getPosition().y;
    }

    public void moveLeft(float delta) {
        if(getX()<= -getWidth()){
            setX(Gdx.graphics.getWidth());
        }else {
            MoveByAction moveLeftAction = new MoveByAction();
            moveLeftAction.setAmount(-this.velocidad, 0);
            moveLeftAction.setDuration(delta);
            addAction(moveLeftAction);
        }}

    public void moveUp(float delta) {
        if(getY()>= Gdx.graphics.getHeight()){
            setY(-getHeight());
        }else {
            MoveByAction moveUpAction = new MoveByAction();
            moveUpAction.setAmount(0,this.velocidad);
            moveUpAction.setDuration(delta);
            addAction(moveUpAction);
        }}

    public void moveDown(float delta) {
        if(getY()<=-getHeight()){
            setY(Gdx.graphics.getHeight());
        }else {
            MoveByAction moveDownAction = new MoveByAction();
            moveDownAction.setAmount(0,-this.velocidad);
            moveDownAction.setDuration(delta);
            addAction(moveDownAction);
        }}

    public void moveRight(float delta) {
        if(getX()>= Gdx.graphics.getWidth()){
            setX(-getWidth());
        }else {
            MoveByAction moveRightAction = new MoveByAction();
            moveRightAction.setAmount(this.velocidad, 0);
            moveRightAction.setDuration(delta);
            addAction(moveRightAction);
        }}
}
