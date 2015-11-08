package net.amirrazmjou.wampus.test;

import net.amirrazmjou.wampus.Literal;
import net.amirrazmjou.wampus.StateLiteral;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
public class LiteralTest {

    @Test
    public void testHashCode() throws Exception {

        HashSet<Literal<StateLiteral>> a, b;
        a = new HashSet<>();
        b = new HashSet<>();
        a.add(new Literal<>(StateLiteral.PIT, 1, 1));
        a.add(new Literal<>(StateLiteral.PIT, 2, 3, true));
        a.add(new Literal<>(StateLiteral.PIT, 2, 3, false));
        a.add(new Literal<>(StateLiteral.WAMPUS, 2, 4, true));

        b.add(new Literal<>(StateLiteral.PIT, 1, 1));
        b.add(new Literal<>(StateLiteral.PIT, 2, 3, true));
        b.add(new Literal<>(StateLiteral.PIT, 2, 3, false));
        b.add(new Literal<>(StateLiteral.WAMPUS, 2, 4, true));


        if (a.hashCode() != b.hashCode())
            throw new Exception("testHashCode()");

        a.add(new Literal<>(StateLiteral.PIT, 1, 1));
        a.add(new Literal<>(StateLiteral.PIT, 2, 3, true));
        a.add(new Literal<>(StateLiteral.PIT, 2, 3, false));
        a.add(new Literal<>(StateLiteral.WAMPUS, 2, 4, true));

        b.add(new Literal<>(StateLiteral.PIT, 1, 1));
        b.add(new Literal<>(StateLiteral.PIT, 2, 3, true));
        b.add(new Literal<>(StateLiteral.PIT, 2, 3, false));
        b.add(new Literal<>(StateLiteral.WAMPUS, 2, 4, false));

        if (a.hashCode() == b.hashCode())
            throw new Exception("testHashCode()");

    }
}