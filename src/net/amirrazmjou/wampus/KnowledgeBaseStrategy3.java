package net.amirrazmjou.wampus;

import java.util.*;

/**
 * Created by Amir Razmjou on 11/12/15.
 */
public class KnowledgeBaseStrategy3<L extends Literal> extends KnowledgeBase<L> {
    public KnowledgeBaseStrategy3(KnowledgeBase<L> kb) {
        this.repo = kb.repo;
    }

    private class PriorityClause implements Comparable<PriorityClause>{
        public PriorityClause(int priority, HashSet<L> clause) {
            this.priority = (priority * 100) - clause.size();
            this.clause = clause;
        }

        public HashSet<L> clause;
        public int priority;

        @Override
        public String toString() {
            return clause.toString();
        }

        @Override
        public int compareTo(PriorityClause o) {
            if (this.priority > o.priority)
                return 1;
            else if (this.priority == o.priority)
                return 0;
            else return -1;
        }
    }


    protected final String columnFormat = "%4s |%2s | %-23s |%2s | %-23s | %-20s";


    public boolean ask(L literal) {
        // the set of repo in the CNF representation of KB ∧ ¬α
        LinkedList<PriorityClause> clauses = new LinkedList<>();

        for (HashSet<L> clause: repo) {
            clauses.add(new PriorityClause(0, clause));
        }
        L negated = (L) literal.getNegated();
        HashSet<L> ls = new HashSet<>();
        ls.add(negated);
        clauses.add(new PriorityClause(1, ls));

        HashSet<HashSet<L>> resolvents;

        System.out.println(String.format(columnFormat, "#", "P1", "Clause 1", "P2", "Clause 2", "Resolvents"));

        int i = 0;
        while (true) {
            HashSet<PriorityClause> newClauses = new HashSet<>();

            for (PriorityClause c1 : clauses) {
                List<PriorityClause> subClauses = clauses.subList(clauses.indexOf(c1) + 1, clauses.size());
                for (PriorityClause c2 : subClauses) {
                    resolvents = new HashSet<>(); // initialize if needed
                    if (resolve(c1.clause, c2.clause, resolvents)) {
                        System.out.println("The sentence " + literal + " has been proved " + "as the clauses " + c1 +
                                " and " + c2 + " are resolved to an empty clause.");
                        return true;
                    }
                    System.out.println(String.format(columnFormat, ++i, c1.priority, c1, c2.priority, c2, resolvents));
                    for (HashSet<L> resolvent : resolvents) {
                        newClauses.add(new PriorityClause(1, resolvent));
                    }
                }
            }
            if (clauses.containsAll(newClauses)) {
                System.out.println("The sentence " + literal + " as the clauses  " + newClauses +
                        " are all already in original KB");
                return false;
            }
            clauses.addAll(0, newClauses);
            Collections.sort(clauses, Collections.reverseOrder());
        }
    }
}
