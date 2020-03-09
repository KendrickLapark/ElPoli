package com.el.poli.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.el.poli.Juego;
import com.el.poli.actores.Boo;
import com.el.poli.actores.CellJr;
import com.el.poli.actores.Vegeta;
import com.el.poli.entradas.EscuchadorTeclado;
import com.el.poli.objetos.Bola;
import com.el.poli.objetos.Manzana;
import com.el.poli.objetos.Radar;

import java.util.ArrayList;
import java.util.Random;

public class PantallaJuego implements Screen {

    private Juego juego; //Clase principal que hereda de Game
    private World world; //Mundo de nuestro juego
    private SpriteBatch batch; //Batch donde representanmos el mundo 2d

    private TiledMap mapa; //Mapa del juego
    private OrthographicCamera camara; //Camara del juego
    private OrthogonalTiledMapRenderer renderer; //Renderer del mapa

    private Vegeta vegeta;//Nuestro personaje principal
    private Boo bubu;// Enemigo
    private Bola bola1, bola2, bola3, bola4, bola5, bola6, bola7; //Clase que hereda de ObjetoJuego, representa los objetos que hay que conseguir
    private Radar radar; // Clase que hereda de ObjetoJuego, necesario para conseguir los demas objetos
    private Manzana manzana; //Clase que hereda de ObjetoJuego, aumenta la velocidad y permite volar al personaje
    private CellJr cellJr, cell2; //Enemigo
    public int vidas; //Vidas de nuestro jugador
    private Box2DDebugRenderer dbRenderer;
    private static final float pixelsPorCuadro = 16f;
    public ArrayList<Bola>listBolas; //Coleccion con las bolas
    private boolean poseeRadar,poseeManzana; //Bolean para detectar interacciones
    private Music sonido; //Música de fondo


    //Atributos para representar la hud del juego, contiene las vidas del jugador
    private SpriteBatch batchTexto;
    private BitmapFont textoVidas;
    private BitmapFont textoVictoria;


    //Botones para android y texture atlas de los botones que aparecen en el juego,

    private ImageButton rightBoton;
    private ImageButton leftBoton;
    private ImageButton jumpBoton;
    private TextureAtlas buttonAtlas;
    private Stage stage;

    public PantallaJuego(Juego j){
        this.juego = j;
        vidas = 5;
        batchTexto = new SpriteBatch();
        sonido = Gdx.audio.newMusic(Gdx.files.internal("musica/dbz.mp3"));
        sonido.play();
        sonido.setVolume(0.03f);
        sonido.setLooping(true);

        listBolas = new ArrayList<>();
        batch = new SpriteBatch();
        world = new World(new Vector2(0,-9.8f),true);
        vegeta = new Vegeta(world,"personajes/vegira.png",5,4);
        bubu = new Boo(world, "personajes/boo.png",139,2);
        cellJr = new CellJr(world,"personajes/celljr.png", 86,17);
        cell2 = new CellJr(world,"personajes/celljr.png", 129,24);
        camara = new OrthographicCamera(20,20);
        mapa = new TmxMapLoader().load("mapa/mapa1.tmx");
        bola1 = new Bola(world, "bolas/1.png", 156, 32);
        bola2 = new Bola(world, "bolas/2.png", 97,25);
        bola3 = new Bola(world, "bolas/3.png", 15,5);
        bola4= new Bola(world, "bolas/4.png", 110,6);
        bola5 = new Bola(world, "bolas/5.png", 30,35);
        bola6 = new Bola(world, "bolas/6.png", 62,17);
        bola7 = new Bola(world, "bolas/7.png", 156,6);
        manzana = new Manzana(world, "objetos/manzana.png",11,5);
        listBolas.add(bola1);
        listBolas.add(bola2);
        listBolas.add(bola3);
        listBolas.add(bola4);
        listBolas.add(bola5);
        listBolas.add(bola6);
        listBolas.add(bola7);
        radar = new Radar(world, "bolas/radar.png",150,5);  //150, 5 coordenadas iniciales
        renderer = new OrthogonalTiledMapRenderer(mapa,1/pixelsPorCuadro);
        this.dbRenderer = new Box2DDebugRenderer();
        camara.position.x=vegeta.getX()+10;
        camara.position.y= vegeta.getY()+10;
        camara.zoom=2f;

        //Texto para el juego

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fuente/impact.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.borderColor=new Color(444f,0.4f,0,1);
        parameter.borderWidth=1f;
        parameter.incremental=true;
        textoVidas = generator.generateFont(parameter);

        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fuente/saiyan.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter.borderColor=new Color(0.1f,0.1f,0.1f,1);
        parameter.borderWidth=3f;
        parameter.incremental=true;
        textoVictoria = generator.generateFont(parameter);

        //Botones para android y los listener para interactuar, se representa en pantalla agregando una tabla a un stage en la pantalla

        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        buttonAtlas= new TextureAtlas("boton/botonesA.pack");
        Skin btSkin=new Skin();
        btSkin.addRegions(buttonAtlas);

        ImageButton.ImageButtonStyle botonDerecha=new ImageButton.ImageButtonStyle();
        botonDerecha.up=btSkin.getDrawable("botonDerecha");
        rightBoton=new ImageButton(botonDerecha);


        rightBoton.addListener(new InputListener() {



            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(!poseeManzana){
                    if(vegeta.getCuerpo().getLinearVelocity().x <= 2){
                        vegeta.getCuerpo().applyLinearImpulse(new Vector2(5, 0), vegeta.getCuerpo().getWorldCenter(), true);
                        vegeta.setSprite(new Sprite(new Texture("personajes/vegira.png")));
                        vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
                    }

                }else{

                    if(vegeta.getCuerpo().getLinearVelocity().x <= 10){
                        vegeta.getCuerpo().applyLinearImpulse(new Vector2(8, 0), vegeta.getCuerpo().getWorldCenter(), true);
                        vegeta.setSprite(new Sprite(new Texture("personajes/vegira.png")));
                        vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
                    }

                }


                return true;
            }
        });

        ImageButton.ImageButtonStyle botonIzquierda=new ImageButton.ImageButtonStyle();
        botonIzquierda.up=btSkin.getDrawable("botonIzquierda");
        leftBoton=new ImageButton(botonIzquierda);


        leftBoton.addListener(new InputListener() {



            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(!poseeManzana){
                    if(vegeta.getCuerpo().getLinearVelocity().x >= -2){
                        vegeta.getCuerpo().applyLinearImpulse(new Vector2(-5, 0), vegeta.getCuerpo().getWorldCenter(), true);
                        vegeta.setSprite(new Sprite(new Texture("personajes/vegira2.png")));
                        vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
                    }

                }else{

                    if(vegeta.getCuerpo().getLinearVelocity().x >= -10){
                        vegeta.getCuerpo().applyLinearImpulse(new Vector2(-8, 0), vegeta.getCuerpo().getWorldCenter(), true);
                        vegeta.setSprite(new Sprite(new Texture("personajes/vegira2.png")));
                        vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
                    }

                }


                return true;
            }
        });

        ImageButton.ImageButtonStyle botonSalto=new ImageButton.ImageButtonStyle();
        botonSalto.up=btSkin.getDrawable("botonArriba");
        jumpBoton=new ImageButton(botonSalto);


        jumpBoton.addListener(new InputListener() {



            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if(!poseeManzana){
                    if(vegeta.getCuerpo().getLinearVelocity().y<1 && vegeta.getCuerpo().getPosition().y<4){
                        vegeta.getCuerpo().applyLinearImpulse(new Vector2(0, 5), vegeta.getCuerpo().getWorldCenter(), true);
                    }

                }else{
                    vegeta.getCuerpo().applyLinearImpulse(new Vector2(0, 10), vegeta.getCuerpo().getWorldCenter(), true);
                }


                return true;
            }
        });

        Table tb=new Table();
        tb.bottom();
        tb.setFillParent(true);
        tb.add(leftBoton).height(Gdx.graphics.getHeight()/6).width(Gdx.graphics.getWidth()/7);
        tb.add(rightBoton).height(Gdx.graphics.getHeight()/6).width(Gdx.graphics.getWidth()/7).padRight(Gdx.graphics.getWidth() / 2.7f);
        tb.add(jumpBoton).height(Gdx.graphics.getHeight()/6).width(Gdx.graphics.getWidth()/7);
        stage.addActor(tb);


        //bucle que define las propiedades físicas de la capa denominada "suelo" de nuestro tilemap

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


        //ContactListener para detectar el contacto entre los actores


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                if(contact.getFixtureA().getBody()==vegeta.getCuerpo()&&
                        contact.getFixtureB().getBody()==bola1.getCuerpo()){
                    Gdx.app.log("Vegeta","Vegeta ha tocado la bola");
                }
                if(contact.getFixtureA().getBody()==vegeta.getCuerpo()&&
                        contact.getFixtureB().getBody()==cellJr.getCuerpo()){
                    vegeta.getCuerpo().applyForceToCenter(100,200,true);
                    vegeta.setVidas(vidas--);
                }
                if(contact.getFixtureA().getBody()==vegeta.getCuerpo()&&
                        contact.getFixtureB().getBody()==cell2.getCuerpo()){
                    vegeta.getCuerpo().applyForceToCenter(100,200,true);
                    vegeta.setVidas(vidas--);
                }
                if(contact.getFixtureA().getBody()==vegeta.getCuerpo()&&
                        contact.getFixtureB().getBody()==bubu.getCuerpo()){
                    vegeta.getCuerpo().applyForceToCenter(100,200,true);
                    vegeta.setVidas(vidas--);
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
    public void show() {

    }

    @Override
    public void render(float delta) {
        actualizar(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        vegeta.seguir(camara);
        renderer.render();
        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        vegeta.draw(batch,0);
        bubu.draw(batch,0);
        cellJr.draw(batch,0);
        cell2.draw(batch,0);

        if(!poseeRadar) {
            radar.draw(batch,0);
            if (vegeta.compruebaColision(radar)) {
                poseeRadar = true;
                world.destroyBody(radar.getCuerpo());
            }
        }else{

            for(Bola b : listBolas){
                b.draw(batch,0);
            }

            if(vegeta.compruebaColision(bola2)) {
                listBolas.remove(bola2);
            }

            if(vegeta.compruebaColision(bola1)){
                listBolas.remove(bola1);
            }

            if(vegeta.compruebaColision(bola3)) {
                listBolas.remove(bola3);
            }

            if(vegeta.compruebaColision(bola4)){
                listBolas.remove(bola4);
            }

            if(vegeta.compruebaColision(bola5)) {
                listBolas.remove(bola5);
            }

            if(vegeta.compruebaColision(bola6)){
                listBolas.remove(bola6);
            }

            if(vegeta.compruebaColision(bola7)) {
                listBolas.remove(bola7);
            }

        }

        if(vegeta.compruebaColision(manzana)){
            poseeManzana = true;
        }

        if(!poseeManzana){
            manzana.draw(batch,0);
        }

        bubu.patrullar();
        cellJr.patrullar();
        cell2.patrullar();

        batch.end();

        if(listBolas.size() == 0){
            juego.setPantallaActual(new PantallaGameOver(this.juego,new Texture("atlas/menuvictoria.png"),2));
            sonido.stop();
        }

        if(vidas==0){
            juego.setPantallaActual(new PantallaGameOver(this.juego,new Texture("atlas/menuover.png"),1));
            sonido.stop();
        }

        batchTexto.begin();
        textoVidas.draw(batchTexto, "Vidas: "+vidas, Gdx.graphics.getHeight() / 30, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 30, Gdx.graphics.getWidth(), -1, false);
        batchTexto.end();

        stage.act();
        stage.draw();

        camara.update();

        dbRenderer.render(world,camara.combined);
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

        renderer.dispose();
        world.dispose();
        sonido.dispose();
        batch.dispose();
        batchTexto.dispose();
        stage.dispose();
        textoVidas.dispose();
        dbRenderer.dispose();


    }

    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) /pixelsPorCuadro, (rectangle.y + rectangle.height * 0.5f ) / pixelsPorCuadro);
        polygon.setAsBox(rectangle.width * 0.5f /pixelsPorCuadro, rectangle.height * 0.5f / pixelsPorCuadro, size, 0.0f);
        return polygon;
    }

    /*
    Método para manejar la órdenes del jugador sobre el personaje
     */

    public void entrada(float delta, boolean poseeManzana){

        if(!poseeManzana) {

            if (Gdx.input.isKeyPressed(Input.Keys.UP) && vegeta.getCuerpo().getLinearVelocity().y<1 && vegeta.getCuerpo().getPosition().y<4) {
                vegeta.getCuerpo().applyLinearImpulse(new Vector2(0, 10), vegeta.getCuerpo().getWorldCenter(), true);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vegeta.getCuerpo().getLinearVelocity().x <= 2) {
                vegeta.getCuerpo().applyLinearImpulse(new Vector2(5, 0), vegeta.getCuerpo().getWorldCenter(), true);
                vegeta.setSprite(new Sprite(new Texture("personajes/vegira.png")));
                vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vegeta.getCuerpo().getLinearVelocity().x >= -2) {
                vegeta.getCuerpo().applyLinearImpulse(new Vector2(-5, 0), vegeta.getCuerpo().getWorldCenter(), true);
                vegeta.setSprite(new Sprite(new Texture("personajes/vegira2.png")));
                vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
            }

        }else{

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                vegeta.getCuerpo().applyLinearImpulse(new Vector2(0, 10), vegeta.getCuerpo().getWorldCenter(), true);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && vegeta.getCuerpo().getLinearVelocity().x <= 10) {
                vegeta.getCuerpo().applyLinearImpulse(new Vector2(8, 0), vegeta.getCuerpo().getWorldCenter(), true);
                vegeta.setSprite(new Sprite(new Texture("personajes/vegira.png")));
                vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && vegeta.getCuerpo().getLinearVelocity().x >= -10) {
                vegeta.getCuerpo().applyLinearImpulse(new Vector2(-8, 0), vegeta.getCuerpo().getWorldCenter(), true);
                vegeta.setSprite(new Sprite(new Texture("personajes/vegira2.png")));
                vegeta.getSprite().setBounds(vegeta.getCuerpo().getPosition().x,vegeta.getCuerpo().getPosition().y,2,2);
            }
        }
    }

    //Método para actualizar la cámara

    public void actualizar(float delta){
        entrada(delta,poseeManzana);

        world.step(1/60f,6,2);

        camara.update();
        renderer.setView(camara);
    }


}
