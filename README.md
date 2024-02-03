# Chat Application with File Transfer

## Overview
This project provides a simple and intuitive Java-based chat application allowing users to communicate in real-time. Moreover, users can seamlessly share files with each other. The project consists of a server-side component, a client-side GUI, and a server thread to handle communication and file transfers.

## Features 🚀
- User-friendly Swing GUI for the client application.
- Real-time chat functionality.
- File transfer capability for both text files and images.

## Project Structure 🏗️
- **Server.java**
  - Main server class that listens for client connections on port 5000.
  - Creates a new `ServerThread` for each connected client.

- **ServerThread.java**
  - Handles communication with an individual client.
  - Manages chat messages and file transfers.

- **ClientGUI.java**
  - Handles the client-side GUI using Swing.
  - Manages the connection to the server and user interactions.

- **FileTransfer.java**
  - Serializable class for transferring files between the client and server.

## How to Run 🏃‍♂️
1. **Clone the Repository**
   - Clone the project repository using the following command:
     ```bash
     git clone https://github.com/LSBogdan/Chat-Application-with-File-Transfer.git
     ```

2. **Run the Server**
   - Open a command prompt or terminal.
   - Navigate to the project directory.
   - Compile and run the server:
     ```bash
     javac src/*.java
     java -cp src Server
     ```

3. **Run the Client GUI**
   - Open another command prompt or terminal.
   - Navigate to the project directory.
   - Compile and run the client GUI:
     ```bash
     java -cp src ClientGUI
     ```
   - Enter your name when prompted.
   - Start chatting and sending files!

## Usage Guidelines 📘
- **Sending Messages**
  - Type your message in the text field and press "Send" or hit Enter.
  - To exit the chat, type "exit" and press "Send" or hit Enter.

- **Sending Files**
  - To send a file, type "file" in the message field and press "Send" or hit Enter.
  - A file chooser will appear; select the file you want to send.
  - Currently, text files (.txt) and images are supported.


Happy chatting! 😊🚀
