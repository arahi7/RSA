public class RSADemo {
    public static void main(String[] args) {
        // Start the server in its own thread
        new Thread(() -> {
            try {
                KeyPairGenerator keyGen = new KeyPairGenerator(1024);
                Server server = new Server(keyGen.getN(), keyGen.getD());
                server.start(6789); // Use the same port as in the client
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Give the server a moment to start up
        try {
            Thread.sleep(1000); // Wait for 1 second before starting the client
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Start the client in the main thread
        try {
            KeyPairGenerator keyGen = new KeyPairGenerator(1024);
            Client client = new Client(keyGen.getN(), keyGen.getE());
            client.send("Hello, RSA!", "localhost", 6789);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
