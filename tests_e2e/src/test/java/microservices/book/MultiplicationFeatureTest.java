package microservices.book;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author moises.macero
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber", "junit:target/junit-report.xml" },
        features = "src/test/resources/multiplication.feature")
public class MultiplicationFeatureTest {
}
