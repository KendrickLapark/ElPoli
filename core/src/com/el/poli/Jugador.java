package com.el.poli;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class Jugador {

    private Sprite sprite; //El sprite del jugador
    private Vector3 posicionTiles; //Posición x e y (z siempre 0) del jugador contando en tiles
    private Vector3 posicionPixels; //Posición x e y (z siempre 0) del jugador contando en pixels
    private OrthographicCamera camara; //Cámara que renderiza al jugado
    private int anchuraMapaPixels; //Anchura del mapa donde nos movemos en pixels
    private int alturaMapaPixels; //Altura del mapa donde nos movemos en pixels
    private int anchuraMapaTiles; //Anchura del mapa donde nos movemos en  tiles
    private int alturaMapaTiles; //Anchura del mapa donde nos movemos en tiles
    private SpriteBatch batch; //Batch donde contengo y dibujo al personaje
    private TiledMap mapa; //El mapa donde se juega

    public Jugador(OrthographicCamera camara,TiledMap mapa) {
        this.sprite = new Sprite(new Texture("personajes/vegira.png"));
        this.camara = camara;
        posicionTiles=this.camara.position;
        batch=new SpriteBatch();
        this.mapa=mapa;
        anchuraMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
        alturaMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getHeight();
        //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa
        anchuraMapaPixels=anchuraMapaTiles*(int)mapa.getProperties().get("width");
        alturaMapaPixels=alturaMapaTiles*(int)mapa.getProperties().get("height");
        //Como siempre estaré en el medio, voy a poner el sprite en el medio
        //Como la cámara está centrada en el medio, voy a necesitar coger el tile de ahi
        Vector3 posPixels = camara.project(
                new Vector3(camara.position.x,camara.position.y,0));
        sprite.setPosition(posPixels.x,posPixels.y);
    }

    public void dibujar(){
        ajustarACamara();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void dispose(){
        batch.dispose();
    }

    private void ajustarACamara(){
        sprite.setSize(
                ((Gdx.graphics.getWidth()*sprite.getTexture().getWidth())
                        /anchuraMapaPixels)*(1/camara.zoom),
                ((Gdx.graphics.getHeight()*sprite.getTexture().getHeight())
                        /alturaMapaPixels)
                        *(1/camara.zoom));
    }

    public void mover(char direccion){
        switch (direccion){
            case 'u':
                //Cambio posición del jugador, todavía no cambia nada visualmente
                if(posicionTiles.y<this.alturaMapaTiles-1) {
                    posicionTiles.y++;
                }
                //Pongo la cámara donde esté el jugador, para que siempre quede centrado en el tile en que está
                //Recuerdo que el jugador no está de verdad en el tile: El dibujado
                //del sprite es independiente del dibujado del mapa, y solo estamos
                //moviendo el mapa para hacer parecer que el personaje se mueve.
                camara.position.y=posicionTiles.y;
                break;
            case 'd':
                if(posicionTiles.y>0) {
                    posicionTiles.y--;
                }
                camara.position.y=posicionTiles.y;
                break;
            case 'l':
                if(posicionTiles.x>0) {
                    posicionTiles.x--;
                }
                camara.position.x=posicionTiles.x;
                break;
            case 'r':
                if(posicionTiles.x<this.anchuraMapaTiles-1) {
                    posicionTiles.x++;
                }
                camara.position.x=posicionTiles.x;
                break;
        }
        camara.update();
    }

    public boolean moverCamaraTile(char direccion) {
        switch (direccion) {
            case 'u':
                if (camara.position.y < this.alturaMapaTiles) {
                    camara.position.y++;
                    return true;
                }
                break;
            case 'd':
                if (camara.position.y > 0) {
                    camara.position.y--;
                    return true;
                }
                break;
            case 'l':
                if (camara.position.x > 0) {
                    camara.position.x--;
                    return true;
                }
                break;
            case 'r':
                if (camara.position.x < this.anchuraMapaTiles) {
                    camara.position.x++;
                    return true;
                }
                break;
        }
        return false;
    }

    private void ponerEnTile(){
        posicionPixels=camara.project(new Vector3(posicionTiles));
        sprite.setPosition(posicionPixels.x,posicionPixels.y);
    }

    public void cambiarTile(int x,int y){
        posicionTiles=new Vector3(x,y,0);
        // ponerEnTile();
    }

    public void moverJugadorTile(char direccion,boolean moverDelCentro){
        switch (direccion){
            case 'u':
                if(posicionTiles.y<this.alturaMapaTiles-1) {
                    posicionTiles.y++;
                }
                break;
            case 'd':
                if(posicionTiles.y>0) {
                    posicionTiles.y--;
                }
                break;
            case 'l':
                if(posicionTiles.x>0) {
                    posicionTiles.x--;
                }
                break;
            case 'r':
                if(posicionTiles.x<this.anchuraMapaTiles-1) {
                    posicionTiles.x++;
                }
                break;
        }
        this.cambiarTile((int)posicionTiles.x,(int)posicionTiles.y);
        if(moverDelCentro){
            ponerEnTile();
        }
    }

    public OrthographicCamera getCamara(){
        return this.camara;
    }

    public TiledMap getMapa(){
        return this.mapa;
    }
}
