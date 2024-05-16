package account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServIMPL implements Serv {
    private int usersLimit;
    private final static int DEFAULT_LIMIT = 10;
    static final Logger logger = LogManager.getLogger(ServIMPL.class.getName());

    public ServIMPL() {
        usersLimit = DEFAULT_LIMIT;
    }

    @Override
    public int getUsersLimit() {
        return this.usersLimit;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        logger.info("Set limit to: " + usersLimit);
        this.usersLimit = usersLimit;
    }
}