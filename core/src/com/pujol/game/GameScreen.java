package com.pujol.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
   // private Texture background;
    private Texture[] fondos;

    //timing
   // private int backgroundOffset;
    private float[] backgroundOffsets = {0,0,0,0};
    private float backgroundMaxxScrollingSpeed;

    //world parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    GameScreen() {

        camera = new OrthographicCamera(); //Solo tiene 2d esta camara
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        //background = new Texture("darkPurpleStarscape.png");
        //backgroundOffset = 0;

        fondos = new Texture[4];
        fondos[0] = new Texture("Starscape00.png");
        fondos[1] = new Texture("Starscape01.png");
        fondos[2] = new Texture("Starscape02.png");
        fondos[3] = new Texture("Starscape03.png");

        backgroundMaxxScrollingSpeed = (float) (WORLD_HEIGHT) / 4;


        batch = new SpriteBatch();


    }

    @Override
    public void render(float deltaTime) {
        batch.begin();

        //Scrolling background (Lo hacemos infinito) y con movimiento
        renderBackground(deltaTime);

        batch.end();
    }

    //Con esto, hacemos que la velocidad de las estrellas del mapa sea diferente.
    private void renderBackground(float deltaTime) { //Velocidad de las lineas/capas.
        backgroundOffsets[0] += deltaTime * backgroundMaxxScrollingSpeed / 8;
        backgroundOffsets[1] += deltaTime * backgroundMaxxScrollingSpeed / 4;
        backgroundOffsets[2] += deltaTime * backgroundMaxxScrollingSpeed / 2;
        backgroundOffsets[3] += deltaTime * backgroundMaxxScrollingSpeed;

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
