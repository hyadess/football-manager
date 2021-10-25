package objects;

import java.io.Serializable;

public class sell_respond implements Serializable {
    private player p;
    private boolean done_selling;

    public sell_respond(player p,boolean done_selling)
    {
        this.p=p;
        this.done_selling=done_selling;
    }

    public player getP() {
        return p;
    }

    public boolean isDone_selling() {
        return done_selling;
    }
}
