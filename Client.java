import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class Client {
    private BigInteger n, e;

    public Client(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }

    public String encrypt(String message) {
        return new BigInteger(message.getBytes()).modPow(e, n).toString();
    }

    public void send(String message, String host, int port) throws IOException {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(encrypt(message));
            System.out.println("Encrypted message sent. Waiting for response...");

            String response = in.readLine();
            System.out.println("Server response: " + response);
        }
    }

    public static void main(String[] args) throws IOException {
        KeyPairGenerator keyGen = new KeyPairGenerator(1024);
        new Client(keyGen.getN(), keyGen.getE()).send("Hello, RSA!", "localhost", 6789);
    }
}
