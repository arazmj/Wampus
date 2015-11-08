package net.amirrazmjou.wampus;

/**
 * Created by Amir Razmjou on 11/5/15.
 */

public enum PerceptLiteral {
    BREEZE(StateLiteral.PIT) , STENCH(StateLiteral.WAMPUS);//, GLITTER(StateLiteral.GOLD);

    private StateLiteral matchState;
    PerceptLiteral(StateLiteral matchState) {
        this.matchState = matchState;
    }

    public StateLiteral getMatchState() {
        return matchState;
    }
    @Override public String toString() {
        String s = super.toString();
        return s.substring(0, 1);
    }
}