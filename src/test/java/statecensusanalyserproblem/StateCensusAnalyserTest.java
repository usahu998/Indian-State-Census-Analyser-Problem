package statecensusanalyserproblem;

import com.bridgelabz.statecensusanalyserproblem.StateCensusAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    @Test
   public void checkToEnsure_NumberOfRecordsMatches() throws IOException {
        StateCensusAnalyser stateCensusAnalyser=new StateCensusAnalyser();
          Assert.assertEquals(37,stateCensusAnalyser.readStateData());
       }
}

