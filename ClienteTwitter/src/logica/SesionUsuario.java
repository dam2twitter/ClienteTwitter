package logica;

import twitter4j.Twitter;

public class SesionUsuario {
    
    public static enum TipoLista {USER,HOME};

    private Twitter usuarioActual;

    public SesionUsuario(Twitter usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public Twitter getUsuarioActual() {
        return usuarioActual;
    }

    
    
}
