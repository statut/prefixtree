package org.test.prefixtree.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.prefixtree.repository.WordRepository;
import org.test.prefixtree.tree.Node;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Service to build tree from words.
 */
@Service
public class PrefixTreeBuildService {
    private WordRepository wordRepository;

    @Autowired
    public PrefixTreeBuildService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    /**
     * Build word tree.
     */
    public Node build() {
        Node root = new Node(new HashMap<>());
        Collection<String> words = wordRepository.findAll();
        if (CollectionUtils.isNotEmpty(words)) {
            //TODO: Parallel adding
            words.forEach(word -> addToTree(word, root));
        }
        return root;
    }

    Node addToTree(String word, Node root) {
        Map<Character, Node> childs = root.getChilds();
        for (char symbol : word.toCharArray()) {
            //TODO: Maps should be immutable.
            Map<Character, Node> map = childs.getOrDefault(symbol, new Node(new HashMap<>())).getChilds();
            childs.put(symbol, new Node(map));
            childs = map;
        }
        return root;
    }
}
