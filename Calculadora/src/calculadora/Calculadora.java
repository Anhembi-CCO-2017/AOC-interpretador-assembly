
package calculadora;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Calculadora {

    public static void main(String[] args) {
        
        // entrada de dados
        JTextArea msg = new JTextArea("");
        
        msg.setLineWrap(true); //quebrar linhas
        msg.setRows(10); //numero de linhas
        msg.setColumns(10); //numero de colunas
        msg.setWrapStyleWord(true); // qubrar paçavras
        
        // adicionando scroll na entrada de dados
        JScrollPane scrollPane = new JScrollPane(msg);
        
        // abrindo dialogo
        JOptionPane.showMessageDialog(null, scrollPane);
        
        String valor = msg.getText();
        
        // Validar a expressão        
        IdentificarExpressao ie = new IdentificarExpressao();
               
        
    }
    
}
