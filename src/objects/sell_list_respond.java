package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class sell_list_respond implements Serializable {

    private List<player> players=new ArrayList<>();

    public sell_list_respond(List<player> players)
    {
        this.players=players;
    }

    public List<player> getPlayers() {
        return players;
    }
}
