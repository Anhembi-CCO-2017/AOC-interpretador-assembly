package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

public class Assembly {
    public ArrayList< ArrayList > matriz_operacao = new ArrayList< ArrayList>();
    
    public Assembly() {
    }

    public boolean isAssembly(String valor) {
        Pattern regex1 = Pattern.compile("[$&:;=?!@#|]");
        Matcher matcher1 = regex1.matcher(valor);

        if(matcher1.find())
            return false;
        
        String linha[] = valor.split("\n");
            
        //Reconhecimento de pattern POR LINHA
        for (int i = 0; i < linha.length; i++) {
            Pattern regex = Pattern.compile("(MOVE|move|ADD|add|SUB|sub|MPY|mpy|DIV|div).*(\\w),(\\w)");
            Matcher matcher = regex.matcher(linha[i]);

            if(matcher.find()) {
                if(i==0)
                    if(!matcher.group(1).toUpperCase().equals("MOVE"))
                        return false;

                if(matcher.group(2).toUpperCase().equals(matcher.group(3).toUpperCase()))
                    return false;

                ArrayList< String > data = new ArrayList< String >();

                data.add(matcher.group(1).toUpperCase());
                data.add(matcher.group(2).toUpperCase());
                data.add(matcher.group(3).toUpperCase());

                matriz_operacao.add(data);
            } else
                return false;
        }

        return true;
    }

    public void convert() {
        int lines = matriz_operacao.size();

        //String to store data constructor
        ArrayList<String> group = new ArrayList();

        for (int i = 0; i < lines; i++) {
            ArrayList<String> data = matriz_operacao.get(i);
            
            if(!group.contains(data.get(1)))
                if(data.get(0).equals("MOVE"))
                    group.add(data.get(1));
                else {
                    System.out.println("BREAK CODE ON LINE: "+i);
                    return;
                }
        }

        //String to assing Register to Group
        ArrayList<String> groupData = new ArrayList<>(group);
        //This is same of groupData but NEVER IS CHANGE;
        ArrayList<String> groupStatic = new ArrayList<>(group);

        for (int i = 0; i < lines; i++) {
            ArrayList<String> data = matriz_operacao.get(i);
            int groupIndex = group.indexOf(data.get(1));
            int auxData = group.indexOf(data.get(2)); 
            boolean needMerge = false;
            boolean isMove = false;
            char op = ' ';

            if(auxData >= 0)
                needMerge = true;

            switch(data.get(0)) {
                case "MOVE": {
                    isMove = true;
                    if(!needMerge) // CASE IF DATA IS ANOTHER REGISTER (THIS IS CORRECT ON END OF OP)
                        groupData.set(groupIndex, data.get(2));
                    break;
                }

                case "ADD": {
                    op = '+';
                    break;
                }

                case "SUB": {
                    op = '-';
                    break;
                }

                case "DIV": {
                    op = '/';
                    break;
                }

                case "MPY": {
                    op = '*';
                    break;
                }                
            }

            if(!isMove)
                if(needMerge) {
                    groupData.set(groupIndex, concat(groupData.get(groupIndex), op+" "+groupData.get(auxData)));
                    groupData.remove(auxData);
                    group.remove(auxData);
                } else
                    groupData.set(groupIndex, concat(groupData.get(groupIndex), op+" "+data.get(2)));
            else if(needMerge) { // UTILIZADO NA VERIFICAÇÃO DE REGISTRADOR NA DATA DO MOVE
                group.remove(auxData);
            }
        }

        for (int i = groupData.size()-1; i >= 0 ; i--)
            if(groupStatic.indexOf(groupData.get(i)) >= 0)
                groupData.remove(i);
        
        int currentPos = 0;
        for(String s : groupData) {
            System.out.println(currentPos+": "+s);
            currentPos++;
        }
    }

    private String concat(String atual, String data) {
        return "("+atual+" "+data+")";
    }
}

