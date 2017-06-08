package com.github.rskupnik;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.czyzby.autumn.annotation.Initiate;
import com.github.czyzby.autumn.annotation.Inject;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewRenderer;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewResizer;
import com.github.czyzby.autumn.mvc.stereotype.View;
import com.github.czyzby.lml.parser.action.ActionContainer;

@View(id = "sample", value = "templates/sample.lml", first = true)
public class SampleController implements ViewRenderer, ActionContainer, ViewResizer {

    private SampleRenderer rendererInitiated;

    //@Inject
    //private SampleRenderer rendererInjected;

    @Initiate
    public void init() {
        rendererInitiated = new SampleRenderer();
    }

    @Override
    public void render(Stage stage, float delta) {
        stage.act(delta);
        stage.draw();
        rendererInitiated.render(delta);
    }

    @Override
    public void resize(Stage stage, int width, int height) {

    }
}
