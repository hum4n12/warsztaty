package zapalki;

public class RedMatch extends Match{
    RedMatch(){
        color = "red";
    }
    @Override
    public void putOnFire(){
        System.out.println(color);
    }
}
