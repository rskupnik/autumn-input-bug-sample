package com.github.rskupnik;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.czyzby.autumn.annotation.Component;
import com.github.czyzby.autumn.annotation.Initiate;
import com.github.czyzby.autumn.mvc.component.ui.InterfaceService;
import com.github.czyzby.autumn.mvc.component.ui.SkinService;
import com.github.czyzby.autumn.mvc.component.ui.controller.impl.StandardViewResizer;
import com.github.czyzby.autumn.mvc.config.AutumnActionPriority;
import com.github.czyzby.autumn.mvc.stereotype.preference.*;
import com.github.czyzby.autumn.mvc.stereotype.preference.sfx.MusicEnabled;
import com.github.czyzby.autumn.mvc.stereotype.preference.sfx.MusicVolume;
import com.github.czyzby.autumn.mvc.stereotype.preference.sfx.SoundEnabled;
import com.github.czyzby.autumn.mvc.stereotype.preference.sfx.SoundVolume;
import com.github.czyzby.kiwi.util.gdx.asset.lazy.provider.ObjectProvider;
import com.github.czyzby.lml.parser.LmlSyntax;
import com.github.czyzby.lml.vis.parser.impl.VisLmlSyntax;
import com.kotcrab.vis.ui.VisUI;

@Component
public class Configuration {
    /** Path to application's main {@link Preferences}. */
    public static final String PREFERENCES_PATH = "sample";
    /** Virtual viewport size. */
    public static final int WIDTH = 800, HEIGHT = 600;

    /** Path to our default i18n files. */
    @I18nBundle
    String bundlePath;
    /** Custom syntax used by our LML templates. LML allows to parse HTML-like templates to Scene2D actors; in this case
     * - VisUI actors, since we're using gdx-lml-vis extension. If you do not use any extension, default syntax will be
     * used. */
    @LmlParserSyntax
    LmlSyntax syntax = new VisLmlSyntax();
    /** We want to use custom viewports for stages - {@link FitViewport}. This provider will be invoked each time a
     * {@link Stage} needs a {@link Viewport} instance. */
    @StageViewport
    ObjectProvider<Viewport> viewportProvider = new ObjectProvider<Viewport>() {
        @Override
        public Viewport provide() {
            return new FitViewport(WIDTH, HEIGHT);
        }
    };

    /** These sound-related fields allow {@link MusicService} to store settings in preferences file. Sound preferences
     * will be automatically saved when the application closes and restored the next time it's turned on. Sound
     * configuration methods will be automatically added to LML templates - see settings.lml. */
    @SoundVolume(preferences = PREFERENCES_PATH, defaultVolume = 0.75f) String soundVolume = "soundVolume";
    @SoundEnabled(preferences = PREFERENCES_PATH) String soundEnabled = "soundOn";
    @MusicVolume(preferences = PREFERENCES_PATH, defaultVolume = 0.75f) String musicVolume;
    @MusicEnabled(preferences = PREFERENCES_PATH) String musicEnabledPreference = "musicOn";

    /** These i18n-related fields will allow {@link LocaleService} to save game's locale in preferences file. Locale
     * changing actions will be automatically added to LML templates - see settings.lml. */
    @I18nLocale(propertiesPath = PREFERENCES_PATH, defaultLocale = "en") String localePreference = "locale";
    @AvailableLocales
    String[] availableLocales = new String[] { "en", "pl" };

    public Configuration() {
        bundlePath = "i18n/sample";
        musicVolume = "musicVolume";
    }

    /** This action will be invoked when the application is being initialized. Actions are sorted by their priority and
     * invoked in descending order; by manipulating the priority values, you can fully control the flow of your
     * application's initiation.
     *
     * @param skinService its instance will be injected into the method. We're using this service to register VisUI
     *            skin. Again: VisUI is an extension, so we have to register its skin manually. Fortunately, it doesn't
     *            require much work. */
    @Initiate(priority = AutumnActionPriority.TOP_PRIORITY)
    public void initiateSkin(final SkinService skinService) {
        // Loading default VisUI skin with double scale:
        VisUI.load(VisUI.SkinScale.X1);
        // Registering VisUI skin with "default" name - skin with this ID will be used by default if no other ID is
        // specified. Since we're not using multiple skins, we do not have to worry about them in LML templates at all.
        skinService.addSkin("default", VisUI.getSkin());
        // Since we use FitViewports, we need to set a stage resizer that does not center the camera:
        InterfaceService.DEFAULT_VIEW_RESIZER = new StandardViewResizer();
    }
}
