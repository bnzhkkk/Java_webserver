package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, UserProfile> users;

    public AccountService() {
        users = new HashMap<>();
    }

    public Map<String, UserProfile> getUsers() {
        return users;
    }

    public void singUp(String login, String password) {
        users.put(login, new UserProfile(login, password));
    }

    public boolean signIn(String login, String pass) {
        if (users.get(login).getPassword().equals(pass) && users.get(login) != null)
            return true;
        else
            return false;
    }
}