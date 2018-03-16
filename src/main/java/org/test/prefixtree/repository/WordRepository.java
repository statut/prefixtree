package org.test.prefixtree.repository;

import java.util.Collection;

/**
 * Repository to work with words.
 */
public interface WordRepository {
    /**
     * Find all words.
     *
     * @return all words.
     */
    Collection<String> findAll();
}
