import static org.junit.jupiter.api.Assertions.*;

import org.example.IsPalindrome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

public class IsPalindromeTest {
    @ParameterizedTest
    @NullSource
    @DisplayName("T1W-T1B: strIsNull")
    void t1strIsNull(String str) {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome(str,0,1);});
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("T2B: str empty")
    void t2StrEmpty(String str) {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome(str,0,1);});
    }

    @Test
    @DisplayName("T3B: start < 0")
    void t3StartLowerThan0() {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome("abcd",-1,3);});
    }

    @Test
    @DisplayName("T4B: end < 0")
    void t4EndLowerThan0() {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome("abcd",0,-1);});
    }

    @Test
    @DisplayName("T5B: start >= str.length()")
    void t5StartGreaterOrEqualStrLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome("abcd",4,3);});
    }

    @Test
    @DisplayName("T6B: end >= str.length()")
    void t6EndGreaterOrEqualsStrLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome("abcd",0,4);});
    }

    @Test
    @DisplayName("T7B: end < start")
    void t7EndLowerThanStart() {
        assertThrows(IllegalArgumentException.class, () -> {
            IsPalindrome.isPalindrome("abcd",2,1);});
    }

    @Test
    @DisplayName("T8B: str not null and end - start = 0")
    void t8StrNotNullAndEndEqualsStart() {
        assertEquals(true, IsPalindrome.isPalindrome("abcd",0,0));
    }

    @ParameterizedTest
    @MethodSource("palArgs")
    @DisplayName("T3W-T10B: palindrome")
    void t10validPalindrome(String str, int start, int end) {
        assertEquals(true, IsPalindrome.isPalindrome(str,start,end));
    }

    static Stream<Arguments> palArgs(){
        return Stream.of(
                Arguments.of("otto", 1, 2),
                Arguments.of("aerea", 2, 2),
                Arguments.of("aveva", 1, 3),
                Arguments.of("aci d ica", 2, 6),
                Arguments.of("ingegni", 0, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("randNotPalArgs")
    @DisplayName("T2W-T9B: str not null and end – start > 1 and not palindrome")
    void t9invalidPalindrome(String str, int start, int end) {
        assertEquals(false, IsPalindrome.isPalindrome(str, start, end));
    }
    static Stream<Arguments> randNotPalArgs(){
        return Stream.of(
                Arguments.of("abcde", 1, 2),
                Arguments.of("qwerty", 2, 4),
                Arguments.of("asdfg", 1, 3),
                Arguments.of("zxc vb", 2, 3),
                Arguments.of("$e €fl", 3, 4)
        );
    }


}

