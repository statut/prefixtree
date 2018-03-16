package org.test.prefixtree.service;

import java.util.Collection;

/**
 * Service to work with words.
 */
public interface WordSearchService {
    /**
     * Find all words with common prefix.
     *
     * @param prefix prefix.
     * @return all words with given prefix.
     */
    Collection<String> findWords(String prefix);
}
