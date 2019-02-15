package logica;

import java.io.File;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class LogicaNegocio {

    private static LogicaNegocio INSTANCE;
    private LoginUsuario loginUsuario;
    private ConfiguracionApp configuracionApp;
    File ficheroDatos;
    Twitter admin = TwitterFactory.getSingleton();

    public static LogicaNegocio getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogicaNegocio();
        }
        return INSTANCE;
    }

    public LoginUsuario getLoginUsuario() {
        return loginUsuario;
    }

    public Twitter getAdmin() {
        return admin;
    }

    public void setAdmin(Twitter admin) {
        this.admin = admin;
    }

   
    
    

}
