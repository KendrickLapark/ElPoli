package com.el.poli.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.el.poli.Juego;

public class PantallaGameOver implements Screen {

    protected Stage stage; //Stage para dibujar el fondo de nuestra pantalla de game over
    private Texture fondo; //Fondo de la pantalla
    private Juego juego; //Juego sobre el que se manejan las pantallas
    private int opcion;

    public PantallaGameOver(Juego j,Texture f, int num) {
        this.juego = j;
        stage = new Stage(new FillViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        fondo = f;
        opcion = num;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.getBatch().begin();
        stage.getBatch().draw(fondo,0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage.getBatch().end();
        stage.draw();

        System.out.println(opcion);

        if(opcion == 1){
            coordenadas();
            reiniciar();
            salir();
        }else if(opcion == 2){
            reiniciar2();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void coordenadas(){

        if(Gdx.input.isTouched()){
            System.out.println("X= "+Gdx.input.getX()+"Y= "+Gdx.input.getY());
        }
    }

    //Reinicia el juego en funcion de las coordenadas donde pulsa el jugador, detectando la opcion elegida

    public void reiniciar(){
        if (Gdx.input.getX()>131 && Gdx.input.getX()<250 && Gdx.input.getY()>223 && Gdx.input.getY()<278){
            this.juego.setScreen(new PantallaJuego(this.juego));
        }
    }

    //Sale del juego en funcion de las coordenadas donde pulsa el jugador, detectando la opcion elegida

    public void salir(){
        if (Gdx.input.getX()>363 && Gdx.input.getX()<494 && Gdx.input.getY()>213 && Gdx.input.getY()<278){
            System.exit(0);
        }
    }

    public void reiniciar2(){
        if (Gdx.input.getX()>250 && Gdx.input.getX()<389 && Gdx.input.getY()<325 && Gdx.input.getY()>262){
            this.juego.setScreen(new PantallaJuego(this.juego));
        }
    }

}
