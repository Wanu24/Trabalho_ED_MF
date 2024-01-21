package trabalho_ed_mf;


import trabalho_ed_mf.Bot;
import trabalho_ed_mf.Flag;

import java.util.LinkedList;

public class Location {
    private boolean hasFlag;
    private boolean hasBot;
    private Flag flag;
    private LinkedList<Bot> bot;
    private int index;


    public Location(int i){
        this.hasFlag = false;
        this.hasBot = false;
        this.flag = null;
        this.bot = new LinkedList<Bot>();
        this.index = i+1;
    }

    public boolean getHasFlag(){
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag){
        this.hasFlag = hasFlag;
    }

    public boolean getHasBot(){
        return hasBot;
    }

    public void setHasBot(boolean hasBot){
        this.hasBot = hasBot;
    }

    public Flag getFlag(){
        return flag;
    }

    public void setFlag(Flag flag){
        this.flag = flag;
    }

    public int getIndex() {
        return index;
    }

    public LinkedList<Bot> getBots(){
        return bot;
    }

    public Bot getBot(){
        return bot.getFirst();
    }

    public void setBot(Bot bot){
        this.bot.add(bot);
        this.hasBot = true;
    }
}