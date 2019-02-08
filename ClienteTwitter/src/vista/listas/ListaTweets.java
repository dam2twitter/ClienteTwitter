package vista.listas;

import javax.swing.AbstractListModel;
import twitter4j.ResponseList;
import twitter4j.Status;

public class ListaTweets extends AbstractListModel {

    private ResponseList<Status> listaTweets;

    public ListaTweets(ResponseList<Status> listaTweets) {
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
