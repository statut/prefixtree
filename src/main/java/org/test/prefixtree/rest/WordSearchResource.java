package org.test.prefixtree.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.prefixtree.service.WordSearchService;

import java.util.Collection;

/**
 * REST controller for serching words.
 */
@RestController
public class WordSearchResource {
    @Autowired
    private WordSearchService wordSearchService;

    @RequestMapping("/find/{prefix}")
    public Collection<String> findWords(@PathVariable String prefix) {
        return wordSearchService.findWords(prefix);
    }

}
