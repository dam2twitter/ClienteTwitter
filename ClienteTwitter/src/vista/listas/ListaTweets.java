package vista.listas;

import java.util.List;
import javax.swing.AbstractListModel;
import twitter4j.Status;

public class ListaTweets extends AbstractListModel {

    private List<Status> listaTweets;

    public ListaTweets(List<Status> listaTweets) {
        this.listaTweets = listaTweets;
    }
    
    @Override
    public int getSize() {
        return 10;
    }

    @Override
    public Object getElementAt(int i) {
        return listaTweets.get(i);
    }
    
}
