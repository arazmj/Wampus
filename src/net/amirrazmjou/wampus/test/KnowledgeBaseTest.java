package net.amirrazmjou.wampus.test;

import net.amirrazmjou.wampus.KnowledgeBase;
import net.amirrazmjou.wampus.Literal;
import net.amirrazmjou.wampus.StateLiteral;

import java.util.Arrays;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
public class KnowledgeBaseTest {

    @org.junit.Test
    public void testAdd() throws Exception {
        KnowledgeBase<Literal<StateLiteral>> wampus = new KnowledgeBase<>();
        wampus.add(new Literal<>(StateLiteral.PIT, 2, 2));
        wampus.add(new Literal<>(StateLiteral.PIT, 1, 2));
        wampus.add(new Literal<>(StateLiteral.PIT, 2, 3, true));

        if (!wampus.toString().trim().equals("(P22) (P12) (!P23) "))
            throw new Exception("testAdd()");

    }

    @org.junit.Test
    public void testAdd1() throws Exception {
        KnowledgeBase<Literal<StateLiteral>> wampus = new KnowledgeBase<>();

        wampus.add(Arrays.asList(
                new Literal[]{
                    new Literal<>(StateLiteral.WAMPUS, 3, 3, true),
                    new Literal<>(StateLiteral.WAMPUS, 2, 3, true),
                    new Literal<>(StateLiteral.WAMPUS, 1, 3, true)}));

        if (!wampus.toString().trim().equals("(!W33 !W23 !W13)"))
            throw  new Exception("testAdd1()");
    }
}