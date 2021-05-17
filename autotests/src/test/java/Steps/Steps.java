package Steps;

import Interfaces.IAuthPage;
import Interfaces.IRegPage;
import Pages.AuthPage;
import Pages.RegPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Steps {
    protected final Logger logger = LogManager.getLogger(this.getClass());
     IAuthPage iAuthPage = new AuthPage();
     IRegPage iRegPage = new RegPage();
}
