package View.ViewModel;

public class NewLogin {
    private String User;
    private String Password;

    public NewLogin(String user, String password) {
        User = user;
        Password = password;
    }

    public String getUser() {
        return User;
    }

    public String getPassword() {
        return Password;
    }
}
