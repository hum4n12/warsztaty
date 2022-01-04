package zapalki;

import gui.GuiManager;

public class BlueMatch extends Match{
    BlueMatch(){
        color = "blue";
    }
    @Override
    public void putOnFire(){
        GuiManager.print(color);
    }
}

