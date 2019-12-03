package statecensusanalyserproblem;


import com.bridgelabz.statecensusanalyserproblem.CSVStates;
import com.bridgelabz.statecensusanalyserproblem.CSVStatesCensus;
import com.bridgelabz.statecensusanalyserproblem.CensusCsvException;
import com.bridgelabz.statecensusanalyserproblem.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    @Test
    public void checkToEnsure_NumberOfRecordsMatches() throws CensusCsvException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode.csv");
        Assert.assertEquals(37, stateCensusAnalyser.readStateData(CSVStates.class));
    }

    @Test
    public void givenWrongFileName_ShouldThrowNoSuchFileException() {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode12.csv");
        try {
            int value = stateCensusAnalyser.readStateData(CSVStates.class);
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
            int checkNumberOfRecords = stateCensusAnalyser.readStateData(CSVStates.class);
        } catch (CensusCsvException e) {
            e.printStackTrace();
            Assert.assertEquals("Exception due to Header or mismatch data", e.getMessage());
        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectDelimiterPosition_ShouldReturnException() throws IOException, CensusCsvException {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode.csv");
            int value = stateCensusAnalyser.readStateData(CSVStates.class);
            Assert.assertEquals(37, value);
        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Exception due to Header or mismatch data", e.getMessage());
        }
    }

    @Test
    public void givenMethod_ifFoundNoHeader_ShouldReturnException() {

        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCode.csv");
            int value = stateCensusAnalyser.readStateData(CSVStates.class);
            Assert.assertEquals(37, value);
        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Exception due to Header or mismatch data", e.getMessage());
        }
    }

    @Test
    public void givenMethod_CheckNumberOfRecodesMatchesOrNot_ForStatesCensus_ShouldReturnTrue() throws IOException, CensusCsvException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCensusData.csv");
        int count = stateCensusAnalyser.readStateData(CSVStatesCensus.class);
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenMethod_ifFoundIncorrectName_OfStatesCensusFile_ShouldThrowException() throws IOException {
        StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCensusData.csv");
        try {
            int value = stateCensusAnalyser.readStateData(CSVStatesCensus.class);
            Assert.assertEquals(29, value);
        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Please enter proper file name", e.getMessage());
        }
    }

    @Test
    public void givenWrongFilePath_ShouldThrowRunTimeException_ReturnFalse() {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/gradlew.bat");
            int checkNumberOfRecords = stateCensusAnalyser.readStateData(CSVStatesCensus.class);
        } catch (CensusCsvException e) {
            e.printStackTrace();
            Assert.assertEquals("Exception due to Header or mismatch data", e.getMessage());
        }
    }

    @Test
    public void givenMethod_ifFoundIncorrectDelimiterPositionInCSVStatusCensus_ShouldReturnException() throws IOException, CensusCsvException {
        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/java/com/bridgelabz/statecensusanalyserproblem/CSVStatesCensus.java");
            int value = stateCensusAnalyser.readStateData(CSVStatesCensus.class);
            Assert.assertEquals(29, value);
        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Exception due to Header or mismatch data", e.getMessage());
        }
    }

    @Test
    public void givenMethod_ifFoundNoHeaderInCSVStatusCensus_ShouldReturnException() {

        try {
            StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser("/home/admin265/IdeaProjects/StateCensusAnalyserProblem/src/main/resources/StateCensusData.csv");
            int value = stateCensusAnalyser.readStateData(CSVStatesCensus.class);
            Assert.assertEquals(29, value);
        } catch (CensusCsvException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("Exception due to Header", e.getMessage());
        }
    }
}