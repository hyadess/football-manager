package objects;

import java.io.Serializable;

public class sell_request implements Serializable

{
    private player the_player;

    public sell_request(player p)
    {
        the_player=p;
    }

    public player getThe_player()
    {
        return the_player;
    }

    public void setThe_player(player the_player)
    {
        this.the_player = the_player;
    }
}
