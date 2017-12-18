package pl.edu.agh.to2.dziki.presenter.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

public class TextAutoFillerTest {

    private TextAutoFiller autoFiller;
    private Set<String> testDictionary;


    @Before
    public void setUp() throws Exception {

        testDictionary = new HashSet<>(
                asList("tolerant", "drop", "straighten", "wear out", "heaven"
                        , "tail", "incident", "joint", "ghostwriter", "uniform"
                        , "decoration", "sting", "bitter", "danger", "build"
                        , "password", "spider", "orange", "arrogant", "stride"
                        , "fastidious", "prey", "drill", "salon", "bronze", "guide", "venture"));
        autoFiller = new TextAutoFiller(testDictionary);
    }

    @Test
    public void shouldNotMatchEmptyPrefix() {
        // given
        String prefix = "";

        // when
        String result = autoFiller.fillOrGetNextMatch(prefix);

        // then
        assertThat(result).isNull();
    }

    @Test
    public void shouldMatchProperWords() {
        // given
        String prefix = "d";

        // when
        autoFiller.fillOrGetNextMatch(prefix);
        List<String> expectedMatches = testDictionary.stream().filter(s -> s.startsWith(prefix)).collect(toList());

        // then
        expectedMatches.forEach(match -> assertThat(autoFiller.contains(match)).isTrue());

    }

    @Test
    public void shouldMatchAndIterateOverAllWords() {
        // given
        String prefix = "s";
        String word = "straighten";

        // when
        autoFiller.fillOrGetNextMatch(prefix);
        Set<String> resultSet = new HashSet<>();

        for (int i = 0; i < testDictionary.size(); i++) {
            resultSet.add(autoFiller.fillOrGetNextMatch(word));
        }

        Set<String> expectedResultSet = testDictionary.stream()
                .filter(s -> s.startsWith(prefix))
                .map(String::toUpperCase)
                .collect(toSet());

        // then
        assertThat(resultSet).containsAll(expectedResultSet);
        assertThat(resultSet).hasSameSizeAs(expectedResultSet);

    }
}