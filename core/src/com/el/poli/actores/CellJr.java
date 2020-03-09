package com.el.poli.actores;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Clase CellJr que hereda de la clase personaje
 */
public class CellJr extends Personaje {

    private boolean ida;
    public int vidas = 2;

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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(this.getCuerpo().getLinearVelocity().x>0){
            ida = true;
            this.sprite = new Sprite(new Texture("personajes/celljr2.png"));
            this.sprite.setBounds(20,4,2,2);
        }else if(this.getCuerpo().getLinearVelocity().x<0){
            ida = false;
            this.sprite = new Sprite(new Texture("personajes/celljr.png"));
            this.sprite.setBounds(20,4,2,2);
        }
    }
}
