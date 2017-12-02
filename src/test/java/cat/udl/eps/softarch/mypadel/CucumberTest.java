package cat.udl.eps.softarch.mypadel;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin={"pretty"}, features="src/test/resources")
public class CucumberTest {
}
