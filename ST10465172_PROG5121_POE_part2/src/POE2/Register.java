package POE2;
public class Register  {
    private String username;
    private String password;
    private String phonenumber;
// parameters that allow users be successfully registered
    public Register(String username, String password, String phonenumber) {
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public static boolean isRegisteredPassword(String inputPassword, Register registeredUser) {
        if (inputPassword.equals(registeredUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


}
