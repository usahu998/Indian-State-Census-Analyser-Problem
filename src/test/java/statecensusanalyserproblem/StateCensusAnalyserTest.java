package statecensusanalyserproblem;


import com.bridgelabz.statecensusanalyserproblem.CensusCsvException;
import com.bridgelabz.statecensusanalyserproblem.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    @Test
    public void checkToEnsure_NumberOfRecordsMatches() throws CensusCsvException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode.csv");
        Assert.assertEquals(37, stateCensusAnalyser.readStateData());
    }

    @Test
    public void givenWrongFileName_ShouldThrowNoSuchFileException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode12.csv");
        try {
            int value = stateCensusAnalyser.readStateData();
            Assert.assertEquals(37, value);

        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Please enter proper file name", e.getMessage());
        }
    }

    @Test
    public void givenWrongFilePath_ShouldThrowRunTimeException() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/gradlew.bat");
            int checkNumberOfRecords = stateCensusAnalyser.readStateData();
        } catch (CensusCsvException e) {
            e.printStackTrace();
            Assert.assertEquals("binding of file to failed", e.getMessage());
        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectDelimiterPosition_ShouldReturnException() throws IOException, CensusCsvException {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode.csv");
            int value = stateCensusAnalyser.readStateData();
            Assert.assertEquals(37, value);
        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Exception due to incorrect delimiter position", e.getMessage());
        }
    }
}