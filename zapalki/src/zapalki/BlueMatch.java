package zapalki;

public class BlueMatch extends Match{
    BlueMatch(){
        color = "blue";
    }
    @Override
    public void putOnFire(){
        GuiManager.print(color);
    }
}

