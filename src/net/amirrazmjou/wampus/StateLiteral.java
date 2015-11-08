package net.amirrazmjou.wampus;

/**
 * Created by Amir Razmjou on 11/5/15.
 */

public enum StateLiteral  {
    PIT, WAMPUS, GOLD;

    @Override public String toString() {
        String s = super.toString();
        return s.substring(0, 1);
    }
}