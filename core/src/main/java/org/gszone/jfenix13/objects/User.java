package org.gszone.jfenix13.objects;

import org.gszone.jfenix13.utils.Rect;

/**
 * Representa al usuario actual
 *
 * indexInServer: es el número de index del pj actual (sirve para buscarlo entre los chars).
 * cambiandoDir: indica si el usuario inició la petición de cambiar la dirección. (Si no se chequea de esta forma,
 *               desde que se hace la petición hasta que se recibe el cambio, se harían varias peticiones y esto
 *               generaría lag).
 * area: contiene info de los límites del área actual, (y permite cambiar de área)
 *
 * los demás atributos se entienden a simple vista:
 */
public class User {
    private int index;
    private int indexInServer;
    private String name;
    private int map;
    private boolean paralizado;
    private boolean descansando;
    private boolean meditando;
    private boolean navegando;
    private boolean cambiandoDir;
    private UserStats stats;
    private UserAtributos atributos;


    private Rect area;

    public User() {
        area = new Rect();
        stats = new UserStats();
        atributos = new UserAtributos();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndexInServer() {
        return indexInServer;
    }

    public void setIndexInServer(int indexInServer) {
        this.indexInServer = indexInServer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public boolean isParalizado() {
        return paralizado;
    }

    public void setParalizado(boolean paralizado) {
        this.paralizado = paralizado;
    }

    public boolean isDescansando() {
        return descansando;
    }

    public void setDescansando(boolean descansando) {
        this.descansando = descansando;
    }

    public boolean isMeditando() {
        return meditando;
    }

    public void setMeditando(boolean meditando) {
        this.meditando = meditando;
    }

    public boolean isNavegando() {
        return navegando;
    }

    public void setNavegando(boolean navegando) {
        this.navegando = navegando;
    }

    public boolean isCambiandoDir() {
        return cambiandoDir;
    }

    public void setCambiandoDir(boolean cambiandoDir) {
        this.cambiandoDir = cambiandoDir;
    }

    public UserStats getStats() {
        return stats;
    }

    public UserAtributos getAtributos() {
        return atributos;
    }

    public Rect getArea() { return area; }
}