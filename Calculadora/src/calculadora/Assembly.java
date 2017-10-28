package calculadora;

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

        //Reconhecimento de pattern POR LINHA
        //Pattern pattern = Pattern.compile("(MOV|ADD|SUB|MPY|DIV) (\w),(\w)");
        //Matcher matcher = pattern.matcher(linha[i]);

        //if(matcher.find()) {
            //$1 -> matcher.group(1);
            //$2 -> matcher.group(2);
            //$3 -> matcher.group(3);
        //}

        return false;
    }    
}
