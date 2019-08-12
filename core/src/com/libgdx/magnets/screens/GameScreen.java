package com.libgdx.magnets.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;
import com.libgdx.magnets.entities.Magnet;
import com.libgdx.magnets.entities.MagnetMan;
import com.libgdx.magnets.entities.Test;
import com.libgdx.magnets.entities.Wall;

public class GameScreen implements Screen {

    private final MagnetsGame GAME;
    private Stage stage;

//    private final World world;
    private MagnetMan magnetMan;
    private Magnet magnet;
    private Image image;

    private World world;
    private Box2DDebugRenderer debugRenderer;
//    private B2WorldCreator creator;

    private Test test;
    private Test test2;
    private Wall north,east,south,west;

    // character speed
    private int v_x = 0;
    private int v_y = 0;

    // input pressed
    private boolean pressed;

    public GameScreen(final MagnetsGame game) {

        this.GAME = game;
        /*this.stage = new Stage(GAME.viewport, GAME.batch);
        Gdx.input.setInputProcessor(stage);
*/
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
//        world.setContactListener(new WorldContactListener());

//        BodyDef bdef = new BodyDef();

        test = new Test(world, 20,20);
        test2 = new Test(world,40,40);

        // map boundaries
        north = new Wall(world, 1,20,20,20);
//        east = new Wall(world, 15,1,20,62);
        south = new Wall(world, 1,1,64,1);
        west = new Wall(world, 1,1,1,63);
//        s = new Sprite((new Texture(Gdx.files.internal("MagnetMan.png"))));

      /*  Texture splash = new Texture(Gdx.files.internal("battle-arena-background.png"));
        image = new Image(splash);
        image.setPosition(0,0);

        initObjects();


//        stage.addActor(image);
        stage.addActor(magnetMan);
        stage.addActor(magnet);

        stage.setKeyboardFocus(magnetMan);*/

//        world = new World(0, true);




    }

    @Override
    public void show() {

    }

    public void update(float delta) {
       /* if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && test.b2body.getLinearVelocity().x <= 2)//
//            test.b2body.setLinearVelocity(15,0);
                        test.b2body.applyLinearImpulse(new Vector2(2f, 0), test.b2body.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && test.b2body.getLinearVelocity().x >= -2)
            test.b2body.applyLinearImpulse(new Vector2(-2f, 0), test.b2body.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.UP) && test.b2body.getLinearVelocity().x <= 2)
            test.b2body.applyLinearImpulse(new Vector2(0, 2f), test.b2body.getWorldCenter(), true);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && test.b2body.getLinearVelocity().x >= -2)
            test.b2body.applyLinearImpulse(new Vector2(0, -2f), test.b2body.getWorldCenter(), true);
        else
            test.b2body.setLinearVelocity(0,0);*/

       pressed = false;


        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
//            test.b2body.setLinearVelocity(30, test.b2body.getLinearVelocity().y);
            v_x += 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            test.b2body.setLinearVelocity(-30, test.b2body.getLinearVelocity().y);
            v_x -= 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            test.b2body.setLinearVelocity(test.b2body.getLinearVelocity().x, 30);
            v_y += 30;
            pressed = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            test.b2body.setLinearVelocity(test.b2body.getLinearVelocity().x, -30);
            v_y -= 30;
            pressed = true;
        }
        if(!pressed)
            test.b2body.setLinearVelocity(0,0);
        test.b2body.setLinearVelocity(v_x, v_y);
        v_x =0;
        v_y=0;

        world.step(1/60f,1,1);
        test.update(delta);
        test2.update(delta);
//        east.update(delta);
    }

    @Override
    public void render(float delta) {

        update(delta);

//        GAME.shapeRenderer.setProjectionMatrix(GAME.camera.combined);

        Gdx.gl.glClearColor(0.2f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        GAME.batch.setProjectionMatrix(GAME.camera.combined);

//        GAME.font.draw(GAME.batch, "Welcome to Drop!!! ", 100, 150);
//        GAME.font.draw(GAME.batch, "Tap anywhere to begin!", 100, 100);


        GAME.batch.begin();
//        test.draw(GAME.batch);
        test.draw(GAME.batch);
        test2.draw(GAME.batch);
        GAME.batch.end();

        debugRenderer.render(world, GAME.camera.combined);


//        stage.act(delta);
//
//        stage.draw();

       /* GAME.batch.begin();
        GAME.batch.end();*/


//        magnetMan.draw( GAME.shapeRenderer);e


    }

    @Override
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, false);
        GAME.viewport.update(width, height, false);
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

        world.dispose();


    }

    public void initObjects() {
        magnetMan = new MagnetMan();
        magnet = new Magnet();
    }
}
