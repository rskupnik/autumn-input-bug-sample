package com.github.rskupnik;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.czyzby.autumn.mvc.application.AutumnApplication;
import com.github.czyzby.autumn.scanner.ClassScanner;

public class Sample extends AutumnApplication {

	public Sample(ClassScanner componentScanner, Class<?> scanningRoot) {
		super(componentScanner, scanningRoot);
	}
}
