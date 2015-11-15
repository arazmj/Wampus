package net.amirrazmjou.wampus;

import java.util.*;
import static net.amirrazmjou.wampus.UnicornLiteral.Unicorn;
import static net.amirrazmjou.wampus.WampusLiteral.Wampus;

public class Main {

    /**
     * add percepts (a list of clauses) to kb and return the updated kb
     * @param kb
     * @param percepts
     */
    public static void tellKb (KnowledgeBase<Literal> kb,
                               List<WampusLiteral> percepts) {
        for(WampusLiteral percept: percepts) {
          //  List<Literal> implications = percept.getImplications();
          //  kb.add(implications);
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
    public static void testWumpus () {
        KnowledgeBase<WampusLiteral> knowledgeBase = new KnowledgeBaseStrategy1<>();

        knowledgeBase.add(new WampusLiteral(Wampus.PIT, 1, 1, false));
        WampusLiteral b11 = new WampusLiteral(Wampus.BREEZE, 1, 1, true);
        knowledgeBase.add(b11.getImplications());

        knowledgeBase.add(new WampusLiteral(Wampus.PIT, 2, 1, false));
        WampusLiteral b21 = new WampusLiteral(Wampus.BREEZE, 2, 1, true);
        knowledgeBase.add(b21.getImplications());


        WampusLiteral p12 = new WampusLiteral(Wampus.PIT, 1, 2, true);
        WampusLiteral p22 = new WampusLiteral(Wampus.PIT, 2, 2, true);

        if (knowledgeBase.ask(p12) ^ knowledgeBase.ask(p12.getNegated() )) {
            System.out.println("There's no pit at [1,2].");
        } else {
            System.out.println("Query is pit at [1,2] cannot be inferred.");
        }

        if (knowledgeBase.ask(p22) ^ knowledgeBase.ask(p22.getNegated() )) {
            System.out.println("There's no pit at [2,2].");
        } else {
            System.out.println("Query is pit at [2,2] cannot be inferred.");
        }
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

    private static final String[] operators = { "<=>", "=>", "!", "|", "&"};
    private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList(operators));
    private static final Set<String> UNARY = new HashSet<>(Arrays.asList("!"));

    public static List<String> convertToPostfix(List<String> tokens)
    {
        Stack<String> stack = new Stack<>();
        List<String> result = new LinkedList<>();

        for (String token : tokens) {
            if (OPERATORS.contains(token)) {
                while (!stack.empty() && OPERATORS.contains(stack.peek())
                        && !UNARY.contains(token))
                    result.add(stack.pop());

                stack.push(token);
            }
            else if (token.equals("(")) {
                stack.push(token);
            }
            else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("("))
                    result.add(stack.pop());

                stack.pop();
            }
            else {
                result.add(token);
            }
        }

        while (!stack.empty())
            result.add(stack.pop());

        return result;
    }

    public static List<SemiClause> convertPostfixToSemiClause(List<String> tokens) {
        Stack<SemiClause> stack = new Stack<>();
        for (String token : tokens) {
            if (!OPERATORS.contains(token)) {
                stack.push(new SemiClause(token));
            } else {
                if (token.equals("!")) {
                    SemiClause operand = stack.pop();
                    operand.negate();
                    stack.push(operand);
                } else {
                    SemiClause right = stack.pop();
                    SemiClause left = stack.pop();

                    if (token.equals("|")) {
                        if (left.getMode(SemiClause.Mode.Disjunction) &&
                                right.getMode(SemiClause.Mode.Disjunction)) {
                            left.addSingle(right, SemiClause.Mode.Disjunction);
                            stack.push(left);
                        } else {
                            stack.push(left);
                            stack.push(right);
                        }
                    }
                    else if (token.equals("&")) {
                        if (left.getMode(SemiClause.Mode.Conjunction) &&
                                right.getMode(SemiClause.Mode.Conjunction)) {
                            left.addSingle(right, SemiClause.Mode.Conjunction);
                            stack.push(left);
                        } else {
                            stack.push(left);
                            stack.push(right);
                        }
                    }
                }
            }
        }

        List<SemiClause> result = new LinkedList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }


    public static class SemiClause {
        public SemiClause(String token) {
            literals = new LinkedList<>();
            SemiLiteral semiLiteral = new SemiLiteral();
            semiLiteral.literal = token;
            literals.add(semiLiteral);
            this.mode = Mode.Single;
        }

        public boolean getMode(Mode m) {
            if (this.mode == Mode.Single)
                return true;
            return m == this.mode;
        }

        public void addSingle(SemiClause right, Mode mode) {
            assert (right.literals.size() == 1);
            this.literals.addAll(right.literals);
            this.mode = mode;
        }


        private static class SemiLiteral {
            public boolean negated;
            public String literal;

            @Override
            public String toString() {
                return String.format("%s%s", negated ? "!" : "", literal);
            }
        }
        public void negate() {
            for (SemiLiteral literal: literals) {
                literal.negated = !literal.negated;
            }
            this.mode = this.mode == Mode.Conjunction ? Mode.Disjunction : Mode.Conjunction;
        }

        public enum Mode { Conjunction, Disjunction, Single }
        private Mode mode;
        public List<SemiLiteral> literals;

        @Override
        public String toString() {
            String[] stringArray = new String[literals.size()];
            for (int i = 0; i < literals.size(); i++) {
                stringArray[i] = literals.get(i).toString();
            }

            if (mode == Mode.Single)
                return literals.get(0).toString();
            else if (mode == Mode.Conjunction)
                return String.join(" & ", stringArray);
            else //if (mode == Mode.Disjunction)
                return String.join(" | ", stringArray);
        }
    }

    public static void main(String[] args) {
        testUnicorn();
        testWumpus();
    }

    private static void testConvert() {
        String s = "A | ( B & C )";
        String[] tokens  = s.split(" ");
        List<String> rpn = convertToPostfix(Arrays.asList(tokens));
        //System.out.println(tokens);
        for (String s1 : rpn) {
            System.out.print(s1 + " ");
        }

        System.out.println("");
        List<SemiClause> semiClauses = convertPostfixToSemiClause(rpn);
        for (SemiClause semiClause : semiClauses) {
            System.out.println(semiClause);
        }
    }

}