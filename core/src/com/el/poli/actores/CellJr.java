package com.el.poli.actores;

import com.badlogic.gdx.physics.box2d.World;

public class CellJr extends Personaje {

    public CellJr(World w, String rutaTextura) {
        super(w, rutaTextura);
    }

    public CellJr(World w, String rutaTextura, float x, float y) {
        super(w, rutaTextura, x, y);
    }

    public void patrulla(){


        if(getX()>=9){
            this.getCuerpo().setLinearVelocity(-2,0);
        }

    }
}
