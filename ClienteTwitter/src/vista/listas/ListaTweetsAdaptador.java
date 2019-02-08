package vista.listas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import twitter4j.Status;
import Componentes.ComponenteTweet;

public class ListaTweetsAdaptador extends DefaultListCellRenderer  {

    @Override
    public Component getListCellRendererComponent(JList jlist, Object value, int index, boolean isSelected, boolean isFocused) {
        ComponenteTweet componenteTweet = new ComponenteTweet();
        Status tweet = (Status) value;
        componenteTweet.cargarTweet(tweet);
        if (isSelected) {
            componenteTweet.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        }
        return componenteTweet;
    }
    
}
    