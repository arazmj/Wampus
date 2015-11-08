package net.amirrazmjou.wampus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    //final const int world_size = 4;
    // add percepts (a list of clauses) to kb and return the updated kb
    public static void tellKb (KnowledgeBase<Literal<StateLiteral>> kb,
                               List<Literal<PerceptLiteral>> percepts) {
        for(Literal<PerceptLiteral> percept: percepts) {
            List[] clauses = new List[PerceptLiteral.values().length];

            for (int i = 0; i < clauses.length; i++)
                clauses[i] = new LinkedList<>();

            int[] cs = {1, -1, 0, 0};
            int[] rs = {0, 0, 1, -1};
            for (int i = 0; i < cs.length; i++) {
                int column = percept.getColumn() + cs[i];
                int row = percept.getRow() + rs[i];
                if (row >= 0 && column >= 0) {
                    for (PerceptLiteral p: PerceptLiteral.values()) {
                        boolean positive = percept.getState() == p;
                        StateLiteral s = p.getMatchState();
                        Literal<StateLiteral> e = new Literal<>(s, row, column, positive);
                        clauses[p.ordinal()].add(e);
                    }
                }
            }
            for (List clause: clauses) {
                if (!clause.isEmpty()) {
                    kb.add(clause);
                }
            }

        }
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
        KnowledgeBase<Literal<StateLiteral>> knowledgeBase = new KnowledgeBase<>();
        knowledgeBase.add(new Literal<>(StateLiteral.PIT, 1, 1));
    }


    public static void main(String[] args) {
        KnowledgeBase<Literal<StateLiteral>> wampus = new KnowledgeBase<>();
        tellKb(wampus,
                Arrays.asList(
                new Literal[]{
                        new Literal<>(PerceptLiteral.BREEZE, 3, 3, true),
               //         new Literal<>(PerceptLiteral.STENCH, 2, 3),
               //         new Literal<>(PerceptLiteral.BREEZE, 1, 3)
                        }));
        System.out.println(wampus);

    }
}
