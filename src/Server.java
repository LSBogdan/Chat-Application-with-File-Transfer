import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {
        
        System.out.println("Server started. Waiting for connections...");

        ArrayList<ServerThread> threadList = new ArrayList<>();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();
            }
        } catch (Exception e) {
            System.out.println("Error occurred in server: " + e.getMessage());
        }
    }
}
