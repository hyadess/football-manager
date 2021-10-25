package objects;

import javafx.beans.property.SimpleStringProperty;

public class tableObject {
    private final SimpleStringProperty player_name;

    public tableObject(String player_name) {

        this.player_name =new SimpleStringProperty(player_name);
    }

    public String getPlayer_name() {
        return player_name.get();
    }
}
