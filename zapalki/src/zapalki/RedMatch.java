package zapalki;

public class RedMatch extends Match{
    RedMatch(){
        color = "red";
    }
    @Override
    public void putOnFire(){
        GuiManager.print(color);
    }
}
