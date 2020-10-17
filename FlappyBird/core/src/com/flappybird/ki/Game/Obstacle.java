package com.flappybird.ki.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flappybird.ki.Main;

public class Obstacle {
    int x=0;
    int y=0;
    int height=0;
    int holeheight=0;
    int width=0;
    int holesize=150;
    public Obstacle(int x, int y , int holeheight, int w, int height, int holesize){
        this.x=x;
        this.y=y;
        this.holeheight=holeheight;
        this.width=w;
        this.holesize=holesize;
        this.height=height;
    }
    public void draw(){
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        Main.shapeRenderer.rect(x,y,width,holeheight+y);
        Main.shapeRenderer.rect(x,holeheight+holesize+y,width, height-(holeheight+holesize));
        Main.shapeRenderer.end();
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
