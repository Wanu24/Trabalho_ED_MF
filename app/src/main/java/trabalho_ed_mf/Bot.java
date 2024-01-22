package trabalho_ed_mf;


import static trabalho_ed_mf.BotMovement.*;

public class Bot{
    private int location;

    private int lastLocation;
    private MovementEnum movEnum;
    private Player player;
    private static int contador;


    public Bot(Player player){
        contador++;
        this.location = player.getFlag().getIndex();
        this.lastLocation = -1;
        this.player = player;
        this.movEnum = contador%3 == 0 ? MovementEnum.RANDOMPATH : contador%3 == 1 ? MovementEnum.SHORTESTPATH : MovementEnum.ATHELETICPATH;
    }

    public void move(Map map,Player enemy){
        map.getNetwork().getVertex(location).setHasBot(false);
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

    public Player getPlayer(){
        return player;
    }

    public PlayerColour getPlayerColour(){
        return player.getFlag().getColour();
    }
}

