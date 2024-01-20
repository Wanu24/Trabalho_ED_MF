package trabalho_ed_mf;

import trabalho_ed_mf.PlayerColour;

public class Flag {
    private PlayerColour colour;
    private int index;

    //Constructors
    public Flag(String colour){
        this.colour = PlayerColour.valueOf(colour);
        this.index = -1;
    }
    public Flag(){
        this.colour = null;
        this.index = -1;
    }

    //Getters and Setters
    public PlayerColour  getColour(){
        return colour;
    }
    public void setColour(PlayerColour colour){
        this.colour = colour;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
