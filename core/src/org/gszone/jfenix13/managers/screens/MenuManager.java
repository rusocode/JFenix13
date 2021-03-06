package org.gszone.jfenix13.managers.screens;

import org.gszone.jfenix13.utils.Dialogs;
import org.gszone.jfenix13.views.screens.CrearPjView;

public class MenuManager extends ViewManager {

    public MenuManager() {

    }

    public void conectar(String nombre, String contrase�a) {
        if (nombre.equals(""))
            Dialogs.showOKDialog("Error", "El nombre no puede estar vacío.");
        else if (contrase�a.equals(""))
            Dialogs.showOKDialog("Error", "La contraseña no puede estar vacía");
        else {
            if (getConnection().connect())
                getClPack().writeLoginExistingChar(nombre, contrase�a);
        }
    }

    public void crearPj() {
        if (getConnection().connect()) {
            setScreen(new CrearPjView());
            getClPack().writeThrowDices();
        }
    }
}
