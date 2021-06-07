package com.pujol.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Enemigo extends Nave{
    public Enemigo(float xCentre, float yCentre,
                   float width, float height,
                   float movementSpeed, int shield,
                   float laserWidth, float laserHeight,
                   float laserMovementSpeed, float timeBetweenShots,
                   TextureRegion shipTextureRegion,
                   TextureRegion shieldTextureRegion,
                   TextureRegion laserTextureRegion) {
        super(xCentre, yCentre, width, height, movementSpeed, shield, laserWidth, laserHeight, laserMovementSpeed, timeBetweenShots, shipTextureRegion, shieldTextureRegion, laserTextureRegion);
    }
}
