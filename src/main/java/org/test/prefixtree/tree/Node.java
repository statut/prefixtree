package org.test.prefixtree.tree;

import java.util.Map;

/**
 * Domain for tree.
 */
public class Node {
    private Map<Character, Node> childs;

    public Node() {
    }

    public Node(Map<Character, Node> childs) {
        this.childs = childs;
    }

    public Map<Character, Node> getChilds() {
        return childs;
    }
}
