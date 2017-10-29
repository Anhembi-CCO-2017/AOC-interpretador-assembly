package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

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
    public String[][] matriz_operacao;
    
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
                    if(matcher.group(1).toUpperCase() != "MOVE")
                        return false;                           

                if(matcher.group(2).toUpperCase().equals(matcher.group(3).toUpperCase()))
                    return false;
                 
                matriz_operacao[i][0]=matcher.group(1).toUpperCase();
                matriz_operacao[i][1]=matcher.group(2).toUpperCase();
                matriz_operacao[i][2]=matcher.group(3).toUpperCase();

            } else
                return false;
        }

        return true;
<<<<<<< HEAD
    }

    public String convert() {
        /*
         * Checar os Registrador
         */
        int lines = this.matriz_operacao.length;

        //String to store data constructor
        ArrayList<String> group = new ArrayList();
        //String to assing Register to Group
        ArrayList<String> data = new ArrayList();

        //Interger to save index of Register on groups String Data
        int asssing_index[] = null;

        for (int i = 0; i < lines; i++) {
            if(!group.contains(matriz_operacao[i][1]))
                if(matriz_operacao[i][0].equals("MOVE"))
                    group.add(matriz_operacao[i][1]);
                else
                    return "BREAK CODE ON LINE: "+i;
        }

        for (int i = 0; i < lines; i++) {
            int groupIndex = group.indexOf(matriz_operacao[i][1]);

        }

        return "";
    }
=======
    }    
>>>>>>> d82b16e42ba0c524be839d8259fb321a1f48c15d
}

