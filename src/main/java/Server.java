import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stanislav Rakitov in 2022
 */
public class Server {

  private final int port;

  public Server() {
    // Default port
    this.port = 8080;
  }

  public Server(int port) {
    this.port = port;
  }

  public static void main(String[] args) {
    Server server = new Server();
    server.run();
  }

  @SuppressWarnings("InfiniteLoopStatement")
  private void run() {

    while (true) {
      try (ServerSocket serverSocket = new ServerSocket(port);
          Socket clientSocket = serverSocket.accept();
          PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
          BufferedReader in =
              new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

        System.out.println("New connection accepted");
        final String name = in.readLine();
        out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
