import javax.management.InstanceAlreadyExistsException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Mochila {

    private static Map<Instancia, Integer> memo = new HashMap<>();

    /**
     * Resolve o problema da mochila 0-1.
     * @return O valor máximo possível de ser colocado na mochila.
     *
     * PS.: This method is NOT thread-safe!!!!!
     */
    public static int solve(List<ItemMochila> itens,
                            int capacidade) {

        memo.clear();
        return resolverSubProblema(itens, itens.size(), capacidade);
    }

    /**
     *
     * @param itens O array de itens
     * @param quantItemsDisponiveis o tamanho do prefixo da lista de itens
     *                              que pode ser considerada nesse subproblema
     * @param capacidade A capacidade da (sub-)mochila
     * @return O valor máximo possível para essa (sub-)instância
     */
    private static int resolverSubProblema(
            List<ItemMochila> itens,
            int quantItemsDisponiveis,
            int capacidade) {

        if (capacidade == 0 || quantItemsDisponiveis == 0) {
            return 0;
        }

        Instancia instancia = new Instancia(quantItemsDisponiveis, capacidade);
        Integer resultFromMemo = memo.get(instancia);
        if (resultFromMemo != null) {
            return resultFromMemo;
        }

        ItemMochila ultimoItemDisponivel = itens.get(quantItemsDisponiveis - 1);

        int solucaoLevandoOUltimoItem = ultimoItemDisponivel.getPeso() > capacidade ? 0 :
                ultimoItemDisponivel.getValor() + resolverSubProblema(
                    itens,
                    quantItemsDisponiveis - 1,
                    capacidade - ultimoItemDisponivel.getPeso());

        int solucaoNaoLevandoOUltimoItem = resolverSubProblema(
                itens,
                quantItemsDisponiveis - 1,
                capacidade);

        int result = Math.max(
                solucaoLevandoOUltimoItem, solucaoNaoLevandoOUltimoItem);

        memo.put(instancia, result);

        return result;
    }

    private static class Instancia {
        int quantItensDisponiveis;
        int capacidade;

        public Instancia(int quantItensDisponiveis, int capacidade) {
            this.quantItensDisponiveis = quantItensDisponiveis;
            this.capacidade = capacidade;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Instancia instancia = (Instancia) o;
            return quantItensDisponiveis == instancia.quantItensDisponiveis &&
                    capacidade == instancia.capacidade;
        }

        @Override
        public int hashCode() {
            return Objects.hash(quantItensDisponiveis, capacidade);
        }
    }
}
