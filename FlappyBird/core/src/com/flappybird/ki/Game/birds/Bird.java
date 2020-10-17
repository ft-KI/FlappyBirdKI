package com.flappybird.ki.Game.birds;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flappybird.ki.Main;

public abstract class Bird {
    float x=0;
    float y=0;
    int fitness=0;
    float fallspeed=0;
    boolean iskilled=false;
    public void flap(){
        fallspeed=-7;
    }
    abstract void update();
    public void draw(){
        if(!iskilled){
            Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            Main.shapeRenderer.setColor(0,1,0,1);
            Main.shapeRenderer.circle(this.x,this.y,11);
            Main.shapeRenderer.setColor(0.8f, 0.8f, 0, 1);
            Main.shapeRenderer.circle(this.x,this.y,10);
            Main.shapeRenderer.end();
            update();
            this.y=this.y-fallspeed;
            fallspeed+=0.5f;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public void kill(){
        iskilled=true;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
