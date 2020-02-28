package com.el.poli.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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

    //variables Box2d
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;


    public PantallaJuego(Juego j){
        this.juego = j;
        texture = new Texture("personajes/vegira.png");
        camara = new OrthographicCamera();
        vp = new FitViewport(1400,700,camara);

        //Cargamos el mapa y nuestro map renderer
        mapa = new TmxMapLoader().load("mapa/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(mapa);
        camara.position.set(vp.getWorldWidth()/2, vp.getWorldHeight()/2,0);

        world = new World(new Vector2(-9.8f,0), true);
        box2DDebugRenderer = new Box2DDebugRenderer();

        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        for(MapObject objeto: mapa.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangulo = ((RectangleMapObject)objeto).getRectangle();

            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rectangulo.getX() + rectangulo.getWidth()/2, rectangulo.getY() + rectangulo.getHeight()/2);

            body = world.createBody(bDef);
            shape.setAsBox(rectangulo.getWidth()/2, rectangulo.getHeight()/2);
            fDef.shape = shape;
            body.createFixture(fDef);

        }
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

        //Limpiamos la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderizamos nuestro mapa
        renderer.render();

        box2DDebugRenderer.render(world, camara.combined);

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
