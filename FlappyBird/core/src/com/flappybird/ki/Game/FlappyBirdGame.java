package com.flappybird.ki.Game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.flappybird.ki.Game.birds.Bird;
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
    int holesize=160;
    int obstacclewide=40;
    int actualObstacleIndex=0;
    int birdsX=100;
    int populationsize=20;
    int generation=1;
    int record = 0;
    public FlappyBirdGame(int x,int y,int w,int h){
        this.Xposition=x;
        this.Yposition=y;
        this.weight=w;
        this.height=h;
        createNewRandomPopulation(populationsize);
    }
    void createObstacles(){
        if(worldposition>oldworldposition+250) {
            oldworldposition=worldposition;
            Obstacle test = new Obstacle(Xposition + weight,this.Yposition, (int) (Math.random() * (height-holesize-50)+50), obstacclewide,this.height,holesize);
            obstacles.add(test);
        }
    }
    public void draw(){
        if(worldposition>record) record =worldposition;

        System.out.println("generation: "+generation+" birdsalive: "+birds.size()+" Rekord: "+record+" Progress: "+worldposition);
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
            obstacles.get(i).setX(obstacles.get(i).getX()-3);
            if(obstacles.get(i).getX()<Xposition-obstacclewide){
                obstacles.remove(i);
                i--;
            }
        }
        for(int i = obstacles.size() - 1; i >= 0; i--){
            if(obstacles.get(i).getX()>birdsX+Xposition-obstacclewide){
                actualObstacleIndex=i;
            }
        }
        for(int i=0;i<birds.size();i++){
            boolean hadkilled=false;
            birds.get(i).draw();
            if(birds.get(i).getY()<this.Xposition){
                birds.get(i).kill();
                hadkilled=true;
            }
                if (birds.get(i).getX()+birds.get(i).getRadius() > obstacles.get(actualObstacleIndex).getX()) {
                    if (birds.get(i).getY()-birds.get(i).getRadius() < obstacles.get(actualObstacleIndex).getHoleheight() + Yposition || birds.get(i).getY()+birds.get(i).getRadius() > obstacles.get(actualObstacleIndex).getHoleheight() + Yposition + obstacles.get(actualObstacleIndex).getHolesize()) {
                        birds.get(i).kill();
                        hadkilled=true;
                    }
                }
                if(hadkilled){if(birds.size()!=1){birds.remove(i);i--;}};

        }

        if(birds.size()<=1){
            generation++;
            birds.get(0).setFitness(worldposition);
            //System.out.println(worldposition);
            createPopulation(birds.get(0),populationsize);
            resetWorld();
        }


        worldposition+=3;
    }
    public void createPopulation(Bird mother,int anzahl){
        for(int i=0;i<anzahl;i++) {
            Bird kibird = new KIBird(mother);
            kibird.setWorld(this);
            kibird.setX(Xposition + birdsX);
            kibird.setY(Xposition + height / 2);
            birds.add(kibird);
        }
    }
    public void createNewRandomPopulation(int anzahl){
        for(int i=0;i<anzahl;i++) {
            Bird kibird = new KIBird();
            kibird.setWorld(this);
            kibird.setX(Xposition + birdsX);
            kibird.setY(Xposition + height / 2);
            birds.add(kibird);
        }
    }
    public void resetWorld(){
        obstacles.clear();
        worldposition=0;
        oldworldposition=-400;
        actualObstacleIndex=0;
        worldposition=0;
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

    public int getObstacclewide() {
        return obstacclewide;
    }

    public int getBirdsX() {
        return birdsX;
    }

    public int getXposition() {
        return Xposition;
    }

    public int getYposition() {
        return Yposition;
    }
}
