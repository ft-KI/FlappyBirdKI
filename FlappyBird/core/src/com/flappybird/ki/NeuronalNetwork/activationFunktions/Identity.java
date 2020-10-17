package com.flappybird.ki.NeuronalNetwork.activationFunktions;

public class Identity implements ActivationFunktion{

    public float activation(float input) {
        return input;
    }

    @Override
    public float derivative(float value) {
        return 1;
    }

}
