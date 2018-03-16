package org.test.prefixtree.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementation of repository to read words from file.
 */
@Repository
public class LocalFileWordRepository implements WordRepository {
    public static final String FILE = "classpath:words.txt";

    @Override
    public Collection<String> findAll() {
        try {
            return new BufferedReader(new InputStreamReader(ResourceUtils.getURL(FILE).openStream()))
                    .lines()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            //TODO: Add logging
            throw new RuntimeException();
        }
    }
}
