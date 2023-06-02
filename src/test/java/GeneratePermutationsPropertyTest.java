
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;
import org.example.GeneratePermutations;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class GeneratePermutationsPropertyTest {

    @Report(Reporting.GENERATED)
    @Property(tries = 500)
    @StatisticsReport(format = Histogram.class)
    void generateValidPermutations(@ForAll("generateValidArgs") Tuple.Tuple3<String, Integer, List<String>> args){
        List<String> result = GeneratePermutations.generatePermutations(args.get1(), args.get2());
        List<String> expected = args.get3();
        String lunghezzaStringa = args.get1().length() <= 1 ? "Str.length () <= 1" : "Str.length () > 1";
        String lunghezzaPerm = args.get2() <= 1 ? "Perm length <= 1 " : "Perm length > 1";
        Statistics.collect(lunghezzaStringa, lunghezzaPerm);
        assertTrue(expected.size() == result.size() && result.containsAll(expected) && expected.containsAll(result));
    }

    @Provide
    Arbitrary<Tuple.Tuple3<String, Integer, List<String>>> generateValidArgs(){
        Arbitrary<Integer> length = Arbitraries.integers().between(0, 3);
        return length.flatMap(lunghezza -> Arbitraries.strings().ofMinLength(0).ofMaxLength(3).withCharRange('a','z').injectDuplicates(0.0)
                .flatMap(stringa ->Arbitraries.strings().ofLength(lunghezza).withChars(stringa.toCharArray()).list().uniqueElements().filter(list-> list.stream().count() == Math.pow(stringa.length(),lunghezza))
                .map(expected -> Tuple.of(stringa, lunghezza, expected)))).ignoreException(net.jqwik.api.JqwikException.class).ignoreException(net.jqwik.api.TooManyFilterMissesException.class);
    }

    @Report(Reporting.GENERATED)
    @Property(tries = 500)
    @StatisticsReport(format = Histogram.class)
    void generateInvalidPermutations(@ForAll("generateInvalidArgs") Tuple.Tuple2<String, Integer> args){
        String strNull = args.get1() == null ? "str null" : "str not null";
        String invalid = args.get2() < 0 ? "length < 0" : "length >= 0";
        Statistics.collect(strNull," and ", invalid);
        assertThrows(IllegalArgumentException.class, () -> {GeneratePermutations.generatePermutations(args.get1(), args.get2());});
    }

    @Provide
    Arbitrary<Tuple.Tuple2<String,Integer>> generateInvalidArgs(){
        Arbitrary<Integer> lengthInvalid = Arbitraries.integers().lessOrEqual(-1);
        Arbitrary<Integer> lengthValid = Arbitraries.integers().between(0,3);
        Arbitrary<String> strInvalid = Arbitraries.strings().injectNull(1.0);
        Arbitrary<String> strValid = Arbitraries.strings().withCharRange('a','z').ofMinLength(0).ofMaxLength(3).injectDuplicates(0.0);
        return Arbitraries.oneOf(Combinators.combine(strValid,lengthInvalid).as((s,l) -> Tuple.of(s,l)),
                Combinators.combine(strInvalid,lengthValid).as((s,l) -> Tuple.of(s,l)),
                Combinators.combine(strInvalid,lengthInvalid).as((s,l) -> Tuple.of(s,l))
        );
    }

}
