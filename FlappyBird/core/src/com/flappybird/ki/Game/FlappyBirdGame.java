package com.flappybird.ki.Game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flappybird.ki.Game.birds.Bird;
import com.flappybird.ki.Game.birds.HumanBird;
import com.flappybird.ki.Game.birds.KIBird;
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
    int holesize=180;
    int obstacclewide=40;
    int actualObstacleIndex=0;
    int birdsX=100;
    public FlappyBirdGame(int x,int y,int w,int h){
        this.Xposition=x;
        this.Yposition=y;
        this.weight=w;
        this.height=h;
        Bird testbird=new HumanBird();
        testbird.setX(Xposition+birdsX);
        testbird.setY(Xposition+height/2);
        birds.add(testbird);
        Bird kibird=new KIBird();
        kibird.setWorld(this);
        kibird.setX(Xposition+birdsX);
        kibird.setY(Xposition+height/2);
        birds.add(kibird);

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
        Main.shapeRenderer.setColor(1,1,1,1);
        Main.shapeRenderer.rect(this.Xposition,this.Yposition,this.weight,this.height);
        Main.shapeRenderer.end();


            for(int i=0;i<obstacles.size();i++){
            if(i==actualObstacleIndex){
                Main.shapeRenderer.setColor(0,0.6f,0,1f);
            }else{
                Main.shapeRenderer.setColor(0.5f,0,0,1f);
            }
            obstacles.get(i).draw();
            obstacles.get(i).setX(obstacles.get(i).getX()-1);
            if(obstacles.get(i).getX()<Xposition-obstacclewide){
                obstacles.remove(i);
            }
        }
        for(int i = obstacles.size() - 1; i >= 0; i--){
            if(obstacles.get(i).getX()>birdsX+Xposition-obstacclewide){
                actualObstacleIndex=i;
            }
        }
        for(int i=0;i<birds.size();i++){
            birds.get(i).draw();
            if(birds.get(i).getY()<this.Xposition){
                birds.get(i).kill();
            }
                if (birds.get(i).getX() > obstacles.get(actualObstacleIndex).getX()) {
                    if (birds.get(i).getY() < obstacles.get(actualObstacleIndex).getHoleheight() + Yposition || birds.get(i).getY() > obstacles.get(actualObstacleIndex).getHoleheight() + Yposition + obstacles.get(actualObstacleIndex).getHolesize()) {
                        birds.get(i).kill();
                    }
                }
            }

        worldposition+=1;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    public int getActualObstacleIndex() {
        return actualObstacleIndex;
    }
}
