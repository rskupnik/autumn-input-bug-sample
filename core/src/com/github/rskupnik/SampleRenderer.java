package com.github.rskupnik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

//@Component
public class SampleRenderer {

    public SampleRenderer() {

        InputProcessor existingProcessor = Gdx.input.getInputProcessor();
        InputMultiplexer mult = null;
        if (existingProcessor == null)
            mult = new InputMultiplexer();
        else {
            if (existingProcessor instanceof InputMultiplexer)
                mult = (InputMultiplexer) existingProcessor;
            else {
                mult = new InputMultiplexer();
                mult.addProcessor(existingProcessor);
            }
        }
        mult.addProcessor(new SampleInputProcessor());
        Gdx.input.setInputProcessor(mult);
    }

    public void render(float delta) {
        // Standard rendering here
    }
}
