import com.github.damienbarrion.DiceRollChecker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiceRollCheckerTest {

    @Test
    public void testOnes() {
        assertEquals(1, new DiceRollChecker(1, 2, 3, 4, 5).ones());
        assertEquals(2, new DiceRollChecker(1, 2, 1, 4, 5).ones());
        assertEquals(0, new DiceRollChecker(6, 2, 2, 4, 5).ones());
        assertEquals(4, new DiceRollChecker(1, 2, 1, 1, 1).ones());
    }

    @Test
    public void testTwos() {
        assertEquals(4, new DiceRollChecker(1, 2, 3, 2, 6).twos());
        assertEquals(10, new DiceRollChecker(2, 2, 2, 2, 2).twos());
    }

    @Test
    public void testThrees() {
        assertEquals(6, new DiceRollChecker(1, 2, 3, 2, 3).threes());
        assertEquals(12, new DiceRollChecker(2, 3, 3, 3, 3).threes());
    }

    @Test
    public void testFours() {
        assertEquals(12, new DiceRollChecker(4, 4, 4, 5, 5).fours());
        assertEquals(8, new DiceRollChecker(4, 4, 5, 5, 5).fours());
        assertEquals(4, new DiceRollChecker(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void testFives() {
        assertEquals(10, new DiceRollChecker(4, 4, 4, 5, 5).fives());
        assertEquals(15, new DiceRollChecker(4, 4, 5, 5, 5).fives());
        assertEquals(20, new DiceRollChecker(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void testSixes() {
        assertEquals(0, new DiceRollChecker(4, 4, 4, 5, 5).sixes());
        assertEquals(6, new DiceRollChecker(4, 4, 6, 5, 5).sixes());
        assertEquals(18, new DiceRollChecker(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void testOnePair() {
        assertEquals(6, new DiceRollChecker(3, 4, 3, 5, 6).onePair());
        assertEquals(10, new DiceRollChecker(5, 3, 3, 3, 5).onePair());
        assertEquals(12, new DiceRollChecker(5, 3, 6, 6, 5).onePair());
    }

    @Test
    public void testTwoPairs() {
        assertEquals(16, new DiceRollChecker(3, 3, 5, 4, 5).twoPairs());
        assertEquals(16, new DiceRollChecker(3, 3, 5, 5, 5).twoPairs());
        assertEquals(0, new DiceRollChecker(1, 2, 3, 3, 3).twoPairs());
    }

    @Test
    public void testThreeOfAKind() {
        assertEquals(9, new DiceRollChecker(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15, new DiceRollChecker(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(9, new DiceRollChecker(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void testFourOfAKind() {
        assertEquals(12, new DiceRollChecker(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20, new DiceRollChecker(5, 5, 5, 4, 5).fourOfAKind());
        assertEquals(12, new DiceRollChecker(3, 3, 3, 3, 3).fourOfAKind());
    }

    @Test
    public void testSmallStraight() {
        assertEquals(15, new DiceRollChecker(1, 2, 3, 4, 5).smallStraight());
        assertEquals(15, new DiceRollChecker(2, 3, 4, 5, 1).smallStraight());
        assertEquals(0, new DiceRollChecker(1, 2, 2, 4, 5).smallStraight());
    }

    @Test
    public void testLargeStraight() {
        assertEquals(20, new DiceRollChecker(6, 2, 3, 4, 5).largeStraight());
        assertEquals(20, new DiceRollChecker(2, 3, 4, 5, 6).largeStraight());
        assertEquals(0, new DiceRollChecker(1, 2, 2, 4, 5).largeStraight());
    }

    @Test
    public void testFullHouse() {
        assertEquals(18, new DiceRollChecker(6, 2, 2, 2, 6).fullHouse());
        assertEquals(0, new DiceRollChecker(2, 3, 4, 5, 6).fullHouse());
        assertEquals(0, new DiceRollChecker(1, 1, 1, 1, 2).fullHouse());
    }

    @Test
    public void testChance() {
        assertEquals(15, new DiceRollChecker(2, 3, 4, 5, 1).chance());
        assertEquals(16, new DiceRollChecker(3, 3, 4, 5, 1).chance());
    }

    @Test
    public void testYatzy() {
        assertEquals(50, new DiceRollChecker(6, 6, 6, 6, 6).yatzy());
        assertEquals(0, new DiceRollChecker(6, 6, 6, 6, 3).yatzy());
    }

}
