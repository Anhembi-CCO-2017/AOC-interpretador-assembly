
package calculadora;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {
    private String valor;
    public ArrayList< ArrayList > matriz_operacao = new ArrayList< ArrayList>();

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){
        this.valor = valor.replaceAll(" ", "").toUpperCase();
        char[] vetor = {'/','*','+','-'};
        Pattern regex = Pattern.compile("[$&,\\:;=?!@#|]");
        Matcher matcher = regex.matcher(valor);

        Pattern regex1 = Pattern.compile("[/*+-]");
        Matcher matcher1 = regex1.matcher(valor);
        
        Pattern regex2 = Pattern.compile("[npoqrstuvwxyzNPOPQRSTUVWXYZ]");
        Matcher matcher3 = regex2.matcher(valor);
        
        // numeros de 0-9
        Pattern regex3 = Pattern.compile("[0-9]");
        Matcher matcher4 = regex3.matcher(valor);

        // identificar se tem caracteres especias
        if (matcher.find()) {
            return false;
        }
        
        //saber se tem mescla de numeros
        if(matcher3.find() || matcher4.find()){
            return false;

        }
        
        
        for (int i = 0; i < valor.length(); i++) {
            for (int j = 0; j < vetor.length; j++) {
                if (valor.charAt(i) == vetor[j]) {
                    for (int k = 0; k < vetor.length; k++) 
                        if (valor.charAt(i+1) == vetor[k]) 
                            return false;
            }
           }
        }
        
        int qtdaberto=0;
        int qtdfechado=0;
        
        //verifica se tem os mesmos numeros de parenteses!
        for (int i = 0; i < valor.length(); i++) {
            
            if (valor.charAt(i) == '(') {
                
                qtdaberto ++;
            }
            if (valor.charAt(i) == ')') {
                
                qtdfechado ++;
            }
        }
        
        if (qtdaberto != qtdfechado) {
            
            return false;
        }
        
        // checar se tem  tem formula
        if (matcher1.find()) {
            //saber se a formula é no final
            for (int j = 0; j < vetor.length; j++){
                if (valor.charAt(0) == vetor[j] || valor.charAt(valor.length() - 1) == vetor[j]){
                    return false;
                }
            }
            //convert();
            return true;
        }

        return false;
    }

    public void convert() {
        for (int index = 0; index < valor.length(); index++) {
            if(valor.charAt(index) == '(' || valor.charAt(index) == ')')
                continue;

            if(matriz_operacao.size() == 0)
                if(valor.charAt(index) == '+' || valor.charAt(index) == '-' || valor.charAt(index) == '/' || valor.charAt(index) == '*') {
                    ArrayList< String > data = new ArrayList< String >();
                    
                    data.add(""+valor.charAt(index-1));
                    data.add(""+valor.charAt(index));
                    if(valor.charAt(index+1) == '(' || valor.charAt(index+1) == ')')
                        data.add("");
                    else
                        data.add(""+valor.charAt(index+1));

                    matriz_operacao.add(data);

                    continue;
                }

            if(valor.charAt(index) == '+' || valor.charAt(index) == '-' || valor.charAt(index) == '/' || valor.charAt(index) == '*') {
                    ArrayList< String > data = new ArrayList< String >();
                    ArrayList< String > aux = matriz_operacao.get(matriz_operacao.size()-1);

                    if(valor.charAt(index-1) == ')' || (aux.get(0).equals("") && !aux.get(2).equals("")) || (!aux.get(0).equals("") && !aux.get(2).equals("")))
                        data.add("");
                    else
                        data.add(""+valor.charAt(index-1));

                    data.add(""+valor.charAt(index));

                    if(valor.charAt(index+1) == '(' || valor.charAt(index+1) == ')')
                        data.add("");
                    else
                        data.add(""+valor.charAt(index+1));

                    matriz_operacao.add(data);

                    continue;
            }
        }

        // Variaveis
        char[] registers = {'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int regCounter = 0;

        // Criando Linguagem de maquina.
        ArrayList< ArrayList > machine_code = new ArrayList< ArrayList>();
        ArrayList< ArrayList > dataClone = new ArrayList< ArrayList>(matriz_operacao);
        
        for(int index = 0; index < dataClone.size(); index++) {
            ArrayList data = dataClone.get(index);

            //LINHA COMPLETA (FAZER MOV)
            if(!data.get(0).equals("") && !data.get(2).equals("")) {
                ArrayList< String > operator = new ArrayList< String >();
                operator.add("MOVE");
                operator.add(""+registers[regCounter]);
                operator.add((String) data.get(0));
                machine_code.add(operator);

                ArrayList< String > operator2 = new ArrayList< String >();
                operator2.add(getOperation((String) data.get(1)));
                operator2.add(""+registers[regCounter]);
                operator2.add((String) data.get(2));
                machine_code.add(operator2);
                
                regCounter++;
            }

            //LINHA POS VAZIA, COM POS FINAL COMPLETA
            if(data.get(0).equals("") && !data.get(2).equals("")) {
                ArrayList<String> pastData = machine_code.get(machine_code.size()-1);
                String register = pastData.get(1);

                ArrayList< String > operator = new ArrayList< String >();
                operator.add(getOperation((String) data.get(1)));
                operator.add(register);
                operator.add((String) data.get(2));
                machine_code.add(operator);
            }

            //LINHA PRENCHIDA, COM FINAL VAZIO
            if(!data.get(0).equals("") && data.get(2).equals("")) {
                boolean findNext = false;
                int internalIndex = 0;

                while(!findNext) {
                    ArrayList<String> internalIndexData = dataClone.get(index+internalIndex);

                    if(!internalIndexData.get(0).equals("") && internalIndexData.get(2).equals("")) {
                        internalIndex++;
                    } else if(!internalIndexData.get(0).equals("") && !internalIndexData.get(2).equals("")) {
                        ArrayList< String > operator = new ArrayList< String >();
                        operator.add("MOVE");
                        operator.add(""+registers[regCounter]);
                        operator.add((String) internalIndexData.get(0));
                        machine_code.add(operator);

                        ArrayList< String > operator2 = new ArrayList< String >();
                        operator2.add(getOperation((String) internalIndexData.get(1)));
                        operator2.add(""+registers[regCounter]);
                        operator2.add((String) internalIndexData.get(2));
                        machine_code.add(operator2);

                        for (int i = internalIndex-1; i >= index; i--) {
                            ArrayList<String> roolbackData = dataClone.get(i);

                            ArrayList< String > operator3 = new ArrayList< String >();
                            operator3.add(getOperation((String) roolbackData.get(1)));
                            operator3.add(""+registers[regCounter]);
                            operator3.add((String) roolbackData.get(0));
                            machine_code.add(operator3);
                        }

                        index = internalIndex;
                        findNext = true;
                    }
                }
            }

            //LINHA VAZIA, COM FINAL VAZIO
            if(data.get(0).equals("") && data.get(2).equals("")) {
                ArrayList< String > operator = new ArrayList< String >();
                operator.add(getOperation((String) data.get(1)));
                operator.add("");
                operator.add(""+registers[regCounter]);
                machine_code.add(operator);
            }
        }
        
        //VERIFICAÇÃO DE MERGE
        for(int index = 0; index < machine_code.size(); index++) {
            ArrayList data = machine_code.get(index);

            if(data.get(1).equals(""))
            {
                ArrayList< String > newData = new ArrayList< String >();

                newData.add((String)data.get(0));
                newData.add(""+registers[0]);
                newData.add((String)data.get(2));

                machine_code.add(newData);
                machine_code.remove(index);
            }
        }

        for(ArrayList code : machine_code)
            System.out.println(code.get(0)+"\t"+code.get(1)+","+code.get(2));
    }

    private String getOperation(String op) {
        switch(op) {
            case "+":
                return "ADD";
            case "-":
                return "SUB";
            case "/":
                return "DIV";
            case "*":
                return "MPY";
        }

        return "";
    }
}
