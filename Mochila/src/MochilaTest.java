import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MochilaTest {

    List<ItemMochila> itens = new ArrayList<>();

    @Before
    public void setUp() {
        itens.clear();
    }

    @Test
    public void testeMochila() {
        itens.add(new ItemMochila(4, 4));
        itens.add(new ItemMochila(100, 48));
        itens.add(new ItemMochila(3, 3));
        itens.add(new ItemMochila(1, 1));

        assertEquals(101, Mochila.solve(itens, 50));
    }

    @Test
    public void testePerformance() {
        for (int i = 0; i < 100; i++) {
            itens.add(new ItemMochila(1, 1));
        }

        assertEquals(99, Mochila.solve(itens, 99));
    }

}