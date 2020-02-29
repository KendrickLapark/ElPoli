package com.el.poli.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.el.poli.actores.Vegeta;

public class PantallaJuego implements Screen {

    private Juego juego;
    private Texture t;
    private OrthographicCamera camara;
    private Viewport vp;
    private TmxMapLoader mapLoader;
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dbr;
    private Vegeta vegeta;

    public PantallaJuego(Juego g){
        this.juego = g;
        t = new Texture("personajes/vegira.png");
        camara = new OrthographicCamera();
        vp = new FitViewport(1400,700,camara);
        mapLoader = new TmxMapLoader();
        mapa =  mapLoader.load("mapa/mapa1.tmx");
        renderer = new OrthogonalTiledMapRenderer(mapa);
        camara.position.set(vp.getWorldWidth()/2,vp.getWorldHeight()/2,0);
        world = new World(new Vector2(0,-100),true);
        b2dbr =  new Box2DDebugRenderer();
        vegeta = new Vegeta(world);

        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixDef = new FixtureDef();
        Body body;

        for(MapObject object : mapa.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
            bDef.type = BodyDef.BodyType.StaticBody;
            bDef.position.set(rectangle.getX() + rectangle.getWidth()/2, rectangle.getY() + rectangle.getHeight()/2);

            body = world.createBody(bDef);
            shape.setAsBox(rectangle.getWidth()/2, rectangle.getHeight()/2);
            fixDef.shape = shape;
            body.createFixture(fixDef);
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        actualizar(delta);
        Gdx.gl.glClearColor(0, 0, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dbr.render(world,camara.combined);

    }

    @Override
    public void resize(int width, int height) {

        vp.update(width,height); //redimensionamos el viewport con la altura y anchura del dispositivo

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

    public void handleInput(float delta){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            vegeta.body.applyLinearImpulse(new Vector2(0,100), vegeta.body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && vegeta.body.getLinearVelocity().x <=2){
            vegeta.body.applyLinearImpulse(new Vector2(100,0), vegeta.body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && vegeta.body.getLinearVelocity().x >=2){
            vegeta.body.applyLinearImpulse(new Vector2(-100,0), vegeta.body.getWorldCenter(),true);
        }

    }

    public void actualizar(float delta){
        handleInput(delta);

        world.step(1/60f,6,2);

        juego.batch.begin();
        vegeta.draw(juego.batch,0);
        juego.batch.end();
        camara.position.x = vegeta.body.getPosition().x;

        camara.update();
        renderer.setView(camara);
    }
}
