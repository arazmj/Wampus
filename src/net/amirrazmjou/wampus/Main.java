package net.amirrazmjou.wampus;

import java.util.List;

public class Main {

    // add percepts (a list of clauses) to kb and return the updated kb
    public static void tellKb (KnowledgeBase<Literal> kb, List<Literal> percepts) {
    }


    // given kb (a list of clauses), use resolution to infer an answer
    //        ; for the query
    //        ; return answer for the query
    public boolean askKb (KnowledgeBase<Literal> kb, List<Literal> query) {
        return true;
    }

    public static void tellKb (KnowledgeBase<Literal> kb, String percepts) {
    }

    public static boolean askKb (String kb, String query) {
        return true;
    }

    public static void testWumpus () {
        KnowledgeBase<Literal> knowledgeBase = new KnowledgeBase<>();
        knowledgeBase.add(new Literal<>(StateLiteral.PIT, 1, 1));
    }

    public enum StateLiteral  {
        PIT, WAMPUS;

        @Override public String toString() {
            String s = super.toString();
            return s.substring(0, 1);
        }
    }

    public enum PerceptLiteral {
        BREEZE, STENCH, GLITTER;
        @Override public String toString() {
            String s = super.toString();
            return s.substring(0, 1);
        }
    }

    public static void main(String[] args) {
	    // write your code here
        KnowledgeBase<Literal<StateLiteral>> wampus = new KnowledgeBase<>();
       // Literal<StateLiteral> stateLiteralLiteral = new Literal<>(StateLiteral.PIT, 2, 2);
        wampus.add(new Literal<>(StateLiteral.PIT, 2, 2));
        wampus.add(new Literal<>(StateLiteral.PIT, 1, 2));
        wampus.add(new Literal<>(StateLiteral.PIT, 2, 3, true));

        System.out.println(wampus);
        //System.out.print(new Literal(States.PIT, 2, 2 ));
        //System.out.println(PerceptLiteral.GLITTER);

    }
}
