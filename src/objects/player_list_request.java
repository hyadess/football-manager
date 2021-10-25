package objects;

import java.io.Serializable;

public class player_list_request implements Serializable
{
    private String club_name;

    public player_list_request(String c)
    {
        club_name=c;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }
}
