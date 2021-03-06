package com.el.poli.objetos;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
/*
Clase bola que hereda de la clase ObjetoJuego
El constructor recibe como parámetro el mundo del juego, un String con el nombre de la ruta de la textura, una posicion en el eje x y una posicion en el eje y
 */
public class Bola extends ObjetoJuego{

    public Bola(World m, String ruta, float x, float y) {
        super(m, ruta, x, y);
    }
}
