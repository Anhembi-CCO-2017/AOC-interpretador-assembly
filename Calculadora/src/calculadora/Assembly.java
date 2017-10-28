package calculadora;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                        
                 matriz_operacao[i][0]=matcher.group(1);
                 matriz_operacao[i][1]=matcher.group(2);
                 matriz_operacao[i][2]=matcher.group(3);
            } else
                return false;
        }

        return true;
    }

    public String convertEquacao() {
        int lines = this.matriz_operacao.length;

        for (int i = 0; i < lines; i++) {
            switch(this.matriz_operacao[i][0]) {
                case "MOVE": {
                    
                }

                case "ADD": {

                }

                case "SUB": {

                }

                case "MPY": {

                }

                case "DIV": {

                }
            }
        }
    }
}

