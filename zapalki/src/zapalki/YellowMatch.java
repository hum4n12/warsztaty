package zapalki;

public class YellowMatch extends Match{
    YellowMatch(){
        color = "yellow";
    }
    @Override
    public void putOnFire(){
        GuiManager.print(color);
    }
}
