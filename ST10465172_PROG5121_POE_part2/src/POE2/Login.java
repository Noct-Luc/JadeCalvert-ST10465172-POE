package POE2;

// parameters that allow users to have a successful testlogin
public class Login {
    private String Loginpass;
    private String loginUser;

    public Login(String loginUser,String Loginpass){

        this.Loginpass=Loginpass;
        this.loginUser=loginUser;
    }

    public String getLoginpass() {
        return Loginpass;}
    public String getLoginUser(){
        return loginUser ;}

    public static void Login(String loginUser,String loginpass){

    }
    public boolean checkLogin(String inputUser, String inputPass) {
        if (inputUser.equals(this.loginUser) && inputPass.equals(this.Loginpass)) {
            return true; // Login is successful
        } else {
            return false;
        }
    }
}