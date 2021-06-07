package com.pujol.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Nave {

    //Caracteristicas
    float movementSpeed; //Units por segundo
    int shield;

    //Posicion y dimensiones
    float xPosition, yPosition; //Esquina inferior izquierda
    float width,height;

    //Graphics
    TextureRegion shipTexture, shieldTexture;

    public Nave(float movementSpeed, int shield, float width,
                float height, float xCentro, float yCentro,
                TextureRegion shipTexture, TextureRegion shieldTexture) {
        this.movementSpeed = movementSpeed;
        this.shield = shield;
        this.xPosition = xCentro - width/2;
        this.yPosition = yCentro - height/2;
        this.width = width;
        this.height = height;
        this.shipTexture = shipTexture;
        this.shieldTexture = shieldTexture;
    }

    public void draw(Batch batch) {
        batch.draw(shipTexture, xPosition, yPosition, width, height);
        if (shield > 0) {
            batch.draw(shieldTexture, xPosition,yPosition, width,height);
        }
    }
}
