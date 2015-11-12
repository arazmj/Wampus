package net.amirrazmjou.wampus.test;

import net.amirrazmjou.wampus.Literal;
import net.amirrazmjou.wampus.Predicate;
import net.amirrazmjou.wampus.WampusLiteral;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
public class WampusLiteralTest {

    @Test
    public void testHashCode() throws Exception {

        HashSet<Literal> a, b;
        a = new HashSet<>();
        b = new HashSet<>();
        a.add(new WampusLiteral(Predicate.PIT, 1, 1, false));
        a.add(new WampusLiteral(Predicate.PIT, 2, 3, true));
        a.add(new WampusLiteral(Predicate.PIT, 2, 3, false));
        a.add(new WampusLiteral(Predicate.WAMPUS, 2, 4, true));

        b.add(new WampusLiteral(Predicate.PIT, 1, 1, false));
        b.add(new WampusLiteral(Predicate.PIT, 2, 3, true));
        b.add(new WampusLiteral(Predicate.PIT, 2, 3, false));
        b.add(new WampusLiteral(Predicate.WAMPUS, 2, 4, true));


        if (a.hashCode() != b.hashCode())
            throw new Exception("testHashCode()");

        a.add(new WampusLiteral(Predicate.PIT, 1, 1, false));
        a.add(new WampusLiteral(Predicate.PIT, 2, 3, true));
        a.add(new WampusLiteral(Predicate.PIT, 2, 3, false));
        a.add(new WampusLiteral(Predicate.WAMPUS, 2, 4, true));

        b.add(new WampusLiteral(Predicate.PIT, 1, 1, false));
        b.add(new WampusLiteral(Predicate.PIT, 2, 3, true));
        b.add(new WampusLiteral(Predicate.PIT, 2, 3, false));
        b.add(new WampusLiteral(Predicate.WAMPUS, 2, 4, false));

        if (a.hashCode() == b.hashCode())
            throw new Exception("testHashCode()");

    }
}