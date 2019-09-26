import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PermCaoticas {

    public static void listarTodas(int n) {
       List<Integer> permutacaoVazia = new ArrayList<>();
       Set<Integer> numerosUsados = new HashSet<>();
       backtrack(permutacaoVazia, numerosUsados, n);
    }

    public static void backtrack(List<Integer> permutacaoCorrente,
                                 Set<Integer> numerosUsados,
                                 int n) {

        // testa se o estado corrente é final
        if (permutacaoCorrente.size() == n) {
            System.out.println(permutacaoCorrente);
            return;  // nada mais a ser feito, estamos numa folha da árvore
        }

        for (int numero = 1; numero <= n; numero++) {
            if (numerosUsados.contains(numero)) {
                continue;  // este numero já apareceu; não pode ser repetido
            }
            int proximaPosicao = permutacaoCorrente.size();
            if (numero == proximaPosicao + 1) {  // lista é 0-based
                continue;  // não posso colocar o número na próxima posição
                           // (seria a posição "certa" (não-caótica) dele)
            }

            // dá o passo, gerando uma nova configuração
            permutacaoCorrente.add(numero);
            numerosUsados.add(numero);

            // chama recursivamente
            backtrack(permutacaoCorrente, numerosUsados, n);

            // limpa a sujeira, i.e., desfaz a alteração que EU FIZ
            permutacaoCorrente.remove(permutacaoCorrente.size() - 1); // pop
            numerosUsados.remove(numero);
        }
    }
}
