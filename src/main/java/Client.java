import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Stanislav Rakitov in 2022
 */
public class Client {

  private final String host;
  private final int port;

  public Client(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public Client() {
    this.host = "localhost";
    this.port = 8080;
  }

  public static void main(String[] args) {
    Client client = new Client();
    client.run();
  }

  private void run() {

    try (Socket socket = new Socket(host, port);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      writer.println("I'm the client!");
      String response = reader.readLine();
      System.out.println(response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
