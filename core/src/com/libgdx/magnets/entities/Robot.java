package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Robot extends Sprite {

    public int characterWidth = 5;
    public int characterHeight = 5;

    public Body b2body;
    public World world;

    public Robot(World world, int pos_x, int pos_y) {

        super((new Texture(Gdx.files.internal("robot.png"))));
        // for set texture manually
//        setRegion(new Texture(Gdx.files.internal("MagnetMan.png")));

        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(pos_x, pos_y);

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

//        CircleShape shape = new CircleShape();
//        shape.setRadius(2);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(characterWidth/2f, characterHeight/2f);

        fdef.shape = bodyShape;
        b2body.createFixture(fdef);
        b2body.setLinearDamping(10f);

//        b2body.createFixture(fdef).setUserData(this);

        setBounds(Math.round(b2body.getPosition().x - getWidth() / 2),Math.round(b2body.getPosition().y - getHeight() / 2),characterWidth,characterHeight);
    }

    /*public TextureRegion getFrame(float dt) {

    }*/

    public void update(float dt) {
//        System.out.println("Sprite position: " + getX() + ", " + getY());
//        System.out.println("box2d body position" + b2body.getPosition().x + ", " + b2body.getPosition().y );
//        System.out.println(Math.round(b2body.getPosition().x - getWidth() / 2) + ", " + Math.round(b2body.getPosition().y - getHeight() / 2));
        setPosition(Math.round(b2body.getPosition().x - getWidth() / 2) , Math.round(b2body.getPosition().y - getHeight() / 2));
    }


   /* public void draw(Batch batch){
        super.draw(batch);

    }*/

}
