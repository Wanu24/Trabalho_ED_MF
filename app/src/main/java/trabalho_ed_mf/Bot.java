package trabalho_ed_mf;


import static trabalho_ed_mf.BotMovement.*;

public class Bot{
    private int location;
    private int lastLocation;
    private MovementEnum movEnum;
    private static int contador;
    private int index;

    public Bot(Player player,MovementEnum mov){
        contador++;
        this.index = contador;
        this.location = player.getFlag().getIndex();
        this.lastLocation = player.getFlag().getIndex();
        this.movEnum = mov;
    }

    public void move(Map map,Player enemy){
        map.getNetwork().getVertex(location).setHasBot(false);
        lastLocation = location;
        if(movEnum == MovementEnum.SHORTESTPATH){
            location = shortestPath(map,location, enemy.getFlag().getIndex());
        }
        else if(movEnum == MovementEnum.RANDOMPATH){
            location = randomPath(map,location);
        }
        else if(movEnum == MovementEnum.ATHELETICPATH){
            location = atheleticPath(map,location,lastLocation);
        }
        map.getNetwork().getVertex(location).setHasBot(true);
        System.out.println("Bot used " + movEnum);
        System.out.println("Bot " + index +" moved from " + lastLocation + " to " + location);
    }

    //Getters and Setters
    public int getIndex(){
        return location;
    }

    public void setIndex(int index){
        this.location = index;
    }

    public void setMov(MovementEnum mov){
        this.movEnum = mov;
    }

    public MovementEnum getMovEnum(){
        return movEnum;
    }
}

