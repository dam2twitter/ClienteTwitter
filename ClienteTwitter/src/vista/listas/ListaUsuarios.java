
package vista.listas;

import java.util.List;
import javax.swing.AbstractListModel;
import twitter4j.User;

public class ListaUsuarios extends AbstractListModel {
    
    private List<User> listaUsuarios;

    public ListaUsuarios(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public int getSize() {
        return this.listaUsuarios.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.listaUsuarios.get(index);
    }
    
}
