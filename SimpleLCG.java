import java.math.BigInteger;

public class SimpleLCG {
    private BigInteger state, a, c, m;

    public SimpleLCG(BigInteger seed) {
        m = new BigInteger("2").pow(128);
        a = new BigInteger("25214903917");
        c = new BigInteger("11");
        this.state = seed;
    }

    public BigInteger nextInt() {
        state = state.multiply(a).add(c).mod(m);
        return state;
    }
}
