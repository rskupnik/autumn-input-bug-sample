package com.github.rskupnik;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.czyzby.autumn.annotation.Initiate;
import com.github.czyzby.autumn.annotation.Inject;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewRenderer;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewResizer;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewShower;
import com.github.czyzby.autumn.mvc.stereotype.View;
import com.github.czyzby.lml.parser.action.ActionContainer;

@View(id = "sample", value = "templates/sample.lml", first = true)
public class SampleController implements ViewRenderer, ActionContainer, ViewResizer, ViewShower {

    private SampleRenderer rendererInitiated;

    // BUG: This will not work as explained in init()
    //@Inject
    //private SampleRenderer rendererInjected;

    @Initiate
    public void init() {
        // BUG: This will not work as the stage will overwrite your InputProcessor in show()
        //rendererInitiated = new SampleRenderer();
    }

    @Override
    public void render(Stage stage, float delta) {
        stage.act(delta);
        stage.draw();
        if (rendererInitiated != null)
            rendererInitiated.render(delta);
    }

    @Override
    public void resize(Stage stage, int width, int height) {

    }

    // FIX: Implement ViewShower and initiate your custom renderer/inputprocessor inside show()
    @Override
    public void show(Stage stage, Action action) {
        stage.addAction(Actions.sequence(action, Actions.run(new Runnable() {
            @Override
            public void run() {
                rendererInitiated = new SampleRenderer();
            }
        })));
    }

    @Override
    public void hide(Stage stage, Action action) {
        stage.addAction(action);
    }
}
