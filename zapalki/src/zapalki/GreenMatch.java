package zapalki;

import gui.GuiManager;

public class GreenMatch extends Match{
    GreenMatch(){
        color = "green";
    }
    @Override
    public void putOnFire(){
        GuiManager.print(color);
    }
}
