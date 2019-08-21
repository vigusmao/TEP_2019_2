import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollatzTest {

    private Collatz collatz;

    @Before
    public void setUp() {
        collatz = new Collatz();
    }

    @Test
    public void testeBobo() {
        Collatz.Solucao s = collatz.solve(4, 8);
        assertEquals(7, s.x);
        assertEquals(17, s.tamanhoSeq);
    }

    @Test
    public void testePerformance() {
        long start = System.currentTimeMillis();
        Collatz.Solucao s = collatz.solve(10000000, 40000000);
        long duracao = System.currentTimeMillis() - start;

        System.out.println(s.x);
        System.out.println(s.tamanhoSeq);
        System.out.println(String.format(
                "duracao = %.3f segundos", (duracao / 1000f)) );
    }
}