/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

/**
 *
 * @author Matheus
 */
public class IdentificarExpressao {

    public IdentificarExpressao() {
    }
    
    public boolean isAssembly(String valor){
        return false;
    }
    
    public boolean isEquacao(String valor){
        Equacao eq = new Equacao();
        
        if (eq.isEquacao(valor)) {
            
          return true;  
        }
        return false;
    }
}
