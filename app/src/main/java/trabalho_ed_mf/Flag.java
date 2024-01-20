package trabalho_ed_mf;

import trabalho_ed_mf.FlagColour;

public class Flag {
    private FlagColour colour;
    private int index;

    //Constructors
    public Flag(String colour){
        this.colour = FlagColour.valueOf(colour);
        this.index = -1;
    }
    public Flag(){
        this.colour = null;
        this.index = -1;
    }

    //Getters and Setters
    public FlagColour  getColour(){
        return colour;
    }
    public void setColour(FlagColour colour){
        this.colour = colour;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
