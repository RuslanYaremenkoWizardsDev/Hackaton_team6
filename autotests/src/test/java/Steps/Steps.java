package Steps;

import Interfaces.IAuthPage;
import Pages.AuthPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Steps {
    protected final Logger logger = LogManager.getLogger(this.getClass());
     IAuthPage iAuthPage= new AuthPage();
}
