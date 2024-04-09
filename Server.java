import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private BigInteger n, d;

    public Server(BigInteger n, BigInteger d) {
        this.n = n;
        this.d = d;
    }

    public String decrypt(String encryptedMessage) {
        return new String(new BigInteger(encryptedMessage).modPow(d, n).toByteArray());
    }

    public void start(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String encryptedMessage = in.readLine();
                System.out.println("Received encrypted message: " + encryptedMessage);
                String decryptedMessage = decrypt(encryptedMessage);
                System.out.println("Decrypted message: " + decryptedMessage);

                out.println(decryptedMessage);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        KeyPairGenerator keyGen = new KeyPairGenerator(1024);
        new Server(keyGen.getN(), keyGen.getD()).start(6789);
    }
}
