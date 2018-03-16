package org.test.prefixtree.service;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.prefixtree.tree.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PrefixTreeWordSearchService implements WordSearchService {
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Node tree;

    @Autowired
    public PrefixTreeWordSearchService(PrefixTreeBuildService prefixTreeBuildService) {
        this.tree = prefixTreeBuildService.build();
    }

    @Override
    public Collection<String> findWords(String prefix) {
        Node current = tree;
        StringBuilder commonPrefix = new StringBuilder();
        char[] symbols = prefix.toCharArray();
        List<String> words = new ArrayList<>();
        for (int i = 0; i < symbols.length; i++) {
            if (current == null) {
                return Collections.emptyList();
            }
            current = current.getChilds().get(symbols[i]);
            commonPrefix.append(symbols[i]);
            if (i + 1 == prefix.length()) {
                break;
            }
        }
        collectWords(current, commonPrefix.toString(), words);
        return words;
    }

    /**
     * Builds words from the given node and given prefix.
     */
    private void collectWords(Node node, String prefix, List<String> words) {
        if (node == null) {
            return;
        }
        if (MapUtils.isEmpty(node.getChilds())) {
            words.add(prefix);
            return;
        }
        for (Entry<Character, Node> current : node.getChilds().entrySet()) {
            //TODO: Use forkjoin
            collectWords(current.getValue(), prefix + current.getKey(), words);
        }
    }
}
