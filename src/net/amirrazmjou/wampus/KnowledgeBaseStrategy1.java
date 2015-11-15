package net.amirrazmjou.wampus;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Amir Razmjou on 11/12/15.
 */
public class KnowledgeBaseStrategy1<L extends Literal> extends KnowledgeBase<L> {
    public KnowledgeBaseStrategy1(KnowledgeBase<L> kb) {
        this.repo = kb.repo;
    }

    public KnowledgeBaseStrategy1() {

    }

    public boolean ask(L literal) {
        // the set of repo in the CNF representation of KB ∧ ¬α
        LinkedList<HashSet<L>> clauses = new LinkedList<>(repo);
        L negated = (L) literal.getNegated();
        HashSet<L> ls = new HashSet<>();
        ls.add(negated);
        clauses.addFirst(ls);

        HashSet<HashSet<L>> resolvents;

        System.out.println(String.format(columnFormat, "#", "Clause 1", "Clause 2", "Resolvents"));

        int i = 0;
        while (true) {
            HashSet<HashSet<L>> newClauses = new HashSet<>();

            for (HashSet<L> c1 : clauses) {
                List<HashSet<L>> subClauses = clauses.subList(clauses.indexOf(c1) + 1, clauses.size());
                for (HashSet<L> c2 : subClauses) {
                    resolvents = new HashSet<>(); // initialize if needed
                    if (resolve(c1, c2, resolvents)) {
                        System.out.println("The sentence " + literal + " has been proved " + "as the clauses " + c1 +
                                " and " + c2 + " are resolved to an empty clause.");
                        return true;
                    }
                    System.out.println(String.format(columnFormat, ++i, c1, c2, resolvents));
                    newClauses.addAll(resolvents);
                }
            }
            if (clauses.containsAll(newClauses)) {
                System.out.println("The sentence " + literal + " as the clauses  " + newClauses +
                        " are all already in original KB");
                return false;
            }
            clauses.addAll(0, newClauses);
        }
    }

}
