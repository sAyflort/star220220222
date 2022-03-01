package com.star.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.star.app.game.helpers.ObjectPool;

public class AsteroidControlled extends ObjectPool<Asteroid> {
    private Texture texture;

    @Override
    protected Asteroid newObject() {
        return new Asteroid();
    }

    public AsteroidControlled() {
        this.texture = new Texture("asteroid.png");
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            Asteroid a = activeList.get(i);
            batch.draw(texture, a.getPosition().x - 128, a.getPosition().y - 128, 128, 128,
                    256, 256, a.getScale(), a.getScale(), a.getAngle(), 0,0,256,
                    256,false, false);
        }
    }

    public void setup(float x, float y, float scale) {
        getActiveElement().activate(x, y, scale);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }

}
