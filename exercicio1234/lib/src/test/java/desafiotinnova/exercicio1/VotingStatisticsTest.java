/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package desafiotinnova.exercicio1;

import org.junit.Test;
import static org.junit.Assert.*;

public class VotingStatisticsTest {
    @Test public void shouldCalculateValidVoters() {
        VotingStatistics exercise = new VotingStatistics();
        assertEquals(80, exercise.percentageValidVoters());
    }

    @Test public void shouldCalculateNullVoters() {
        VotingStatistics exercise = new VotingStatistics();
        assertEquals(5, exercise.percentageNullVoters());
    }

    @Test public void shouldCalculteWhiteVoters() {
        VotingStatistics exercise = new VotingStatistics();
        assertEquals(15, exercise.percentageWhiteVoters());
    }
}
