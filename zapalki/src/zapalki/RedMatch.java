package zapalki;

import gui.GuiManager;

public class RedMatch extends Match{
    RedMatch(){
        color = "red";
    }
    @Override
    public void putOnFire(){
        GuiManager.print(color);
    }
}
