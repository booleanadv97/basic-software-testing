
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import org.example.GeneratePermutations;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class GeneratePermutationsTest {

    @ParameterizedTest
    @DisplayName("TB1")
    @NullSource
    void t1StrIsNull(String value) {
        assertThrows(IllegalArgumentException.class, ()-> { GeneratePermutations.generatePermutations(value,1); });
    }

    @ParameterizedTest
    @ValueSource(strings = {"abC","dEf", "$€ "})
    @DisplayName("TB2")
    void t2LengthLowerThan0(String str) {
        assertThrows(IllegalArgumentException.class, ()-> { GeneratePermutations.generatePermutations(str,-1); });
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("TB3")
    void t3StrIsEmpty(String value) {
        assertEquals(new ArrayList<String>(), GeneratePermutations.generatePermutations(value,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abC","dEf", "$€ "})
    @DisplayName("TB4")
    void t4Length0(String str) {
        assertEquals(Arrays.asList(""), GeneratePermutations.generatePermutations(str,0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a","E","$", " "})
    @DisplayName("TB5")
    void t5Str1AndLength1(String str) {
        assertEquals(Arrays.asList(str), GeneratePermutations.generatePermutations(str,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abC","dEf", "$e "})
    @DisplayName("TB6")
    void t6StrGreaterThan1AndLength1(String str) {
        assertEquals(Arrays.asList(Character.toString(str.charAt(0)),Character.toString(str.charAt(1)),Character.toString(str.charAt(2))), GeneratePermutations.generatePermutations(str,1));
    }

    @ParameterizedTest
    @MethodSource("t7args")
    @DisplayName("TB7")
    void t7StrGreaterThan1AndLengthGreaterThan1(ArrayList<String> expected, String str, int length) {
        assertEquals(expected, GeneratePermutations.generatePermutations(str,length));
    }

    static Stream<Arguments> t7args(){
        return Stream.of(
                Arguments.of(new ArrayList<String>(Arrays.asList("aa", "ab", "ac", "ba", "bb", "bc", "ca", "cb", "cc")), "abc", 2),
                Arguments.of(new ArrayList<String>(Arrays.asList("aaa", "aab", "aac", "aad", "aba", "abb", "abc", "abd", "aca", "acb", "acc", "acd",
                        "ada", "adb", "adc", "add", "baa", "bab", "bac", "bad", "bba", "bbb", "bbc", "bbd",
                        "bca", "bcb", "bcc", "bcd", "bda", "bdb", "bdc", "bdd", "caa", "cab", "cac", "cad",
                        "cba", "cbb", "cbc", "cbd", "cca", "ccb", "ccc", "ccd", "cda", "cdb", "cdc", "cdd",
                        "daa", "dab", "dac", "dad", "dba", "dbb", "dbc", "dbd", "dca", "dcb", "dcc", "dcd",
                        "dda", "ddb", "ddc", "ddd")), "abcd", 3),
                Arguments.of(new ArrayList<String>(Arrays.asList("aaa", "aaD", "aa$", "aa ", "aDa", "aDD", "aD$", "aD ", "a$a", "a$D", "a$$", "a$ ",
                        "a a", "a D", "a $", "a  ", "Daa", "DaD", "Da$", "Da ", "DDa", "DDD", "DD$", "DD ",
                        "D$a", "D$D", "D$$", "D$ ", "D a", "D D", "D $", "D  ", "$aa", "$aD", "$a$", "$a ",
                        "$Da", "$DD", "$D$", "$D ", "$$a", "$$D", "$$$", "$$ ", "$ a", "$ D", "$ $", "$  ",
                        " aa", " aD", " a$", " a ", " Da", " DD", " D$", " D ", " $a", " $D", " $$", " $ ",
                        "  a", "  D", "  $", "   ")), "aD$ ",3)
                );
    }
    /* playground property based testing
    @Property
    @Report(Reporting.GENERATED)
    void test1(@ForAll @Size(100)  List<@IntRange(min=1, max=50) Integer> ints, @ForAll @IntRange(min=-10,max=150) int startIndex, @ForAll @IntRange(min=-50,max=100) final int valueToFind){

    }*/
}

