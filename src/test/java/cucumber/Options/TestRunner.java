package cucumber.Options;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {"stepdefinitions"},
      //  tags = "@addPlace",
        plugin = {"pretty", "json:target/jsonReports/cucumber-report.json"}
)

public class TestRunner {


}
