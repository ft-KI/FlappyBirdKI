package com.flappybird.ki.Game.birds;

import com.flappybird.ki.NeuronalNetwork.NeuronalNetwork;

public class KIBird extends Bird{
    NeuronalNetwork brain = new NeuronalNetwork();
    public KIBird(){
        brain.createInputNeurons(3);
        brain.addHiddenLayer(2);
        brain.createOutputtNeurons(1);
        brain.connectRandomFullMeshed();
    }
    @Override
    void update() {

    }
}
