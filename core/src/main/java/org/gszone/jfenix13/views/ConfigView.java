package org.gszone.jfenix13.views;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.*;
import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPane;
import org.gszone.jfenix13.general.DtConfig;
import org.gszone.jfenix13.managers.ConfigManager;

import static org.gszone.jfenix13.utils.Actors.*;



public class ConfigView extends View {

    public ConfigView() {
        super(new ConfigManager());
    }
    public ConfigManager getGestor() { return (ConfigManager)gestor; }

    // Pestaña Video
    private VisCheckBox cbVSync;
    private VisRadioButton rbModoVentana;
    private VisRadioButton rbPantCompleta;
    private VisTable tModoVentana;
    private VisSelectBox<String> sbRes;
    private VisCheckBox cbTitleBar;
    private VisCheckBox cbRedimensionable;

    // Pestaña Audio
    private VisCheckBox cbMusica;
    private VisCheckBox cbSonido;

    private VisSlider slVolMusica;
    private VisSlider slVolSonido;


    private VisTextButton tbAtras;
    private VisTextButton tbGuardar;

    @Override
    public void show() {
        super.show();

        //stage.setDebugAll(true);

        VisWindow w = newWindow(bu("cf.title"), null, false, false);
        TabbedPane panel = newTabbedPane(w, 400, 400).getActor().getTabbedPane(); w.row();

        // Referencia general para cada tabla de cada pestaña
        Table t;

        // La pestaña de video está disponible solo para escritorio
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            // Pestaña 'Video'
            t = newTab(panel, "Video").getContentTable();
                cbVSync = newCheckBox(t, "Sincronización vertical").left().getActor();
                t.row();

                rbPantCompleta = newRadioButton(t, "Pantalla Completa").left().getActor(); t.row();
                rbModoVentana = newRadioButton(t, "Modo Ventana").left().getActor(); t.row();
                new ButtonGroup<>(rbPantCompleta, rbModoVentana);

                // Tabla interna
                tModoVentana = newTable(t, "transparente2", true).left().padLeft(8).getActor(); tModoVentana.pad(8);
                newLabel(tModoVentana, "Resolución:", "col-title", "smallgradient").left().spaceBottom(0).row();
                sbRes = newSelectBox(tModoVentana, "800 x 600", "1024 x 768 (recomendado)", "1200 x 900").getActor(); tModoVentana.row();

                cbTitleBar = newCheckBox(tModoVentana, "Barra de título").left().getActor(); tModoVentana.row();
                cbRedimensionable = newCheckBox(tModoVentana, "Redimensionable").left().getActor(); tModoVentana.row();
        }

        // Pestaña 'Audio'
        t = newTab(panel, "Audio").getContentTable();
            cbMusica = newCheckBox(t, "Música").left().getActor();
            slVolMusica = newSlider(t, 0, 1, 0.05f, false).right().getActor();

            t.row();

            cbSonido = newCheckBox(t, "Sonido").left().getActor();
            slVolSonido = newSlider(t, 0, 1, 0.05f, false).right().getActor();


        // TODO: Agregar el resto de Tabs

        Table t2 = newTable(w).padTop(40).colspan(3).getActor();
            tbAtras = newTextButton(t2, bu("back")).getActor();
            tbGuardar = newTextButton(t2, "Guardar").getActor();

        fitWindow(w);

        // Acciones de los elementos:
        ClickListener l = new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                tModoVentana.setVisible(!rbPantCompleta.isChecked());
            }
        };
        rbPantCompleta.addListener(l);
        rbModoVentana.addListener(l);

        tbAtras.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                getGestor().back();
            }
        });

        tbGuardar.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                getGestor().guardar(cbVSync.isChecked(), rbPantCompleta.isChecked(), sbRes.getSelectedIndex(),
                            cbTitleBar.isChecked(), cbRedimensionable.isChecked());
            }
        });


        // Actualizo el panel de Video con la configuración actual

        // Configuraciones EXCLUSIVAS para Escritorio
        // GWTIncompatible (comentar el if entero para que compile en GWT)
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {


            if (DtConfig.vSync) cbVSync.setChecked(true);

            if (DtConfig.fullscreeen) rbPantCompleta.setChecked(true);
            else rbModoVentana.setChecked(true);

            tModoVentana.setVisible(!rbPantCompleta.isChecked());

            if (DtConfig.width == 800) sbRes.setSelectedIndex(0);
            if (DtConfig.width == 1024) sbRes.setSelectedIndex(1);
            if (DtConfig.width == 1200) sbRes.setSelectedIndex(2);

            if (DtConfig.decorated) cbTitleBar.setChecked(true);
            if (DtConfig.resizable) cbRedimensionable.setChecked(true);
        }

        // Actualizo el panel de Audio con la configuración actual
        cbMusica.setChecked(getGestor().getConfig().isMusicActive());
        slVolMusica.setValue(getGestor().getConfig().getMusicVol());

        cbSonido.setChecked(getGestor().getConfig().isSoundActive());
        slVolSonido.setValue(getGestor().getConfig().getSoundVol());

    }


}
