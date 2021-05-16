import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // путь в фича-файлы
        features = {"src/test/resources/"},
        // путь к классам Step Definition
        glue = {"Steps"},
        // формат отчета, помещается в таргет-ветке
        plugin = {
                "pretty"
        }
)
public class Runner {
}
