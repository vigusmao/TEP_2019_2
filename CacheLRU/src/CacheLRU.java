import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheLRU<K, V> {

    private LinkedHashMap<K, V> mapa;
    private int capacidade;

    public CacheLRU(int capacidade) {
        this.capacidade = capacidade;
        mapa = new LinkedHashMap<>(capacidade, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return this.size() > CacheLRU.this.capacidade;
            }
        };
    }

    /**
     * Retorna o documento desejado, se existir.
     *
     * @param chave A chave de busca, identificando o documento.
     * @return O documento desejado; ou null, caso não exista.
     */
    public V get(K chave) {
        return mapa.get(chave);
    }

    /**
     * Adiciona um documento ao cache.
     * Se o cache estiver cheio, removerá do cache o documento
     * menos recentemente utilizado (política LRU) para dar lugar
     * ao novo documento.
     *
     * @param chave A chave do novo documento.
     * @param documento O documento em si.
     */
    public void put(K chave, V documento) {
        mapa.put(chave, documento);
    }

    /**
     * Retorna o número de documentos correntemente no cache.
     * @return Um inteiro indicando o tamanho; 0, se cache vazio.
     */
    public int size() {
        return mapa.size();
    }
}


