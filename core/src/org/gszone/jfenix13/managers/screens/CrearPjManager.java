package org.gszone.jfenix13.managers.screens;

import org.gszone.jfenix13.objects.UserAtributos;
import org.gszone.jfenix13.utils.Dialogs;
import org.gszone.jfenix13.utils.StrUtils;
import org.gszone.jfenix13.views.screens.MenuView;

public class CrearPjManager extends ViewManager {

    public CrearPjManager() {

    }

    @Override
    public void back() {
        setScreen(new MenuView());
    }

    public void solicTirarDados() {
        getClPack().writeThrowDices();
    }

    public UserAtributos getAttribActuales() {
        return getGD().getCurrentUser().getAtributos();
    }

    public void crearPj(String nombre, String contrase�a, String rContrase�a, String mail,
                        String rMail, int raza, int genero, int ciudad) {

        if (nombre.equals("")) {
            Dialogs.showOKDialog(bu("error"), "El nombre no puede estar vacío.");
            return;
        }

        if (contrase�a.equals("")) {
            Dialogs.showOKDialog(bu("error"), "La contraseña no puede estar vacía.");
            return;
        }

        Character c = StrUtils.getInvalidChar(contrase�a);
        if (c != null) {
            Dialogs.showOKDialog(bu("error"), "Contraseña inválida. No se permite el caractér '" + c + "'.");
            return;
        }

        if (mail.equals("")) {
            Dialogs.showOKDialog(bu("error"), "El mail no puede estar vacío.");
            return;
        }

        // TODO: Considerar chequear mail no válido (CheckMailString de VB6)

        if (!contrase�a.equals(rContrase�a)) {
            Dialogs.showOKDialog(bu("error"), "Las contraseñas no coinciden.");
            return;
        }

        if (!mail.equals(rMail)) {
            Dialogs.showOKDialog(bu("error"), "Los mails no coinciden.");
            return;
        }


        getClPack().writeLoginNewChar(nombre, contrase�a, mail,
                raza, genero, ciudad);
    }

}
