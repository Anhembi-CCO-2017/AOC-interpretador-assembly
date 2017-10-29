
package calculadora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equacao {
    private String valor;
    public ArrayList< ArrayList > matriz_operacao = new ArrayList< ArrayList>();

    public Equacao() {
    }
    
    
    public boolean isEquacao(String valor){
        this.valor = valor.replaceAll(" ", "");
        char[] vetor = {'/','*','+','-'};
        Pattern regex = Pattern.compile("[$&,\\:;=?!@#|]");
        Matcher matcher = regex.matcher(valor);

        Pattern regex1 = Pattern.compile("[/*+-]");
        Matcher matcher1 = regex1.matcher(valor);
        
        Pattern regex2 = Pattern.compile("[a-zA-Z]");
        Matcher matcher3 = regex2.matcher(valor);
        
        // numeros de 0-9
        Pattern regex3 = Pattern.compile("[0-9]");
        Matcher matcher4 = regex3.matcher(valor);
        
        // identificar se tem caracteres especias
        if (matcher.find()) {
            return false;
        }
        
        //saber se tem mescla de numeros e letras
        if(matcher3.find()){
            if (matcher4.find()) {
                return false;
            }
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
        
        System.out.println(valor);

        //while(dataClone.size())
        for(int index = 0; index < dataClone.size(); index++) {
            ArrayList data = dataClone.get(index);

            //LINHA COMPLETA (FAZER MOV)
            if(!data.get(0).equals("") && !data.get(2).equals("")) {
                //if(index == 0) {
                    ArrayList< String > operator = new ArrayList< String >();
                    operator.add("MOVE");
                    operator.add(""+registers[regCounter]);
                    operator.add((String) data.get(0));

                    ArrayList< String > operator2 = new ArrayList< String >();
                    operator2.add(getOperation((String) data.get(1)));
                    operator2.add(""+registers[regCounter]);
                    operator2.add((String) data.get(2));

                    regCounter++;

                    machine_code.add(operator);
                    machine_code.add(operator2);
                    //dataClone.remove(dataCloneIndex);
                //}
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
                //dataClone.remove(dataCloneIndex);
            }

            //LINHA PRENCHIDA, COM FINAL VAZIO
            if(!data.get(0).equals("") && data.get(2).equals("")) {
                int increment = 1;
                boolean check = false;

                while(!check) {
                    ArrayList nextData = dataClone.get(index+increment);

                    if(!nextData.get(0).equals("") && nextData.get(2).equals("")) {
                        increment++;
                    } else if(!nextData.get(0).equals("") && !nextData.get(2).equals("")) {
                        //move data to up

                        ArrayList< String > operator = new ArrayList< String >();
                        operator.add("MOVE");
                        operator.add(""+registers[regCounter]);
                        operator.add((String) nextData.get(0));

                        ArrayList< String > operator2 = new ArrayList< String >();
                        operator2.add(getOperation((String) nextData.get(1)));
                        operator2.add(""+registers[regCounter]);
                        operator2.add((String) nextData.get(2));

                        machine_code.add(operator);
                        machine_code.add(operator2);

                        for(int i = 0; i < increment; i++) {
                            ArrayList pastData = dataClone.get(index+i);
                            ArrayList< String > operator3 = new ArrayList< String >();

                            operator3.add(getOperation((String) pastData.get(1)));
                            operator3.add(""+registers[regCounter]);
                            operator3.add((String) pastData.get(2));

                            machine_code.add(operator3);
                        }

                        regCounter++;
                        check = true;
                    }
                }
            }

            //LINHA VAZIA, COM FINAL VAZIO
            if(data.get(0).equals("") && data.get(2).equals("")) {
                String lastRegister = ""+registers[regCounter-1];

                ArrayList< String > operator = new ArrayList< String >();
                operator.add(getOperation((String) data.get(1)));
                operator.add(lastRegister);
                operator.add("");
                
                machine_code.add(operator);
            }
        }

        //VERIFICAÇÃO DE MERGE
        for(int index = 0; index < machine_code.size(); index++) {
            ArrayList data = machine_code.get(index);

            if(data.get(2).equals(""))
            {
                ArrayList nextData = machine_code.get(index+1);
                ArrayList< String > newData = new ArrayList< String >();

                newData.add((String)data.get(0));
                newData.add((String)data.get(1));
                newData.add((String)nextData.get(1));

                machine_code.add(newData);
                machine_code.remove(index);
            }
        }

        for(ArrayList code : machine_code)
            System.out.println(Arrays.toString(code.toArray()));
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

    public String convert2(){
        String result = "";
        System.out.println(valor);
        String[] vars = {"x", "y", "t"};
        Pattern p = Pattern.compile("\\(([^()]*)\\)((\\\\d*\\\\.\\\\d+)|(\\\\d+)|([\\\\+\\\\-\\\\*/\\\\(\\\\)]))");
        Matcher m = p.matcher(valor);

        System.out.println(m.groupCount());
        if(m.find()){
            for (int i = 0; i < m.groupCount(); i++) {
                System.out.println(m.group(i));
            }
       
        }

        for (int i = 0; i < valor.length(); i++) {
            
            switch(valor.charAt(i)){
                
                case '(':
                    if(i == 0){
                        result += "MOVE ";
                    }
                    break;
                case ')':
                    break;
                    
                case '+':
                    result += "ADD ";
                    
                    break;
                case '-':
                    result += "SUB ";

                    break;
                case '/':
                    result += "DIV ";
                    break;
                case '*':
                    result += "MPY ";
                    break;
                default:
                    if (i == 0) {
                        result += "MOVE "+ vars[0] + ", " +valor.charAt(i)+"\n";
                    }else {
                        result += valor.charAt(i) + "\n";
                    }
                    
            }
        }
        System.out.println(result);
        
        return result;
    }
   
    //Gera letras do alfabeto aleatórias
    public char rndChar () {
        
        int rnd = (int) (Math.random() * 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }
}
