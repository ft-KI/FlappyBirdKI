package com.flappybird.ki.Game.birds;

import com.flappybird.ki.Main;
import com.flappybird.ki.NeuronalNetwork.NeuronalNetwork;

public class KIBird extends Bird{
    NeuronalNetwork brain = new NeuronalNetwork();
    public KIBird(){
        brain.createInputNeurons(2);
        brain.addHiddenLayer(6);
        brain.createOutputtNeurons(1);
        brain.connectRandomFullMeshed();
        brain.addBiasforallNeurons();
    }
    public KIBird(Bird mother) {
        if(mother instanceof KIBird) {
                brain = ((KIBird) mother).getBrain().cloneFullMeshed();
                for(int i=0;i<3;i++){
                    brain.randomMutate(0.23f);
                }
        }
    }
    @Override
    void update() {
        int distancetonextobstacle=((super.getWorld().getObstacles().get(super.getWorld().getActualObstacleIndex()).getX()+super.getWorld().getObstacclewide()/2)-(super.getWorld().getBirdsX()+super.getWorld().getXposition()));
        int distancetomiddelofhole=(super.getWorld().getObstacles().get(super.getWorld().getActualObstacleIndex()).getHoleheight()+super.getWorld().getObstacles().get(super.getWorld().getActualObstacleIndex()).getHolesize()/2+super.getWorld().getYposition())-(int)this.y;
        brain.setInputValues(distancetonextobstacle,distancetomiddelofhole);
        brain.reset();
        if(brain.getOutputNeurons().get(0).getOutputValue()>=0.5f){
            flap();
        }

    }

    public NeuronalNetwork getBrain() {
        return brain;
    }
}
