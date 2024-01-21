package trabalho_ed_mf;

import trabalho_ed_mf.MovementEnum;
import trabalho_ed_mf.BotMovement;
import trabalho_ed_mf.Player;

public class Bot{
    private int location;
    private MovementEnum movEnum;
    private Player player;
    private BotMovement mov;

    private static int contador;


    public Bot(Player player){
        contador++;
        this.location = player.getFlag().getIndex();
        this.mov = null;
        this.player = player;
        this.movEnum = contador%3 == 0 ? MovementEnum.RANDOMPATH : contador%3 == 1 ? MovementEnum.SHORTESTPATH : MovementEnum.GREEDYPATH;
    }

    public void move(Map map,Player enemy){
        if(movEnum == MovementEnum.SHORTESTPATH){
            mov.shortestPath(map,location, enemy.getFlag().getIndex());
        }
        else if(movEnum == MovementEnum.RANDOMPATH){
            mov.randomPath(map,location);
        }
        else if(movEnum == MovementEnum.GREEDYPATH){
            mov.greedyPath(map,location);
        }
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

    public void setMov(BotMovement mov){
        this.mov = mov;
    }

    public BotMovement getMov(){
        return mov;
    }

    public PlayerColour getPlayerColour(){
        return player.getFlag().getColour();
    }
}

