import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PermCaoticasTest {

    @Test
    public void testeBasico() {
//        List<List<Integer>> permutacoes = PermCaoticas.listarTodas(3);
//        assertEquals(2, permutacoes.size());
//        for (List<Integer> perm : permutacoes) {
//            System.out.println(perm);
//        }
        PermCaoticas.listarTodas(4);
    }
}
