package net.mortalsilence.olli.space.gameObjects;

import net.mortalsilence.olli.space.AsteroidsApplet;
import net.mortalsilence.olli.space.events.*;
import net.mortalsilence.olli.space.factorys.GameSceneFactory;
import net.mortalsilence.olli.space.scenes.Scene;
import net.mortalsilence.olli.space.utility.Keyboard;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import static processing.core.PApplet.*;

public class Spaceship extends GameObject implements ButtonPressedListener, AsteroidMovedListener, SpaceshipProjektileHitListener, AlienUFOMovedListener, SpaceshipProjektileMovedListener, ItemPickedUpListener, ItemTimeUpListener {

    private int level;
    private int exp;
    private PVector pos = new PVector(1000,500);
    private final int size;
    private final int cooldownTurret;
    @SuppressWarnings("SpellCheckingInspection")
    private int cooldownTurretakt;

    public float turretTurnVelocity;
    private float velocity;
    private float bulletVelocity;
    PVector direction;
    PVector vel ;
    private boolean wPressed = false;

    private int hp;

    private final int amountInvincFrames;

    private int amountInvincFramesLeft;

    private boolean invincible = false;

    private int highestLevel;

    private int highestExperience;

    private final PImage textureNormal;

    private final PImage textureAccelerating;

    private final PImage textureEMP;
    private boolean accelerating = false;

    private ArrayList<Item> items = new ArrayList<>();

    private ArrayList<Item> itemsToDelete = new ArrayList<>();

    private int amountMPBlast = 0;
    private int empSize = 300;
    private Thread bombTimer;

    boolean canUseEmp = true;

    private int amountCooldownFramesLeft;


    public Spaceship(Scene scene) {
        super(scene);
        this.amountInvincFrames = (int) AsteroidsApplet.asteroidsApplet.getGameRule(2);
        this.amountInvincFramesLeft = this.amountInvincFrames;
        amountCooldownFramesLeft = 10;
        this.cooldownTurret = (int) AsteroidsApplet.asteroidsApplet.getGameRule(5);
        this.cooldownTurretakt = 0;
        this.velocity = 15;
        this.turretTurnVelocity = (float) AsteroidsApplet.asteroidsApplet.getGameRule(4);
        this.direction = new PVector(1,0);
        this.vel = new PVector();
        this.bulletVelocity = (float) AsteroidsApplet.asteroidsApplet.getGameRule(6);//20.0F;
        this.size = 5;
        System.out.println("Debug: Initial turretTurningVelocity " + turretTurnVelocity);
        this.scene.getEventbus().registerAlienUFOMovedListeners(this);
        this.scene.getEventbus().registerSpaceshipProjektileMovedListener(this);
        this.scene.getEventbus().registerItemPickedUpListener(this);
        this.scene.getEventbus().registerItemTimeUpListener(this);
        this.hp = (int) AsteroidsApplet.asteroidsApplet.getGameRule(0);
        textureNormal = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+ File.separator+"Spaceship_v2.png");
        textureAccelerating = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+File.separator+"Spaceship_v2_beschleunigend.png");
        textureEMP = AsteroidsApplet.asteroidsApplet.loadImage(AsteroidsApplet.ADRESS_TO_SPACE+"textures"+File.separator+"EMP_blast.png");
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Override
    public void buttonPressed() {
        if(Keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
            //System.out.println("Debug: SpacePressed proccesed by Spaceship");
            wPressed = true;
            PVector traveldirection = new PVector(AsteroidsApplet.asteroidsApplet.mouseX - this.pos.x,AsteroidsApplet.asteroidsApplet.mouseY - this.pos.y);// schlecht programmiert posy   and posx direkt abgefragt
            traveldirection = traveldirection.normalize();
            PVector velmax = traveldirection.mult(velocity);// velmax ist die Geschwindigkeit die das Raumschiff anstrebt
            this.vel = this.vel.mult(0.95f).add(velmax.mult(0.05f));
            this.accelerating = true;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_A)){
            if(this.cooldownTurretakt <= 0) {
                this.cooldownTurretakt = this.cooldownTurret;
                this.direction = this.direction.normalize();
                PVector bulletdirection = new PVector(this.direction.x, this.direction.y);
                bulletdirection.mult(bulletVelocity);
                this.scene.addObject(new Projektile(this.scene, new PVector(this.pos.x, this.pos.y), new PVector(bulletdirection.x, bulletdirection.y),this));
                AsteroidsApplet.asteroidsApplet.getFxPlayer().playSound(0);
            }
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_P)){
            AsteroidsApplet.asteroidsApplet.switchScene(GameSceneFactory.buildGameScene(GameSceneFactory.HOME));
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_R) && this.amountMPBlast > 0){
            useEMP();
        }
    }

    public void render(){
        //Altes Modell


        //Texture
        PImage texture;
        if(accelerating){
            texture = this.textureAccelerating;
        }else {
            texture = this.textureNormal;
        }
        float alpha = (float) (atan2(AsteroidsApplet.asteroidsApplet.mouseY - this.pos.y, AsteroidsApplet.asteroidsApplet.mouseX - this.pos.x) +89.55);
        AsteroidsApplet.asteroidsApplet.imageMode(CENTER);
        AsteroidsApplet.asteroidsApplet.pushMatrix();
        AsteroidsApplet.asteroidsApplet.translate(this.pos.x, this.pos.y);
        AsteroidsApplet.asteroidsApplet.rotate((alpha));
        AsteroidsApplet.asteroidsApplet.image(texture, 0,0);
        AsteroidsApplet.asteroidsApplet.translate(0,0);
        AsteroidsApplet.asteroidsApplet.popMatrix();

        //XP-Bar
        AsteroidsApplet.asteroidsApplet.color(0,0,0);
        AsteroidsApplet.asteroidsApplet.fill(0);
        AsteroidsApplet.asteroidsApplet.stroke(255);
        AsteroidsApplet.asteroidsApplet.rect(0,0,AsteroidsApplet.asteroidsApplet.width-1,30);
        AsteroidsApplet.asteroidsApplet.fill(85, 107, 47);
        AsteroidsApplet.asteroidsApplet.rect(1F, 1F, (float) (AsteroidsApplet.asteroidsApplet.width*exp/100/Math.pow(1.5,level)),28);
        AsteroidsApplet.asteroidsApplet.fill(250,0,0);
        AsteroidsApplet.asteroidsApplet.text("Level: "+this.level, (float)AsteroidsApplet.asteroidsApplet.width/2, (float) AsteroidsApplet.asteroidsApplet.height/40 );
        AsteroidsApplet.asteroidsApplet.fill(250,250,40);
        AsteroidsApplet.asteroidsApplet.text("HP: "+this.hp,(float)AsteroidsApplet.asteroidsApplet.width/20,(float) AsteroidsApplet.asteroidsApplet.height/40);
        AsteroidsApplet.asteroidsApplet.text("Aktive Items: "+this.items.size(),(float)AsteroidsApplet.asteroidsApplet.width/20,(float) AsteroidsApplet.asteroidsApplet.height/40*4);

        if(this.amountMPBlast == 0){
            AsteroidsApplet.asteroidsApplet.text("Amount EMPs: " + this.amountMPBlast, (float) AsteroidsApplet.asteroidsApplet.width / 2, (float) AsteroidsApplet.asteroidsApplet.height / 40 * 4);
        }else {
            AsteroidsApplet.asteroidsApplet.text("Amount EMPs: " + this.amountMPBlast + " (press R)", (float) AsteroidsApplet.asteroidsApplet.width / 2, (float) AsteroidsApplet.asteroidsApplet.height / 40 * 4);
        }

        if(AsteroidsApplet.asteroidsApplet.isDebugModeOn()){
            AsteroidsApplet.asteroidsApplet.color(255,0,0);
            AsteroidsApplet.asteroidsApplet.stroke(255,0,0);
            AsteroidsApplet.asteroidsApplet.fill(250,130,145);
            AsteroidsApplet.asteroidsApplet.ellipse(pos.x,pos.y,30,30);
            AsteroidsApplet.asteroidsApplet.line(pos.x,pos.y,pos.x+direction.x*20,pos.y+direction.y*20);
        }
    }

    @Override
    public void process() {
        if(!Keyboard.isKeyPressed(KeyEvent.VK_SPACE)){
            this.accelerating = false;
        }
        if(AsteroidsApplet.asteroidsApplet.getGameRuleBoolean(10)){
            this.invincible = true;
            amountInvincFramesLeft = 10000;
        }


        //facing Turret

        PVector v1 = new PVector(AsteroidsApplet.asteroidsApplet.mouseX - this.pos.x,AsteroidsApplet.asteroidsApplet.mouseY - this.pos.y);// schlecht programmiert posy   and posx direkt abgefragt
        v1 = v1.normalize(); // SOLL RICHTUNG
        //System.out.println("Debug: Heading of connection vector Mouse,Spaceship "+v1.heading());
        float theta = v1.heading()-direction.heading();
        //System.out.println("Debug: theta Spaceship " + theta);
        if(theta > Math.PI){
            theta = (float)(theta - 2*Math.PI);
        }
        if(theta < -Math.PI){
            theta = (float) (theta + 2*Math.PI);
        }
        //System.out.println("Debug: theta Spaceship " + theta);
        if(theta> turretTurnVelocity){
            theta = (float)Math.PI *turretTurnVelocity;
            //System.out.println("Debug: Spaceship turning right");
        }
        if(theta< -turretTurnVelocity){
            //System.out.println("Debug: Spaceship turning left");
            theta = -1*(float)Math.PI *turretTurnVelocity;
        }
        //System.out.println("Debug: Turret turning velocity Spaceship " + turretTurnVelocity);
        //System.out.println("Debug: theta Spaceship " + theta);
        direction = direction.rotate((float) ( theta));
        this.direction = this.direction.normalize();

        //Movement

        if(!this.wPressed){
            this.vel = this.vel.mult(0.95f);
        }
        this.pos.add(vel);
        //System.out.println("Debug: Velx Spaceship: " + vel.x +" Vely Spaceship: " + vel.y);
        this.wPressed = false;

        //Turret Cooldown

        this.cooldownTurretakt = this.cooldownTurretakt -1;
        this.scene.getEventbus().spaceshipMoved(new PVector(this.pos.x, this.pos.y));

        if(invincible){
            AsteroidsApplet.asteroidsApplet.text("Invincibel ", AsteroidsApplet.asteroidsApplet.width/2, AsteroidsApplet.asteroidsApplet.height/2);
            amountInvincFramesLeft --;
            if(amountInvincFramesLeft <= 0){
                invincible = false;
                amountInvincFramesLeft = amountInvincFrames;
            }
        }

        if(!canUseEmp){
            AsteroidsApplet.asteroidsApplet.image(textureEMP,this.pos.x,this.pos.y);
            amountCooldownFramesLeft --;
            if(amountCooldownFramesLeft <= 0){
                canUseEmp= true;
                amountCooldownFramesLeft = 5;
            }
        }

        if(this.hp == 0){
            this.getHighScore();
            if(this.level > this.highestLevel){
                this.highestLevel = this.level;
            }
            if(this.exp > this.highestExperience){
                this.highestExperience = (int) this.exp;
            }
            this.setHighScore();
            AsteroidsApplet.asteroidsApplet.switchScene(GameSceneFactory.buildGameScene(2));
        }

        this.scene.getEventbus().playerLevelListen(this.level, this.exp);

        //Items
        for(Item item : items) {
            if(item.getType() == 1){
                this.invincible = true;
                this.amountInvincFramesLeft = 2;
                AsteroidsApplet.asteroidsApplet.circle(this.pos.x, this.pos.y, 100);
            }

        }

        //WICHTIG NACH DEM DARÜBER
        this.items.removeAll(itemsToDelete);
        this.itemsToDelete = new ArrayList<>();
    }

    @Override
    public void astroidMoved(Asteroid asteroid) {
        PVector astpos = new PVector(asteroid.getPos().x,asteroid.getPos().y);
        if(astpos.sub(this.pos).mag() < asteroid.getSize() + this.size){
            //AsteroidsApplet.asteroidsApplet.background(255,0,0);
            this.drainHp(1);
            //System.out.println("Debug: Spaceship hit bei an Asteroid");
        }
    }


    public PVector getPos(){return this.pos; }

    public void setPos(PVector pos){this.pos = pos;}

    @Override
    public void spaceshipProjektileHit(int exp) {
        this.exp = this.exp + exp;
        if(this.exp >= 100* (Math.pow(1.5,level))){
            this.exp = 0;
            this.level++;
        }
    }

    @Override
    public void alienUFOMoved(AlienUFO ufo) {
        PVector ufpos = new PVector(ufo.getPos().x,ufo.getPos().y);
        if(ufpos.sub(this.pos).mag() < (float) ufo.getSize() /2 + (float) this.size /2){
            //AsteroidsApplet.asteroidsApplet.background(255,0,0);
            this.drainHp(1);
            //System.out.println("Debug: Spaceship hit bei an Asteroid");
        }
    }

    public int getLevel(){return this.level;}

    public float getExp(){return this.exp;}

    @Override

    public void spaceshipProjektileMoved(PVector pos, Projektile projektile, GameObject shooter) {
        if(pos.dist(this.pos) < this.size && shooter.getClass() == AlienUFO.class){
            System.out.println("\\u001B[31mDebug: Projectile hit the Player");
            this.scene.deleteObject(projektile);
            this.scene.getEventbus().deleteSpaceshipProjektileMovedListener(projektile);
            //AsteroidsApplet.asteroidsApplet.background(255,0,0);
            this.drainHp(1);

        }
    }

    public void drainHp(int amount){
        int isSheeld = -1;
        for(int i = 0; i< items.size(); i++){
            if(items.get(i).getType() == 1){
                isSheeld = i;
                System.out.println("Gefunge, isSheeld: "+isSheeld);
                break;
            }
        }
        if(isSheeld != -1){
            items.remove(isSheeld);
            System.out.println("Schild gelöscht");
            this.amountInvincFramesLeft = 10;
        }
        if(!this.invincible && this.amountInvincFramesLeft == amountInvincFrames && isSheeld == -1){
            this.hp -= amount;
            this.invincible = true;
            this.setPos(new PVector((float) AsteroidsApplet.asteroidsApplet.width /2, (float) AsteroidsApplet.asteroidsApplet.height /2));
            //Delete all items
            items = new ArrayList<>();

            AsteroidsApplet.asteroidsApplet.getFxPlayer().playSound(3);
        }
    }

    public void increaseHp(int amount){this.hp += amount;}

    private void getHighScore(){
        String[] highString = AsteroidsApplet.asteroidsApplet.loadStrings(AsteroidsApplet.ADRESS_TO_SPACE+"scores.txt");
        String[] cache = highString[0].replaceAll("\\s+","").split(":");
        this.highestLevel = Integer.parseInt(cache[0]);
        this.highestExperience = Integer.parseInt(cache[1]);
    }

    public void setHighScore(){

        String cache =  str( this.highestLevel)+" : "+str(this.highestExperience);
        AsteroidsApplet.asteroidsApplet.getWriterLine().writeToLine(AsteroidsApplet.ADRESS_TO_SPACE+"scores.txt",0,cache);
    }

    public int getHighestLevel(){return this.highestLevel;}

    public int getHighestExperience(){return this.highestExperience;}

    public void setHighestLevel(int amount){
        this.highestLevel = amount;
    }
    public void setHighestExperience(int amount){
        this.highestExperience = amount;
    }

    @Override
    public void itemPickedUp(Item item) {
        if(item.getType() == 1){
            this.items.add(item);
        }
        if(item.getType() == 2){
            this.amountMPBlast ++;
        }
    }

    @Override
    public void itemTimeUp(Item item) {
            this.itemsToDelete.add(item);
    }

    public void useEMP(){

        if(this.canUseEmp) {

           // AsteroidsApplet.asteroidsApplet.circle(this.pos.x, this.pos.y, this.empSize);
            this.amountMPBlast--;
            for (GameObject object : scene.getGameObjects()) {
                if (object.getClass() == Asteroid.class) {
                    PVector pos = object.getPos();
                    if (pos.dist(this.pos) < empSize) {
                        ((Asteroid) object).asteroidHit();
                    }
                }
                if (object.getClass() == AlienUFO.class) {
                    PVector pos = object.getPos();
                    if (pos.dist(this.pos) < empSize) {
                        ((AlienUFO) object).ufoHit();
                    }
                }

            }
            this.amountCooldownFramesLeft = 5;
            canUseEmp = false;

        }
    }
}
