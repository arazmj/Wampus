package net.amirrazmjou.wampus;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Amir Razmjou on 11/5/15.
 */
public class KnowledgeBase<L> {
    private HashSet<HashSet<L>> repo = new HashSet<>();

    public void add(L stateLiteral) {
        repo.add(new HashSet<> (Arrays.asList(stateLiteral)));
    }

    public void add(Collection<L> stateLiteral) {
        repo.add(new HashSet<> (Arrays.asList(stateLiteral)));
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
}
