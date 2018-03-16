package org.test.prefixtree.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.mockito.Mock;
import org.test.prefixtree.tree.Node;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PrefixTreeWordSearchServiceTest {
    @Mock
    private PrefixTreeBuildService prefixTreeBuildService;

    private PrefixTreeWordSearchService service;

    @BeforeClass
    public void setUp() throws IOException {
        initMocks(this);
        Node root = new Node(new HashMap<>());

        Node w = new Node(new HashMap<>());
        w.getChilds().put('o',
            new Node(ImmutableMap.of('r',
                new Node(ImmutableMap.of('d',
                    new Node(Collections.emptyMap()))))));
        w.getChilds().put('e', new Node(Collections.emptyMap()));
        root.getChilds().put('t', new Node(Collections.emptyMap()));
        root.getChilds().put('w', w);

        when(prefixTreeBuildService.build()).thenReturn(root);
        service = new PrefixTreeWordSearchService(prefixTreeBuildService);
    }

    @Test(dataProvider = "testData")
    public void testFindWords(String prefix, Collection<String> expectedResult) {
        assertThat(service.findWords(prefix)).containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    @DataProvider
    public static Object[][] testData() {
        return new Object[][]{
            {"w", ImmutableList.of("word", "we")},
            {"word", ImmutableList.of("word")},
            {"t", ImmutableList.of("t")},
            {"", ImmutableList.of("word", "we", "t")},
            {"wor", ImmutableList.of("word")},
            {"invalid", Collections.emptyList()}
        };
    }
}