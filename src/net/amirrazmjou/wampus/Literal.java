package net.amirrazmjou.wampus;

import java.util.List;

/**
 * Created by Amir Razmjou on 11/11/15.
 */

public interface Literal {
    Predicate getState();

    Literal getNegated();

    List<Literal> getImplications();
}
