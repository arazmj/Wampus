package net.amirrazmjou.wampus;

import java.util.*;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
public abstract class KnowledgeBase<L extends Literal> {
    /**
     * conjuction of disjunctions of type parameter L
     */
    protected HashSet<HashSet<L>> repo = new HashSet<>();

    /**
     * Add a single literal clause to the KB
     * @param clause single literal clause
     */
    public void add(L clause) {
        repo.add(new HashSet<> (Arrays.asList(clause)));
    }

    /**
     * Add a clause (disjunction of literals) to the KB
     * @param clause list of literals
     */
    public void add(List<L> clause) {
        repo.add(new HashSet<> (clause));
    }

    public void add(L[] clause) {
        add(Arrays.asList(clause));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (HashSet<L> clause: repo) {
            sb.append("(");
            for (L l: clause) {
                sb.append(l);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") ");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnowledgeBase<?> that = (KnowledgeBase<?>) o;

        return repo.equals(that.repo);

    }

    @Override
    public int hashCode() {
        return repo.hashCode();
    }


    /**
     * resolve function
     * @param c1 first clause
     * @param c2 second clause
     * @param resolvents resulting repo
     * @return true if one of resolvant is empty
     */
    protected boolean resolve(HashSet<L> c1, HashSet<L> c2, HashSet<HashSet<L>> resolvents) {
        for (L l1: c1) {
            L l1Neg = (L)l1.getNegated();
            for (L l2: c2) {
                if (l2.equals(l1Neg)) {
                    if (c1.size() == 1 && c2.size() == 1) {
                        return true;
                    }

                    HashSet<L> newClause = new HashSet<>();
                    newClause.addAll(c1);
                    newClause.addAll(c2);
                    newClause.remove(l1);
                    newClause.remove(l1Neg);
                    resolvents.add(newClause);
                }
            }
        }
        return false;
    }

    protected final String columnFormat = "%4s | %-23s | %-23s | %-20s";

    public abstract boolean ask(L literal);
}
