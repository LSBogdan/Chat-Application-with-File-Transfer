# 💬 **Chat Application with File Transfer** 📁

## Overview 🌐
Experience real-time communication with our Java-based chat application! Featuring a sleek Swing GUI, this app not only allows you to chat instantly but also lets you share files—be it text files or images—seamlessly. With a robust server-side architecture handling multiple clients, this project brings reliable chat functionality right to your fingertips. 🚀

## Features 🚀
- **Intuitive Swing GUI:**  
  Enjoy a clean and user-friendly interface that makes chatting a breeze. 🖥️
- **Real-Time Chat:**  
  Connect instantly and communicate live with friends and colleagues. ⏱️
- **Seamless File Transfer:**  
  Share text files and images effortlessly with your contacts. 📄🖼️
- **Robust Server Architecture:**  
  Utilizes a dedicated server thread for each client, ensuring smooth and efficient communication. 🔄

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


**Happy chatting! 😊🚀**
