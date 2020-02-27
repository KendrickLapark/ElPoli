package com.el.poli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.el.poli.actores.Boo;
import com.el.poli.actores.CellJr;
import com.el.poli.actores.Vegeta;
import com.el.poli.entradas.EscuchadorTeclado;
import com.el.poli.objetos.Bola;

public class Juego extends Game{

	private World world;
	private SpriteBatch batch;
	private TiledMap mapa;
	private OrthographicCamera camara;
	private OrthogonalTiledMapRenderer renderer;
	private Vegeta vegeta;
	private Boo bubu;
	private Bola bola1, bola2, bola3;
	//private CellJr cellJr;
	private Box2DDebugRenderer dbRenderer;
	private static final float pixelsPorCuadro = 16f;
	private EscuchadorTeclado teclado;

	@Override
	public void create() {

		batch = new SpriteBatch();
		world = new World(new Vector2(0,-9.8f),true);
		vegeta = new Vegeta(world,"personajes/vegira.png",5,4);
		bubu = new Boo(world, "personajes/boo.png",10,2);
		//cellJr = new CellJr(world,"personajes/celljr.png", 9,2);
		camara = new OrthographicCamera(10,10);
		mapa = new TmxMapLoader().load("mapa/mapa1.tmx");
		bola1 = new Bola(world, "bolas/1.png", 60, 10);
		bola2 = new Bola(world, "bolas/2.png", 20,5);
		bola3 = new Bola(world, "bolas/3.png", 25,5);
		renderer = new OrthogonalTiledMapRenderer(mapa,1/pixelsPorCuadro);
		this.dbRenderer = new Box2DDebugRenderer();
		camara.position.x=vegeta.getX();
		camara.position.y= vegeta.getY();
		//camara.zoom=8f;
		teclado = new EscuchadorTeclado(vegeta);
		Gdx.input.setInputProcessor(teclado);


		for (MapObject objeto:mapa.getLayers().get("suelo").getObjects()){
			BodyDef propiedadesRectangulo= new BodyDef(); //Establecemos las propiedades del cuerpo
			propiedadesRectangulo.type = BodyDef.BodyType.StaticBody;
			Body rectanguloSuelo = world.createBody(propiedadesRectangulo);
			FixtureDef propiedadesFisicasRectangulo=new FixtureDef();
			Shape formaRectanguloSuelo=getRectangle((RectangleMapObject)objeto);
			propiedadesFisicasRectangulo.shape = formaRectanguloSuelo;
			propiedadesFisicasRectangulo.density = 1f;
			rectanguloSuelo.createFixture(propiedadesFisicasRectangulo);
		}

	}

	@Override
	public void render() {

		Gdx.gl.glClearColor(0, 0, 0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		vegeta.seguir(camara);
		renderer.setView(camara);
		renderer.render();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		vegeta.draw(batch,0);
		bubu.draw(batch,0);
		bola1.draw(batch,0);
		bola2.draw(batch,0);
		bola3.draw(batch,0);
		//cellJr.draw(batch,0);
		bubu.patrullar();

		batch.end();
		camara.update();
		dbRenderer.render(world,camara.combined);
	}

	private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
		Rectangle rectangle = rectangleObject.getRectangle();
		PolygonShape polygon = new PolygonShape();
		Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) /pixelsPorCuadro, (rectangle.y + rectangle.height * 0.5f ) / pixelsPorCuadro);
		polygon.setAsBox(rectangle.width * 0.5f /pixelsPorCuadro, rectangle.height * 0.5f / pixelsPorCuadro, size, 0.0f);
		return polygon;
	}

	@Override
	public void dispose() {
		world.dispose();
		renderer.dispose();
		this.batch.dispose();
		this.dbRenderer.dispose();
	}


}

