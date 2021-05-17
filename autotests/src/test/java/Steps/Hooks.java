package Steps;

import Core.DriverConfig;
import cucumber.api.java.Before;

public class Hooks {
    @Before
    public void beforeScenario(){
        DriverConfig.initialize();
    }

//    @After
//    public void afterScenario(){
//        DriverConfig.quit();
//    }
}
