import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheLRUTest {

    private static final int CAPACIDADE = 5;
    private CacheLRU<Integer, String> cache;

    @Before  // será rodado antes de CADA teste
    public void setUp() {
        cache = new CacheLRU<>(CAPACIDADE);
    }

    @Test
    public void testeMisses() {
        // cache vazio
        assertNull("O cache deve retornar null para chaves ausentes",
                cache.get(1234));

        cache.put(1235, "Documento");

        // repetindo o teste com cache não-vazio
        assertNull("O cache deve retornar null para chaves ausentes",
                cache.get(1234));
    }

    @Test
    public void testeHits() {
        // sanity check
        assertNull("O cache deve retornar null para chaves ausentes",
                cache.get(1234));

        String doc = "Documento Blah";
        cache.put(1234, doc);

        assertEquals("O cache deve localizar corretamente " +
                "um documento que lá resida",
                doc, cache.get(1234));
    }

    @Test
    public void testeSize() {
        // cache vazio
        assertEquals("Um cache vazio deve retornar zero como tamanho",
                0, cache.size());

        // coloque um documento
        cache.put(1234, "Doc");

        assertEquals("O cache deve retornar corretamente seu tamanho " +
                        "com apenas um documento presente",
                1, cache.size());

        // coloque um segundo documento
        cache.put(4444, "Outro Doc");

        assertEquals("O cache deve retornar corretamente seu tamanho " +
                        "com mais de um documento presente",
                2, cache.size());

        // atualize um documento presente
        cache.put(1234, "Doc Atualizado");

        assertEquals("O cache deve retornar corretamente seu tamanho " +
                        "após a atualização de documentos existentes",
                2, cache.size());
    }

    @Test
    public void testeOverflow() {
        encherCache();

        // sanity check
        assertEquals(CAPACIDADE, cache.size());

        // tente colocar outro documento
        cache.put(-1234, "Doc Novo");

        assertEquals("O tamanho do cache jamais pode exceder sua capacidade",
                CAPACIDADE, cache.size());
    }

    private void encherCache() {
        // encha o cache até a capacidade máxima
        for (int i = 1; i <= CAPACIDADE; i++) {
            cache.put(i, "Doc" + i);
        }
    }

    @Test
    public void testeLRU() {
        encherCache();

        // com o cache cheio, vamos dar um put
        cache.put(-1234, "Doc Novo");

        assertNull("O primeiro documento a ser escrito " +
                "deve ser removido",
                cache.get(1));

        /* agora vamos consultar alguém, para ter um hit
           e assim "esquentar" aquele documento */
        cache.get(2);

        cache.put(-4444, "Outro Novo Doc");

        // sanity check
        assertEquals("Doc2", cache.get(2));

        assertNull("O documento menos recentemente usado " +
                "deve ser removido para liberar espaço",
                cache.get(3));

        /* colocando outros documentos de forma a evictar
           todos os documentos originalmente populados
           exceto o que foi "aquecido" */
        for (int i = 1; i <= CAPACIDADE - 2; i++) {
            cache.put(-1000 + i, "NovoDoc" + i);
        }

        for (int i = 1; i <= CAPACIDADE; i++) {
            if (i == 2) {  // documento que "aquecemos"
                assertNotNull(cache.get(i));
            } else {
                assertNull(cache.get(i));
            }
        }
    }
}