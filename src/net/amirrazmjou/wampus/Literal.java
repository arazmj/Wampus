package net.amirrazmjou.wampus;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
final public class Literal<T> {

    public Literal(T s, int row, int column, boolean positive) {
        this.row = row;
        this.column = column;
        this.state = s;
        this.negated = !positive;
    }

    public Literal(T s, int row, int column) {
        this.row = row;
        this.column = column;
        this.state = s;
        this.negated = false;
    }

    final private int row, column;

    public T getState() {
        return state;
    }

    final private T state;
    final private boolean negated;

    public Literal<T> getNegated() {
        return new Literal<T>(state, row, column, !negated);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return String.format("%s%s%d%d",
                negated ? "!" : "", state.toString(), row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Literal<?> literal = (Literal<?>) o;

        if (row != literal.row) return false;
        if (column != literal.column) return false;
        if (negated != literal.negated) return false;
        return state.equals(literal.state);

    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        result = 31 * result + state.toString().hashCode();
        result = 31 * result + (negated ? 1 : 0);
        return result;
    }
}
