import java.util.ResourceBundle;

public class Spreektaal {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("spreektaal");

    public static String getTekst(String key) {
        return bundle.getString(key);
    }
}