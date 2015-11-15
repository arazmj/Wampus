package net.amirrazmjou.wampus;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Amir Razmjou on 11/5/15.
 */

final public class WampusLiteral implements Literal {
    public enum Wampus { BREEZE, PIT };

    final private Wampus state;
    final private int row;
    final private int column;
    final private boolean negated;

    public WampusLiteral(Wampus s, int row, int column, boolean positive) {
        this.row = row;
        this.column = column;
        this.state = s;
        this.negated = !positive;
    }

    @Override
    public WampusLiteral getNegated() {
        return new WampusLiteral (state, row, column, negated);
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

    public List<WampusLiteral> getImplications() {
        int[] cs = {1, -1, 0, 0};
        int[] rs = {0, 0, 1, -1};
        List<WampusLiteral> clause = new LinkedList<>();

        for (int i = 0; i < Math.min(cs.length, rs.length); i++) {
            int column = getColumn() + cs[i];
            int row = getRow() + rs[i];
            if (row > 0 && column > 0) {
                Wampus implication = state == Wampus.BREEZE ? Wampus.PIT : Wampus.BREEZE;
                WampusLiteral e1 = new WampusLiteral(implication, row, column, true);
                clause.add(e1);
            }
        }
        return clause;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WampusLiteral that = (WampusLiteral) o;

        if (row != that.row) return false;
        if (column != that.column) return false;
        if (negated != that.negated) return false;
        return state == that.state;

    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + row;
        result = 31 * result + column;
        result = 31 * result + (negated ? 1 : 0);
        return result;
    }
}
