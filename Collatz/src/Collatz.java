import java.util.HashMap;
import java.util.Map;

public class Collatz {

    private static final int MAX_IN_MEMO = 100_000_000;
    private int[] memo;

    public Collatz() {
        this.memo = new int[MAX_IN_MEMO + 1];
    }

    public Solucao solve(int minX, int maxX) {
        long bestX = 0;
        int tamanhoSeqBest = 0;

        for (long x = minX; x <= maxX; x++) {
            int seqX = g(x);
            if (seqX > tamanhoSeqBest) {
                tamanhoSeqBest = seqX;
                bestX = x;
            }
        }
        return new Solucao(bestX, tamanhoSeqBest);
    }

    public long f(long x) {
        return (x & 1) == 0 ?
                x / 2 :
                3 * x + 1;
    }

    public int g(long x) {
        int resultFromMemo = x > MAX_IN_MEMO ? 0 :
                this.memo[(int) x];
        if (resultFromMemo != 0) {
            return resultFromMemo;
        }

        int result = x == 1 ? 1 : 1 + g(f(x));

        if (x <= MAX_IN_MEMO) {
            this.memo[(int) x] = result;
        }

        return result;
    }

    class Solucao {
        long x;
        int tamanhoSeq;

        Solucao(long x, int tamanhoSeq) {
            this.x = x;
            this.tamanhoSeq = tamanhoSeq;
        }
    }
}
