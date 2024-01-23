package trabalho_ed_mf;


import ClassImplementation.LinkedList;

public class Player {
    private String name;
    private LinkedList<Bot> botsQueue;
    private Flag flag;

    //Constructors
    public Player(String name, Flag flag){
        this.name = name;
        this.botsQueue = new LinkedList<Bot>();
        this.flag = flag;
    }

    public Player(String name, Flag flag, LinkedList<Bot> bots){
        this.name = name;
        this.botsQueue = bots;
        this.flag = flag;
    }


    public void addBot(Bot bot){
        this.botsQueue.add(bot);
    }

    public void removeBot(Bot bot){
        this.botsQueue.remove(bot);
    }

    //Getters and Setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
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
    public void useTurn(Map map,Player enemy){
        Bot bot = botsQueue.get(0);
        bot.move(map,enemy);
        botsQueue.remove(0);
        botsQueue.add(bot);
    }
    public Bot getLastBot(){
        return botsQueue.getRear().getElement();
    }
}

