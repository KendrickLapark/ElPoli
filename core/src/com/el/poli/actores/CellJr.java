package com.el.poli.actores;

import com.badlogic.gdx.physics.box2d.World;


/**
 * Clase CellJr que hereda de la clase personaje
 */
public class CellJr extends Personaje {

    private boolean ida;

    public CellJr(World w, String rutaTextura) {
        super(w, rutaTextura);
    }


    /*
    Constructor de CellJr con 4 parámetros, el mundo del juego, el nombre de la ruta de la textura, la posicion en el eje x y la posicion en el eje y
     */
    public CellJr(World w, String rutaTextura, float x, float y) {
        super(w, rutaTextura, x, y);
        ida = true;
    }

    /*
    Método patrullar para que CellJr se mueva de un punto a otro del mapa en el eje x
     */

    public void patrullar() {

        System.out.println("la equis de CellJr: "+this.getCuerpo().getPosition().x+" Ida: "+ida);

        if(this.getCuerpo().getPosition().x<20 && ida == true){
            this.getCuerpo().setLinearVelocity(3,0);
        }else{
            ida = false;
        }

        if(this.getCuerpo().getPosition().x>10 && ida == false){
            this.getCuerpo().setLinearVelocity(-3,0);
        }else{
            ida = true;
        }

    }
}
