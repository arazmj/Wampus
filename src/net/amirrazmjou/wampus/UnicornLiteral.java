package net.amirrazmjou.wampus;

import java.util.List;

/**
 * Created by Amir Razmjou on 11/11/15.
 */
public class UnicornLiteral implements Literal {
    public enum Unicorn {Mythical, Mortal, Mammal, Horned, Magical};
    private Unicorn state;
    private boolean negated;

    public UnicornLiteral(Unicorn state, boolean positive) {
        this.state = state;
        negated = !positive;
    }

    @Override
    public Predicate getState() {
        return null;
    }

    @Override
    public UnicornLiteral getNegated() {
        return new UnicornLiteral(state, negated);
    }

    @Override
    public List<Literal> getImplications() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UnicornLiteral that = (UnicornLiteral) o;

        if (negated != that.negated) return false;
        return state == that.state;

    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + (negated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s%s", negated ? "!" : "", state);
    }
}
