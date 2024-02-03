import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class ClientGUI extends JFrame {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private JTextField messageField;
    private JTextArea chatArea;
    private String clientName;

    public ClientGUI() {
        initializeUI();
        connectToServer();
    }

    private void initializeUI() {

        setTitle("Chat Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        messageField = new JTextField();
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void connectToServer() {

        try {
            socket = new Socket("localhost", 5000);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            clientName = JOptionPane.showInputDialog("Enter your name:");
            output.println(clientName);

            Thread clientThread = new Thread(new ClientRunnable());
            clientThread.start();
        } catch (Exception e) {
            System.out.println("Exception occurred in client: " + e.getMessage());
        }
    }

    private void sendMessage() {

        String message = messageField.getText();
        if (message.equalsIgnoreCase("exit")) {
            closeConnection();
        } else if (message.equalsIgnoreCase("file")) {
            sendFile();
        } else {
            output.println("(" + clientName + ") " + message);
            messageField.setText("");
        }
    }

    private void sendFile() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                if (selectedFile.getName().toLowerCase().endsWith(".txt")) {
                    
                    BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder fileContent = new StringBuilder();
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        fileContent.append(line).append("\n");
                    }
                    output.println("file");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(fileContent.toString());
                } else {
                    
                    byte[] imageData = Files.readAllBytes(selectedFile.toPath());
                    String fileName = selectedFile.getName();
                    output.println("file");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(new FileTransfer(fileName, imageData));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeConnection() {

        try {
            socket.close();
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error while closing connection: " + e.getMessage());
        }
    }

    private class ClientRunnable implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    String serverMessage = input.readLine();
                    if (serverMessage == null) {
                        break;
                    }
                    chatArea.append(serverMessage + "\n");
                }
            } catch (Exception e) {
                System.out.println("Error occurred in client: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }
}
