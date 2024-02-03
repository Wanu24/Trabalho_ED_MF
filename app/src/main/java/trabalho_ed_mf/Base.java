package trabalho_ed_mf;

public class Base {

    private int index;
    private Flag flag;

    public Base(Flag flag) {
        this.index = -1;
        this.flag = flag;
    }


    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public Flag getFlag() {
        return flag;
    }
    public void setFlag(Flag flag) {
        this.flag = flag;
    }
}
