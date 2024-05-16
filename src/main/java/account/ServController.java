package account;

public class ServController implements ServControllerMBean {
    private final Serv serv;

    public ServController(Serv serv) {
        this.serv = serv;
    }

    @Override
    public int getUsersLimit() {
        return serv.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        serv.setUsersLimit(usersLimit);
    }
}