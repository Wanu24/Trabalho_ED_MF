package trabalho_ed_mf;


import java.util.LinkedList;

public class Location {
    private boolean hasFlag;
    private boolean hasBot;
    private Flag flag;
    private int index;


    public Location(int i) {
        this.hasFlag = false;
        this.hasBot = false;
        this.flag = null;
        this.index = i;
    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public boolean getHasBot() {
        return hasBot;
    }

    public void setHasBot(boolean hasBot) {
        this.hasBot = hasBot;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public int getIndex() {
        return index;
    }

}