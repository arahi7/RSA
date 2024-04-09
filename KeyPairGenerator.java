import java.math.BigInteger;

public class KeyPairGenerator {
    private BigInteger n, d, e;

    public KeyPairGenerator(int bitLength) {
        PrimeGenerator primeGen = new PrimeGenerator();

        BigInteger p = primeGen.generatePrime(bitLength / 2);
        BigInteger q = primeGen.generatePrime(bitLength / 2);
        n = p.multiply(q);

        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        e = new BigInteger("65537");

        while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }

    public BigInteger getN() { return n; }
    public BigInteger getE() { return e; }
    public BigInteger getD() { return d; }
}
