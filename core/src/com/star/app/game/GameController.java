package com.star.app.game;

import com.badlogic.gdx.math.MathUtils;
import com.star.app.screen.ScreenManager;

public class GameController {
    private Background background;
    private BulletController bulletController;
    private Hero hero;
    private AsteroidControlled asteroidControlled;

    public AsteroidControlled getAsteroidControlled() {
        return asteroidControlled;
    }

    public BulletController getBulletController() {
        return bulletController;
    }

    public Background getBackground() {
        return background;
    }

    public Hero getHero() {
        return hero;
    }

    public GameController() {
        this.background = new Background(this);
        this.bulletController = new BulletController();
        this.hero = new Hero(this);
        this.asteroidControlled = new AsteroidControlled();
    }

    public void update(float dt) {
        background.update(dt);
        bulletController.update(dt);
        hero.update(dt);
        asteroidControlled.update(dt);
        checkCollisions();
    }


    public void checkCollisions() {
        Bullet b;
        Asteroid a;
        for (int i = 0; i < bulletController.getActiveList().size(); i++) {
            b = bulletController.getActiveList().get(i);
            for (int j = 0; j < asteroidControlled.getActiveList().size(); j++) {
                a = asteroidControlled.getActiveList().get(j);
                if (b.getPosition().dst(a.getPosition()) < a.getScale()*128) {
                    a.deactivate();
                    b.deactivate();
                }
            }
        }
    }
}
