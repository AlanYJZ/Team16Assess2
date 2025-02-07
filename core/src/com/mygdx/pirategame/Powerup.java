package com.mygdx.pirategame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Powerup extends Entity { 
    /**
     * Defines the effect that the powerup will give.
     * 1: Restores some health
     * 2: Temporarily increases damage dealt for 10 seconds
     * 3: Temporarily increases movement speed for 20 seconds
     * 4: Temporarily increases attack speed for 10 seconds
     * 5: Temporarily grants immunity to all forms of damage for 10 seconds
     */
    private int powerType;
    private Texture powerup;
    private boolean setToDestroyed;
    private boolean destroyed;
    private Sound powerupPickup;
    
    public Powerup(GameScreen screen, float x, float y, int powerType){
        super(screen, x, y);
        this.powerType = powerType;
        switch (this.powerType){
            case 1:{
                powerup = new Texture("repairPower.png");
            }
            case 2:{
                powerup = new Texture("damagePower.png");
            }
            case 3:{
                powerup = new Texture("movePower.png");
            }
            case 4:{
                powerup = new Texture("attackspeedPower.png");
            }
            case 5:{
                powerup = new Texture("immunityPower.png");
            }

        }
        setBounds(0,0,48 / PirateGame.PPM, 48 / PirateGame.PPM);
        //Set the texture
        setRegion(powerup);
        //Sets origin of the coin
        setOrigin(24 / PirateGame.PPM,24 / PirateGame.PPM);
        powerupPickup = Gdx.audio.newSound(Gdx.files.internal("coin-pickup.mp3"));


    }
    /**
     * Updates the powerup's state. If needed, deletes the powerup if picked up.
     */
    public void update() {
        //If coin is set to destroy and isnt, destroy it
        if(setToDestroyed && !destroyed) {
            world.destroyBody(b2body);
            destroyed = true;
        }
        //Update position of coin
        else if(!destroyed) {
            setPosition(b2body.getPosition().x - getWidth() / 2f, b2body.getPosition().y - getHeight() / 2f);
        }

    }

    /**
     * Defines all the parts of the coins physical model. Sets it up for collisons
     */
    @Override
    protected void defineEntity() {
        //sets the body definitions
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        //Sets collision boundaries
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(24 / PirateGame.PPM);
        // setting BIT identifier
        fdef.filter.categoryBits = PirateGame.COIN_BIT;
        // determining what this BIT can collide with
        fdef.filter.maskBits = PirateGame.DEFAULT_BIT | PirateGame.PLAYER_BIT | PirateGame.ENEMY_BIT;
        fdef.shape = shape;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * What happens when an entity collides with the coin. The only entity that is able to do so is the player ship
     */
    @Override
    public void entityContact() {
        switch (this.powerType){
            case 1:{
                
            }
            case 2:{
               
            }
            case 3:{
                
            }
            case 4:{
                
            }
            case 5:{
                
            }
        }
        //Set to destroy
        setToDestroyed = true;
        Gdx.app.log("powerup", "collision");
        //Play pickup sound
        if (screen.game.getPreferences().isEffectsEnabled()) {
            powerupPickup.play(screen.game.getPreferences().getEffectsVolume());
        }

    }
}







