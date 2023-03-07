package Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    @ParameterizedTest()
    @CsvSource({"the-Stealth-Warrior,theStealthWarrior", "'',''", "The-stealth-warrior, TheStealthWarrior", "the-stealth-warrior, theStealthWarrior", "The-stealth-Warrior, TheStealthWarrior"})
    void hyphenTest(String s1, String s2){
        assertEquals(s2,Solution.toCamelCase(s1));
    }
    @Test
    void hyphenExepction(){
        String s1 = null;
        assertThrows(NullPointerException.class, () -> Solution.toCamelCase(s1));
    }
    @ParameterizedTest()
    @CsvSource({"The_Stealth_Warrior, TheStealthWarrior", "the_stealth_warrior, theStealthWarrior", "The_Stealth_Warrior, TheStealthWarrior", "the_StealthWarrior, theStealthWarrior"})
    void underscoreTest(String s1, String s2){
        assertEquals(s2, Solution.toCamelCase(s1));
    }
}