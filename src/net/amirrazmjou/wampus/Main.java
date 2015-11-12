package net.amirrazmjou.wampus;

import java.util.*;
import static net.amirrazmjou.wampus.UnicornLiteral.Unicorn;

public class Main {

    /**
     * add percepts (a list of clauses) to kb and return the updated kb
     * @param kb
     * @param percepts
     */
    public static void tellKb (KnowledgeBase<Literal> kb,
                               List<WampusLiteral> percepts) {
        for(WampusLiteral percept: percepts) {
            List<Literal> implications = percept.getImplications();
            kb.add(implications);
            kb.add(percept);
        }
    }

    /**
     * given kb (a list of clauses), use resolution to infer an answer
     * for the query
     * @param kb
     * @param query
     * @return answer for the query
     */
    public boolean askKb (KnowledgeBase<Literal> kb, Literal query) {
        return true;
    }

    /**
     * overloaded string version of tellKb
     * @param kb
     * @param percepts
     */
    public static void tellKb (KnowledgeBase<Literal> kb, String percepts) {
    }

    /**
     * overloaded string version of askKb
     * @param kb
     * @param query
     * @return
     */
    public static boolean askKb (String kb, String query) {
        return true;
    }

    /**
     * initialize kb, add percepts to kb,
     * print queries and corresponding answers
     * @return true if done
     */
    public static boolean testWumpus () {
        KnowledgeBase<Literal> kb = new KnowledgeBaseStrategy0<>();
        Literal[] initialStates = new Literal[]{
                new WampusLiteral(Predicate.PIT, 1, 2, true),
                new WampusLiteral(Predicate.PIT, 2, 2, true)};

        kb.add(Arrays.asList(initialStates));
        return true;

    }

    private static void testUnicorn() {
        KnowledgeBase<UnicornLiteral> kb0 = new KnowledgeBaseStrategy0<>();

        // !Mythical V !Mortal)
        kb0.add(new UnicornLiteral[] {
                new UnicornLiteral(Unicorn.Mythical, false),
                new UnicornLiteral(Unicorn.Mortal, false)});

        // Mythical V Mortal
        kb0.add(new UnicornLiteral[] {
                new UnicornLiteral(Unicorn.Mythical, true),
                new UnicornLiteral(Unicorn.Mortal, true)
        });

        // Mythical V Mammal
        kb0.add(new UnicornLiteral[] {
                new UnicornLiteral(Unicorn.Mythical, true),
                new UnicornLiteral(Unicorn.Mammal, true)
        });

        // Mortal V Horned
        kb0.add(new UnicornLiteral[] {
                new UnicornLiteral(Unicorn.Mortal, true),
                new UnicornLiteral(Unicorn.Horned, true)
        });

        // !Mammal V Horned
        kb0.add(new UnicornLiteral[] {
                new UnicornLiteral(Unicorn.Mammal, false),
                new UnicornLiteral(Unicorn.Horned, true)
        });

        // !Horned V Magical
        kb0.add(new UnicornLiteral[] {
                new UnicornLiteral(Unicorn.Horned, false),
                new UnicornLiteral(Unicorn.Magical, true)
        });

        System.out.println("Ask if unicorn is magical using strategy 0.");
        kb0.ask(new UnicornLiteral(Unicorn.Magical, true));
        System.out.println();

//
        KnowledgeBase<UnicornLiteral> kb1 = new KnowledgeBaseStrategy1<>(kb0);
        System.out.println("Ask if unicorn is magical using strategy 1.");
        kb1.ask(new UnicornLiteral(Unicorn.Magical, true));
        System.out.println();

        KnowledgeBase<UnicornLiteral> kb2 = new KnowledgeBaseStrategy2<>(kb0);
        System.out.println("Ask if unicorn is magical using strategy 2.");
        kb2.ask(new UnicornLiteral(Unicorn.Magical, true));
        System.out.println();


        KnowledgeBase<UnicornLiteral> kb3 = new KnowledgeBaseStrategy3<>(kb0);
        System.out.println("Ask if unicorn is magical using strategy 3.");
        kb3.ask(new UnicornLiteral(Unicorn.Magical, true));
        System.out.println();
    }

    public static void main(String[] args) {
        testUnicorn();
//

        //System.out.println(kb);


        // !Mammal V Horned


//        Literal a = new WampusLiteral(Predicate.WAMPUS, 1, 2, true);
//        List<Literal> implications = b.getImplications();
//        for(Literal wl: implications) {
//            System.out.print(wl + " ");
//        }
//
//        EnumSet<Predicate> predicates = EnumSet.of(Predicate.BREEZE);
//        KnowledgeBase<Literal> wampus = new KnowledgeBase<>();
//        WampusLiteral[] wampusLiterals = new WampusLiteral[] {
//            new WampusLiteral(Predicate.BREEZE, 3, 3, true)//,
//            new WampusLiteral(Predicate.STENCH, 2, 3, true),
//            new WampusLiteral(Predicate.BREEZE, 1, 3, true)
//        };
//
//        tellKb(wampus, Arrays.asList(wampusLiterals));
//        System.out.println(wampus);
    }

}