import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

    private Socket socket;
    private ArrayList<ServerThread> threadList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threadList) {
        this.socket = socket;
        this.threadList = threadList;
    }

    @Override
    public void run() {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            String clientName = input.readLine();
            System.out.println("Client '" + clientName + "' connected.");

            while (true) {
                String clientMessage = input.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client '" + clientName + "' disconnected.");
                    break;
                } else if (clientMessage.equalsIgnoreCase("file")) {
                    receiveFile(clientName);
                } else {
                    System.out.println("Message from '" + clientName + "': " + clientMessage);
                    broadcastMessage("(" + clientName + ") " + clientMessage);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in server thread: " + e.getMessage());
        } finally {
            threadList.remove(this);
        }
    }

    private void receiveFile(String clientName) throws IOException, ClassNotFoundException {
        
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Object receivedObject = objectInputStream.readObject();

        if (receivedObject instanceof String) {
            
            String fileContent = (String) receivedObject;
            System.out.println("Received text file from '" + clientName + "': " + fileContent);
            broadcastMessage("(" + clientName + ") sent a text file: " + fileContent);
        } else if (receivedObject instanceof FileTransfer) {
            
            FileTransfer fileTransfer = (FileTransfer) receivedObject;
            String fileName = fileTransfer.getFileName();
            byte[] imageData = fileTransfer.getFileData();
            
            System.out.println("Received image from '" + clientName + "': " + fileName);
            broadcastMessage("(" + clientName + ") sent an image: " + fileName);
        }
    }

    private void broadcastMessage(String message) {
        for (ServerThread thread : threadList) {
            if (thread != this) {
                thread.output.println(message);
            }
        }
    }
}
