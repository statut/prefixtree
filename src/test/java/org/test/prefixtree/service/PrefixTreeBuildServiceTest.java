package org.test.prefixtree.service;

import com.google.common.collect.ImmutableMap;
import org.test.prefixtree.repository.WordRepository;
import org.test.prefixtree.tree.Node;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PrefixTreeBuildServiceTest {
    private PrefixTreeBuildService service = new PrefixTreeBuildService(mock(WordRepository.class));

    @DataProvider
    public static Object[][] testData() {
        return new Object[][]{
            {"a", new Node(ImmutableMap.of('a', new Node(Collections.emptyMap())))},
            {"ab", new Node(ImmutableMap.of('a', new Node(ImmutableMap.of('b', new Node(Collections.emptyMap())))))}
        };
    }

    @Test(dataProvider = "testData")
    public void testAddToTree(String word, Node expectedTree) {
        Node actual = service.addToTree(word, new Node(new HashMap<>()));
        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expectedTree);
    }
}