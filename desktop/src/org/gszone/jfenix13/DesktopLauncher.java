package org.gszone.jfenix13;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.gszone.jfenix13.general.DtConfig;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import static org.gszone.jfenix13.general.DtConfig.*;

/** Launches the desktop (LWJGL) application. */
public class DesktopLauncher {

	public static void main(String[] args) {

		// Se define un trozo de codigo encargado de reiniciar el juego
		final Runnable rebootable = new Runnable() {
			@Override
			public void run() {
				if (Gdx.app != null) Gdx.app.exit();
				start();
			}
		};

		// Crea la aplicacion
		createLwjglApplication(new Main(rebootable));
	}

	/**
	 * Devuelve la aplicacion ya lista con todas sus configuraciones.
	 */
	private static LwjglApplication createLwjglApplication(Main main) {

		DtConfig.loadConfig();

		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		if (!decorated) System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = width;
		config.height = height;
		config.resizable = resizable;
		config.fullscreen = fullscreeen;

		for (int size : new int[] { 128, 64, 32, 16 }) {
			// config.addIcon("icon" + size + ".png", Files.FileType.Internal);
		}

		if (!vSync) {
			config.vSyncEnabled = false;
			config.foregroundFPS = 0;
			config.backgroundFPS = 0;
		}

		return new LwjglApplication(main, config);
	}

	/**
	 * Inicia la aplicacion nuevamente (inserta un nuevo comando, indicando de abrir el juego)
	 */
	public static void start() {
		final StringBuilder cmd = new StringBuilder();
		cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
		for (final String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments()) {
			cmd.append(jvmArg + " ");
		}
		cmd.append("-cp \"").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append("\" ");
		cmd.append(DesktopLauncher.class.getName()).append(" ");

		try {
			System.out.println(cmd.toString());
			Runtime.getRuntime().exec(cmd.toString());
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}