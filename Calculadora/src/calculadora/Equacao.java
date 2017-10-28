
package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){
        Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
        Matcher matcher = regex.matcher(valor);
        return false;
    }
    
}
