package net.amirrazmjou.wampus;

/**
 * Created by Amir Razmjou on 11/5/15.
 */

public enum Predicate {
    BREEZE(true), STENCH(true), //GLITTER(false),
    PIT(true), WAMPUS(true); //GOLD(false);

    private Predicate implication;
    private Predicate implicationNot;
    private final boolean smelly;

    static {
        BREEZE.implication = PIT;
        BREEZE.implicationNot =  WAMPUS;
        STENCH.implication = WAMPUS;
        STENCH.implicationNot = PIT;
        PIT.implication = BREEZE;
        PIT.implicationNot = STENCH;
        WAMPUS.implication = STENCH;
        //GOLD.implication = GLITTER;
        //GLITTER.implication = GOLD;
    }

    Predicate(boolean smelly) {
        this.smelly = smelly;
    }

    public Predicate getImplication() {
        return implication;
    }
    public Predicate getImplicationNot() {
        return implicationNot;
    }


    @Override public String toString() {
        String s = super.toString();
        return s.substring(0, 1);
    }

    public boolean isSmelly() {
        return smelly;
    }
}