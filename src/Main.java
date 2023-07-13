import Views.TelaLogin;
import Views.TelaUpdate;
import Views.TelasSettings;


public class Main {
    public static void main(String[] args) {
        TelasSettings.temaTelas();
        TelaLogin tl = new TelaLogin(null);
        tl.setVisible(true);
    }

}