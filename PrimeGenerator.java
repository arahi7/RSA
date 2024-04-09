import java.math.BigInteger;

class PrimeGenerator {
    private SimpleLCG lcg;

    public PrimeGenerator() {
        lcg = new SimpleLCG(BigInteger.valueOf(System.nanoTime()));
    }

    public BigInteger generatePrime(int bitLength) {
        while (true) {
            BigInteger candidate = new BigInteger(bitLength, 100, new java.util.Random(lcg.nextInt().intValue()));
            if (candidate.isProbablePrime(100)) {
                return candidate;
            }
        }
    }
}
