
public class Slot {

    private int row, col;
    private Mark mark;

    public Slot() {
    }


    public Slot(int row, int col, Mark mark) {
        this.row = row;
        this.col = col;
        this.mark = mark;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.col;
    }

    public Mark getMark() {
        return this.mark;
    }

}