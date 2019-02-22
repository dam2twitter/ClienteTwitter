package vista.listas;

import Componentes.Usuario;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import twitter4j.User;

public class ListaUsuariosAdaptador extends DefaultListCellRenderer {
    
    @Override
    public Component getListCellRendererComponent(JList jlist, Object value, int index, boolean isSelected, boolean isFocused) {
        User usuarioSeleccionado = (User) value;
        Usuario usuario = new Usuario(usuarioSeleccionado.getId());
        if (isSelected) {
            usuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }
        return usuario;
    }
    
}
    