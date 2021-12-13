package zapalki;

public class YellowMatch extends Match{
    YellowMatch(){
        color = "yellow";
    }
    @Override
    public void putOnFire(){
        System.out.println(color);
    }
}
