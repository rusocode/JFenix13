package org.gszone.jfenix13.connection;

/**
 * Interfaz que define los metodos que tienen que tener una conexion
 */
public interface Connection {

	ClientPackages getClPack();

	ServerPackages getSvPack();

	/**
	 * Se conecta con el servidor
	 */
	boolean connect();

	/**
	 * Busca en la cola y envia datos al servidor
	 */
	void write();

	/**
	 * Cierra la conexion
	 */
	void dispose();

}
