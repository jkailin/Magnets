package com.libgdx.magnets.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Magnet extends Sprite {

    public int characterWidth = 4;
    public int characterHeight = 4;

    public Body b2body;
    public World world;

    // pos_x, pos_y bottom left corner of sprite
    public Magnet(World world, int pos_x, int pos_y) {

        super((new Texture(Gdx.files.internal("magnet_small.png"))));
        // for set texture manually
//        setRegion(new Texture(Gdx.files.internal("MagnetMan.png")));

        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(pos_x, pos_y);

        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(characterWidth/2f, characterHeight/2f);

        fdef.shape = bodyShape;
        b2body.createFixture(fdef);

//        b2body.setLinearDamping(3f);

//        b2body.createFixture(fdef).setUserData(this);

        setBounds(Math.round(b2body.getPosition().x - getWidth() / 2),Math.round(b2body.getPosition().y - getHeight() / 2),characterWidth,characterHeight);
    }

}
