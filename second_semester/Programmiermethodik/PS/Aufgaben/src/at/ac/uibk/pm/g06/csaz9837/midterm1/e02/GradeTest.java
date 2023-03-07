package at.ac.uibk.pm.g06.csaz9837.midterm1.e02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    @Test
    void gradeException() {
        assertThrows(IllegalArgumentException.class, () -> Grade.from(-1));
        assertThrows(IllegalArgumentException.class, () -> Grade.from(120));
    }

    @Test
    void isGradeNGD5() {
        assertEquals(Grade.NGD5, Grade.from(59));
        assertEquals(Grade.NGD5, Grade.from(0));
    }

    @Test
    void isGradeGEN4() {
        assertEquals(Grade.GEN4, Grade.from(69));
        assertEquals(Grade.GEN4, Grade.from(60));
    }

    @Test
    void isGradeBEF3() {
        assertEquals(Grade.BEF3, Grade.from(79));
        assertEquals(Grade.BEF3, Grade.from(70));
    }

    @Test
    void isGradeGUT2() {
        assertEquals(Grade.GUT2, Grade.from(89));
        assertEquals(Grade.GUT2, Grade.from(80));
    }

    @Test
    void isGradeSGT1() {
        assertEquals(Grade.SGT1, Grade.from(100));
        assertEquals(Grade.SGT1, Grade.from(90));
    }
}