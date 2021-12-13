package zapalki;

public class GreenMatch extends Match{
    GreenMatch(){
        color = "green";
    }
    @Override
    public void putOnFire(){
        System.out.println(color);
    }
}
