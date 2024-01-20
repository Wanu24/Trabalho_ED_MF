package trabalho_ed_mf;


import ClassImplementation.LinkedList;
import trabalho_ed_mf.Bot;

public class Player {
    private String name;
    private int numBots;
    private LinkedList<Bot> botsQueue;
    private Flag flag;

    //Constructors
    public Player(String name, Flag flag){
        this.name = name;
        this.numBots = 0;
        this.botsQueue = new LinkedList<Bot>();
        this.flag = flag;
    }

    public void addBot(Bot bot){
        this.botsQueue.add(bot);
        this.numBots++;
    }

    public void removeBot(Bot bot){
        this.botsQueue.remove(bot);
        this.numBots--;
    }

    //Getters and Setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getNumBots(){
        return numBots;
    }
    public void setNumBots(int numBots){
        this.numBots = numBots;
    }
    public LinkedList<Bot> getBots(){
        return botsQueue;
    }
    public void setBots(LinkedList<Bot> bots){
        this.botsQueue = bots;
    }
    public Flag getFlag(){
        return flag;
    }
    public void setFlag(Flag flag){
        this.flag = flag;
    }
    public PlayerColour getPlayerColour(){
        return flag.getColour();
    }
    public void useTurn(){
        Bot bot = botsQueue.get(0);
        botsQueue.remove(0);
        botsQueue.add(bot);
    }
}

