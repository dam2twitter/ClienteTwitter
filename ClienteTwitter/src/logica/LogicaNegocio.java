package logica;

import java.io.File;

public class LogicaNegocio {

    private static LogicaNegocio INSTANCE;
    private LoginUsuario loginUsuario;
    private ConfiguracionApp configuracionApp;
    private SesionUsuario sesionUsuario;
    File ficheroDatos;

    public static LogicaNegocio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogicaNegocio();
        }
        return INSTANCE;
    }

    public LoginUsuario getLoginUsuario() {
        return loginUsuario;
    }

}
