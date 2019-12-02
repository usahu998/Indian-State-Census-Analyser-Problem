package statecensusanalyserproblem;

import com.bridgelabz.statecensusanalyserproblem.CsvException;
import com.bridgelabz.statecensusanalyserproblem.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    @Test
    public void checkToEnsure_NumberOfRecordsMatches() throws CsvException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode.csv");
        Assert.assertEquals(37, stateCensusAnalyser.readStateData());
    }

    @Test
    public void givenWrongFileName_ShouldThrowNoSuchFileException() throws CsvException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin165/Desktop/censusAnalyser/src/main/resources/StateCode12.csv");
        //  int checkNumberOfRecords = stateCensusAnalyser.readStateData();
        Assert.assertEquals(37, stateCensusAnalyser.readStateData());
    }

    @Test
    public void givenWrongFileType_ShouldThrowRunTimeException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/gradlew.bat");
            int checkNumberOfRecords = stateCensusAnalyser.readStateData();
        } catch (CsvException e) {
            e.printStackTrace();
            Assert.assertEquals("binding of file to failed", e.getMessage());
        }
    }
}