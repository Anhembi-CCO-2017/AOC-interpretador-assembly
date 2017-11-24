package calculadora;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Calculadora {
    
    public static void main(String[] args) {
        Interpretador core = new Interpretador();
        
        do {
            // entrada de dados
            JTextArea msg = new JTextArea("Digite dados aqui.");
            msg.setLineWrap(true); //quebrar linhas
            msg.setRows(30); //numero de linhas
            msg.setColumns(30); //numero de colunas
            msg.setWrapStyleWord(true); // qubrar palavras

            // adicionando scroll na entrada de dados
            JScrollPane scrollPane = new JScrollPane(msg);

            // abrindo dialogo
            JOptionPane.showMessageDialog(null, scrollPane);

            core.data = msg.getText();  
        } while (!core.identificarExpressao());
        // Validar a expressão        
        
        core.build();
 
    }
    
}
