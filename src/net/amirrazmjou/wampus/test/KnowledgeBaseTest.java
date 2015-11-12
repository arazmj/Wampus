package net.amirrazmjou.wampus.test;

import net.amirrazmjou.wampus.KnowledgeBase;
import net.amirrazmjou.wampus.Literal;
import net.amirrazmjou.wampus.Predicate;
import net.amirrazmjou.wampus.WampusLiteral;

import java.util.Arrays;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
public class KnowledgeBaseTest {

    @org.junit.Test
    public void testAdd() throws Exception {
        KnowledgeBase<Literal> wampus = new KnowledgeBase<>();
        wampus.add(new WampusLiteral(Predicate.PIT, 2, 2, false));
        wampus.add(new WampusLiteral(Predicate.PIT, 1, 2, false));
        wampus.add(new WampusLiteral(Predicate.PIT, 2, 3, true));

        if (!wampus.toString()
                .replace("(!P22)", "")
                .replace("(!P12)", "")
                .replace("(P23)", "").trim().isEmpty())
            throw new Exception("testAdd()");

    }

    @org.junit.Test
    public void testAdd1() throws Exception {
        KnowledgeBase<Literal> wampus = new KnowledgeBase<>();

        wampus.add(Arrays.asList(
                new Literal[]{
                    new WampusLiteral(Predicate.WAMPUS, 3, 3, true),
                    new WampusLiteral(Predicate.WAMPUS, 2, 3, true),
                    new WampusLiteral(Predicate.WAMPUS, 1, 3, true)}));

        if (!wampus.toString().trim().equals("(W13 W33 W23)"))
            throw  new Exception("testAdd1()");
    }
}