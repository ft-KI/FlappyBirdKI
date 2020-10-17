package com.flappybird.ki.Game.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class HumanBird extends Bird{
    public HumanBird(){

    }

    @Override
    void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            flap();
        }
    }
}
