package com.pujol.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;

    private TextureRegion[] fondos;
    private float backgroundHeight;

    private TextureRegion playerShipTextureRegion, playerShieldTextureRegion, enemyShipTextureRegion,
            enemyShieldTextureRegion, playerLaserTextureRegion, enemyLaserTextureRegion;

    //timing
   // private int backgroundOffset;
    private float[] backgroundOffsets = {0,0,0,0};
    private float backgroundMaxScrollingSpeed;

    //world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    //gameobjects
    private Nave playerShip;
    private Nave enemyShip;

    GameScreen() {

        camera = new OrthographicCamera(); //Solo tiene 2d esta camara
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //TextureAtlas
        textureAtlas = new TextureAtlas("Imagenes.atlas");

        //background = new Texture("darkPurpleStarscape.png");
        //backgroundOffset = 0;

        fondos = new TextureRegion[4];
        fondos[0] = textureAtlas.findRegion("Starscape00");
        fondos[1] = textureAtlas.findRegion("Starscape01");
        fondos[2] = textureAtlas.findRegion("Starscape02");
        fondos[3] = textureAtlas.findRegion("Starscape03");

        backgroundHeight = WORLD_HEIGHT * 2;
        backgroundMaxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;

        //Initialize texture regions
        playerShipTextureRegion = textureAtlas.findRegion("playerShip3_blue");
        enemyShipTextureRegion = textureAtlas.findRegion("enemyGreen4");
        playerShieldTextureRegion = textureAtlas.findRegion("shield1");
        enemyShieldTextureRegion = textureAtlas.findRegion("shield2");
        enemyShieldTextureRegion.flip(false,true); //Giramos el  escudo del enemigo para que no salga al reves.
        playerLaserTextureRegion = textureAtlas.findRegion("laserRed03");
        enemyLaserTextureRegion = textureAtlas.findRegion("laserGreen05");

        //Poner los objetos del juego
        playerShip = new Nave(2,3,10,10,
                WORLD_WIDTH/2, WORLD_HEIGHT/4,
                playerShipTextureRegion, playerShieldTextureRegion);
        enemyShip = new Nave(2,1,10,10,
                WORLD_WIDTH/2, WORLD_HEIGHT*3/4,
                enemyShipTextureRegion, enemyShieldTextureRegion);


        batch = new SpriteBatch();


    }

    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Scrolling background (Lo hacemos infinito) y con movimiento
        renderBackground(deltaTime);

        //Naves enemigas
        enemyShip.draw(batch);
        //Nave del jugador
        playerShip.draw(batch);


        batch.end();
    }

    //Con esto, hacemos que la velocidad de las estrellas del mapa sea diferente.
    private void renderBackground(float deltaTime) { //Velocidad de las lineas/capas.
        backgroundOffsets[0] += deltaTime * backgroundMaxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * backgroundMaxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * backgroundMaxScrollingSpeed / 2;
        backgroundOffsets[3] += deltaTime * backgroundMaxScrollingSpeed;

        for (int layer = 0; layer < backgroundOffsets.length; layer ++) { //Mapa infinito
            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0;
            }
            batch.draw(fondos[layer], 0, -backgroundOffsets[layer], WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(fondos[layer], 0, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); //Punto de vista
        batch.setProjectionMatrix(camera.combined);
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
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
