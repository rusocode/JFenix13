package org.gszone.jfenix13.general;

import java.io.*;
import java.util.Properties;

import static org.gszone.jfenix13.general.FileNames.getDtConfigDir;

/**
 * Configuraciones especificas para escritorio (esta clase se usa UNICA y EXCLUSIVAMENTE para escritorio)
 *
 * width, height: Ancho y alto de la pantalla en donde se ejecuta.
 * decorated: Indica si hay que mostrar los bordes y barra de titulo de la pantalla.
 * resizable: Indica si se puede redimensionar la pantalla.
 * fullscreen: Determina si se tiene que ejecutar en pantalla completa.
 * vSync: Indica la sincronizacion vertical.
 *
 */
public class DtConfig {

	public static int width;
	public static int height;
	public static boolean decorated;
	public static boolean resizable;
	public static boolean fullscreeen;
	public static boolean vSync;

	/**
	 * Carga la configuracion desde el archivo.
	 */
	public static void loadConfig() {
		File configFile = new File(System.getProperty("user.home") + getDtConfigDir());
		configFile.getParentFile().mkdir();

		try {
			configFile.createNewFile();
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			// El metodo getProperty() devuelve el argumento de valor predeterminado si no se encuentra la propiedad
			width = Integer.parseInt(props.getProperty("width", "1024"));
			height = Integer.parseInt(props.getProperty("height", "768"));
			decorated = Boolean.parseBoolean(props.getProperty("decorated", "false"));
			resizable = Boolean.parseBoolean(props.getProperty("resizable", "false"));
			fullscreeen = Boolean.parseBoolean(props.getProperty("fullscreeen", "false"));
			vSync = Boolean.parseBoolean(props.getProperty("vSync", "true"));

			reader.close();

		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Guarda la configuracion en el archivo.
	 */
	public static void saveConfig() {
		File configFile = new File(System.getProperty("user.home") + getDtConfigDir());
		configFile.getParentFile().mkdir();

		try {
			FileWriter writer = new FileWriter(configFile);
			Properties props = new Properties();
			props.setProperty("width", "" + width);
			props.setProperty("height", "" + height);
			props.setProperty("decorated", "" + decorated);
			props.setProperty("resizable", "" + resizable);
			props.setProperty("fullscreeen", "" + fullscreeen);
			props.setProperty("vSync", "" + vSync);

			props.store(writer, "");
			writer.flush();
			writer.close();

		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
