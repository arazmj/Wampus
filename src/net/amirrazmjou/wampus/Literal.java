package net.amirrazmjou.wampus;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
final public class Literal<T> {

    public Literal(T s, int row, int column, boolean negated) {
        this.row = row;
        this.column = column;
        this.state = s.toString();
        this.negated = negated;
    }

    public Literal(T s, int row, int column) {
        this.row = row;
        this.column = column;
        this.state = s.toString();
        this.negated = false;
    }

    final private int row, column;
    final private String state;
    final private boolean negated;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("%s%s%d%d",
                negated ? "!" : "", state, row, column);
    }
}
