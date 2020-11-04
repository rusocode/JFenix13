package org.gszone.jfenix13.managers.screens;

import org.gszone.jfenix13.utils.Dialogs;
import org.gszone.jfenix13.views.screens.CrearPjView;

public class MenuManager extends ViewManager {

    public MenuManager() {

    }

    public void conectar(String nombre, String contraseña) {
        if (nombre.equals(""))
            Dialogs.showOKDialog("Error", "El nombre no puede estar vacÃ­o.");
        else if (contraseña.equals(""))
            Dialogs.showOKDialog("Error", "La contraseÃ±a no puede estar vacÃ­a");
        else {
            if (getConnection().connect())
                getClPack().writeLoginExistingChar(nombre, contraseña);
        }
    }

    public void crearPj() {
        if (getConnection().connect()) {
            setScreen(new CrearPjView());
            getClPack().writeThrowDices();
        }
    }
}
