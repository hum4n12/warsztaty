package zapalki;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MatchBox{
    //klasa pudełko zapałek
    protected int boxSize = 5;
    protected List<Match> boxSpace;

    public void addMatch(Match match){
        boxSpace.add(match);
        System.out.println("Added " + match.color + " match");
    }

    public Match getMatch(){
        Match placeholder = null;
        if(boxSpace.size() > 0){
            placeholder = boxSpace.remove(0);
            placeholder.putOnFire();
        }
        return placeholder;
    }

    public void turnColorToColor(String color1, String color2){
        boxSpace.stream()
                .filter(match -> match.color.equals(color1))
                .peek(match -> match.color = color2)
                .forEach(match -> System.out.println(match.color));
                ;
    }

    public void burnAll(){
        boxSpace = boxSpace.stream()
                .peek(match -> System.out.println("Spalono zapalke o kolorze: "+match.color))
                .map(match -> new Ashes())
                .collect(Collectors.toList());
    }

    public Match getMatch(int num){
        Match placeholder = null;
        placeholder = boxSpace.get(num);
        boxSpace.set(num,null);
        placeholder.putOnFire();
        return placeholder;
    }

    public int getBoxSize() {
        return boxSize;
    }
}
