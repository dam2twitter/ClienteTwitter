package logica;

import java.io.File;

public class LogicaNegocio {
    
   private static LogicaNegocio INSTANCE; 
   private LoginUsuario loginUsuario;
   private ConfiguracionApp configuracionApp;
   private SesionUsuario sesionUsuario;
   File ficheroDatos;

    private LogicaNegocio() {
    }

    public static LogicaNegocio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogicaNegocio();
        }
        return INSTANCE;
    }
    
    
}
