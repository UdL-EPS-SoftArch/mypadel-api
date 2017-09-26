package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.When;

import java.util.Date;

public class JoinMatch {
    private static String matchName;
    private static Date matchDate;

    public void setup(){
        JoinMatch.matchName = "";
        JoinMatch.matchDate = null;
    }

    @When("^I join to a match with name \"([^\"]*)\" and datetime \"([^\"]*)\"$")
    public void iJoinToAMatchWithNameAndDatetime(String match, Date dateTime) throws Throwable {
        JoinMatch.matchName = match;
        JoinMatch.matchDate = dateTime;
    }
}
