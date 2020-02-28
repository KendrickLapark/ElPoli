package com.el.poli.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.el.poli.Juego;

public class PantallaJuego implements Screen {

    private Juego juego;
    private Texture texture;

    //variables de camara
    private OrthographicCamera camara;
    private Viewport vp;

    //variables del tiled map
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;


    public PantallaJuego(Juego j){
        this.juego = j;
        texture = new Texture("personajes/vegira.png");
        camara = new OrthographicCamera();
        vp = new FitViewport(1400,700,camara);

        mapa = new TmxMapLoader().load("mapa/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(mapa);
        camara.position.set(vp.getWorldWidth()/2, vp.getWorldHeight()/2,0);

    }

    @Override
    public void show() {

    }

    public void update(float delta){

        camara.update();
        renderer.setView(camara);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        vp.update(width,height);
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

}
