package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.star.app.game.helpers.Poolable;
import com.star.app.screen.ScreenManager;

public class Asteroid implements Poolable {
    private Vector2 position;
    private Vector2 velocity;
    private boolean active;
    private float scale;
    private float angle;
    private float anVelocity;

    public Asteroid() {
        this.position = new Vector2();
        this.active = false;
        this.velocity = new Vector2(MathUtils.random(-300*scale, 300*scale), 0);
        this.velocity.y = (float) (MathUtils.randomSign() * Math.sqrt(90000*scale*scale-velocity.x*velocity.x));
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public float getAngle() {
        return angle;
    }

    public void deactivate() {
        active = false;
    }

    public void activate(float x, float y, float scale) {
        this.position.set(x, y);
        this.scale = scale;
        this.velocity = new Vector2(MathUtils.random(-300*(1-scale), 300*(1-scale)), 0);
        this.velocity.y = (float) (MathUtils.randomSign() * Math.sqrt(90000*(1-scale)*(1-scale)-velocity.x*velocity.x));
        this.angle = MathUtils.random(0, 360);
        this.anVelocity = MathUtils.random(0, (MathUtils.randomSign())*360*(1-scale));
        active = true;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        angle += anVelocity*dt;
        if(position.x < -128*scale) {
            position.x = ScreenManager.SCREEN_WIDTH + 128*scale;
        }
        if(position.x > ScreenManager.SCREEN_WIDTH + 128*scale) {
            position.x = -128*scale;
        }
        if(position.y < -128*scale) {
            position.y = ScreenManager.SCREEN_HEIGHT + 128*scale;
        }
        if(position.y > ScreenManager.SCREEN_HEIGHT + 128*scale) {
            position.y = -128*scale;
        }
    }
}
