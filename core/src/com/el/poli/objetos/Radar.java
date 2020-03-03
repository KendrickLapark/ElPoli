package com.el.poli.objetos;

import com.badlogic.gdx.physics.box2d.World;

/*
Clase radar que hereda de la clase ObjetoJuego
El constructor recibe como parámetro el mundo del juego, un String con el nombre de la ruta de la textura, una posicion en el eje x y una posicion en el eje y
 */

public class Radar extends ObjetoJuego{

    public Radar(World m, String ruta, float x, float y) {
        super(m, ruta, x, y);
    }
}
