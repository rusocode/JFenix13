package org.gszone.jfenix13.managers;

import org.gszone.jfenix13.actors.*;

public class PrincipalManager extends ViewManager {

    public PrincipalManager() {

    }

    public void desconectar() {
        getGD().getCommands().parse("/SALIR");
    }

    public Consola getConsola() {
        return getGD().getConsola();
    }

    public World getWorld() {
        return getGD().getWorld();
    }

    public Grid<Item> getInv() {
        return getGD().getInventario();
    }

    public void parseCommand(String command) {
        getGD().getCommands().parse(command);
    }
}
