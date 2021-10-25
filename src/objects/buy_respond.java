package objects;

import java.io.Serializable;

public class buy_respond implements Serializable {
    private player p;
    private boolean done_buying;

    public buy_respond(player p,boolean done_buying)
    {
        this.p=p;
        this.done_buying=done_buying;
    }

    public player getP() {
        return p;
    }

    public boolean isDone_buying() {
        return done_buying;
    }
}
