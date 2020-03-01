package com.el.poli;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.el.poli.actores.Boo;
import com.el.poli.actores.CellJr;
import com.el.poli.actores.Vegeta;
import com.el.poli.entradas.EscuchadorTeclado;
import com.el.poli.objetos.Bola;
import com.el.poli.objetos.Radar;

import java.util.ArrayList;

public class Juego extends Game{

	private World world;
	private SpriteBatch batch;
	private TiledMap mapa;
	private OrthographicCamera camara;
	private OrthogonalTiledMapRenderer renderer;
	private Vegeta vegeta;
	private Boo bubu;
	private Bola bola1, bola2, bola3, bola4, bola5, bola6, bola7;
	private Radar radar;
	//private CellJr cellJr;
	private int bolas, contBolas;
	private Box2DDebugRenderer dbRenderer;
	private static final float pixelsPorCuadro = 16f;
	private EscuchadorTeclado teclado;
	private ArrayList<Bola>listBolas;
	private boolean poseeRadar, bBola1;


	private SpriteBatch batchTexto;
	private BitmapFont bitmapafuente;


	private Viewport vp;

	@Override
	public void create() {
		bolas = 0;
		contBolas = 0;
		batchTexto = new SpriteBatch();

		listBolas = new ArrayList<>();
		batch = new SpriteBatch();
		world = new World(new Vector2(0,-9.8f),true);
		vegeta = new Vegeta(world,"personajes/vegira.png",5,4);
		bubu = new Boo(world, "personajes/boo.png",10,2);
		//cellJr = new CellJr(world,"personajes/celljr.png", 9,2);
		camara = new OrthographicCamera(20,20);
		mapa = new TmxMapLoader().load("mapa/mapa1.tmx");
		bola1 = new Bola(world, "bolas/1.png", 60, 10);
		bola2 = new Bola(world, "bolas/2.png", 20,5);
		bola3 = new Bola(world, "bolas/3.png", 25,5);
		bola4= new Bola(world, "bolas/4.png", 45,5);
		bola5 = new Bola(world, "bolas/5.png", 30,35);
		bola6 = new Bola(world, "bolas/6.png", 60,45);
		bola7 = new Bola(world, "bolas/7.png", 70,5);
		listBolas.add(bola1);
		listBolas.add(bola2);
		listBolas.add(bola3);
		listBolas.add(bola4);
		listBolas.add(bola5);
		listBolas.add(bola6);
		listBolas.add(bola7);
		radar = new Radar(world, "bolas/radar.png",10,20);

		renderer = new OrthogonalTiledMapRenderer(mapa,1/pixelsPorCuadro);
		this.dbRenderer = new Box2DDebugRenderer();
		camara.position.x=vegeta.getX()+10;
		camara.position.y= vegeta.getY()+10;
		//camara.zoom=1.5f;
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



		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				Gdx.app.log("Contacto comenzado!",contact.getFixtureA()+" : "+contact.getFixtureB());
				Gdx.app.log("Es figura A vegeta?",(contact.getFixtureA().getBody()==vegeta.getCuerpo())+"");
				Gdx.app.log("Es figura B bola?",(contact.getFixtureB().getBody()==bola1.getCuerpo())+"");
				if(contact.getFixtureA().getBody()==vegeta.getCuerpo()&&
						contact.getFixtureB().getBody()==bola1.getCuerpo()){
					Gdx.app.log("Vegeta","Vegeta ha tocado la bola");
				}
				if(contact.getFixtureA().getBody()==vegeta.getCuerpo()&&
						contact.getFixtureB().getBody()==bubu.getCuerpo()){
					vegeta.getCuerpo().applyForceToCenter(100,200,true);
					bolas++;
					//baseDeDatos.guardar(puntuacion);
					Gdx.app.log("Bolas recogidas:",bolas+"");
				}
			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		});

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


		if(!poseeRadar){
			radar.draw(batch,0);
			if(vegeta.compruebaColision(radar)){
				radar.dispose();
				poseeRadar = true;
			}
		}else{
			dibujaBolas();
		}




		//cellJr.draw(batch,0);
		bubu.patrullar();

		batch.end();

		batchTexto.begin();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fuente/impact.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 16;
		parameter.borderColor=new Color(444f,0.4f,0,1);
		parameter.borderWidth=1f;
		parameter.incremental=true;
		bitmapafuente = generator.generateFont(parameter);
		bitmapafuente.draw(batchTexto, "Puntos: " + contBolas, Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 30, Gdx.graphics.getWidth(), -1, false);
		batchTexto.end();

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

	public void dibujaBolas(){
		for(Bola b : listBolas){
			b.draw(batch,0);
		}
	}

	public void dibujaBola(Bola b){
		b.draw(batch,0);
	}


}

