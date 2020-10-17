package com.flappybird.ki.Game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flappybird.ki.Game.birds.Bird;
import com.flappybird.ki.Main;

import java.util.ArrayList;

public class FlappyBirdGame {
    ArrayList<Obstacle>obstacles=new ArrayList<>();
    ArrayList<Bird>birds=new ArrayList<>();
    int Xposition=0;
    int Yposition=0;
    int weight=0;
    int height=0;
    int worldposition=0;
    int oldworldposition=-400;
    int holesize=150;
    int obstacclewide=40;
    public FlappyBirdGame(int x,int y,int w,int h){
        this.Xposition=x;
        this.Yposition=y;
        this.weight=w;
        this.height=h;

    }
    void createObstacles(){
        if(worldposition>oldworldposition+300) {
            oldworldposition=worldposition;
            Obstacle test = new Obstacle(Xposition + weight,this.Yposition, (int) (Math.random() * (height-holesize-50)+50), obstacclewide,this.height,holesize);
            obstacles.add(test);
        }
    }
    public void draw(){
        createObstacles();
        Main.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Main.shapeRenderer.rect(this.Xposition,this.Yposition,this.weight,this.height);
        Main.shapeRenderer.end();
        for(int i=0;i<obstacles.size();i++){
            obstacles.get(i).draw();
            obstacles.get(i).setX(obstacles.get(i).getX()-1);
            if(obstacles.get(i).getX()<Xposition-obstacclewide){
                obstacles.remove(i);
            }
        }
        worldposition+=1;
    }
}
