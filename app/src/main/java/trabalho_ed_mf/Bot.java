package trabalho_ed_mf;

import trabalho_ed_mf.MovementEnum;
import trabalho_ed_mf.BotMovement;
import trabalho_ed_mf.Player;

public class Bot{
    private int index;
    private MovementEnum movEnum;
    private Player player;
    private BotMovement mov;

    //Constructors
    public Bot(Player player){
        this.index = -1;
        this.mov = null;
        this.player = player;
    }

    public Bot(Player player, int index){
        this.index = index;
        this.mov = null;
        this.player = player;
    }


    public void move(){
        if(movEnum == MovementEnum.SHORTESTPATH){
            mov.shortestPath();
        }
        else if(movEnum == MovementEnum.RANDOMPATH){
            mov.randomPath();
        }
        else if(movEnum == MovementEnum.GREEDYPATH){
            mov.greedyPath();
        }
    }

    //Getters and Setters
    public int getIndex(){
        return index;
    }

    public void setIndex(int index){
        this.index = index;
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

