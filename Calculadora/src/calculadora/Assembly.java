package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

/* v1: /(MOV|ADD|SUB|MPY|DIV) (\w),(\w)/g  $1 $2 != $3
 * REGEX DE VALIDAÇÃO
 * Primeiro identificar se existe caracteres especiais. [$&+:;=?!@#|]
 *  Se houver
 *      return false;
 * Splitar linhas pelo \n usando SPLIT -> para vetor de (String linhas[]) com (String.split("\n"))
 * Le Linha por linha com a pattern 'v1'
 *  Se pattern reconhecida e se $2 != $3:
        na primeira linha
            $1 -> TEM QUE SER MOV;
                se for
                    salvar $1,$2,$3 na matriz [0][OP:0:$1, VARIAVEL1:1:$2, VARIAVEL2:2:$3]
                se nao
                    return false;
        se nao for primeira linha
            salvar $1,$2,$3 na matriz [linha][OP:0:$1, VARIAVEL1:1:$2, VARIAVEL2:2:$3]
        No final se tudo verificado e salvo
            return true;
    Se não
        return false;
 */
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
            Pattern regex = Pattern.compile("(MOVE|move|ADD|add|SUB|sub|MPY|mpy|DIV|div) (\\w),(\\w)");
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
        /*
         * Checar os Registrador
         */
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
        }

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

