package com.nhnacademy.server;

import static java.util.Objects.requireNonNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings({"squid:S106", "checkstyle:MissingJavadocType"})

/**
 * Server class.
 */
public class Server {
    private final ConcurrentHashMap<String, DataOutputStream> com = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    /**
     * ServerSocket start function.
     */
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            System.out.println(getTime() + " Start server " + serverSocket.getLocalSocketAddress());
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    ClientSession client = new ClientSession(socket);
                    client.start();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }
    }

    private String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
    }

    @java.lang.SuppressWarnings("squid:S3398")
    private void loggingCurrentClientCount() {
        String str = getTime() + " Currently " + com.size() + " clients are connected.";
        System.out.println(str);
    }

    private void sendToAll(String message) {
        for (DataOutputStream out : com.values()) {
            try {
                out.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    class ClientSession extends Thread {
        private final Socket socket;
        private final DataInputStream in;
        private final DataOutputStream out;
        private String id;

        ClientSession(Socket socket) throws IOException {
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        }

        private void leaveChat(ClientSession session) {
            com.remove(session.id);

            sendToAll("[System] " + session.id + "님이 나갔습니다.");
            String str = getTime() + " " + session.id
                + " is leaved: " + session.socket.getInetAddress();
            System.out.println(str);
            loggingCurrentClientCount();
        }

        @Override
        public void run() {
            initialize();
            connect();
        }

        private void initialize() {
            try {
                this.id = in.readUTF();

            } catch (IOException cause) {
                cause.printStackTrace();
            }
        }

        private void connect() {
            try {
                if (isConnect()) {
                    System.out.println(id);
                    gameResultSend(this.id);
                }

            } finally {
                disconnect();
            }
        }

        private void gameResultSend(String id) {
            Gameplay gameplay = new Gameplay(id);
            try {
                int monster = Integer.parseInt(in.readUTF());
                out.writeUTF(requireNonNull(gameplay.fight(monster)));
                int monster2 = Integer.parseInt(in.readUTF());
                out.writeUTF(requireNonNull(gameplay.fight2(monster2)));
                int monster3 = Integer.parseInt(in.readUTF());
                String tempDragon = gameplay.fight3(monster3);
                out.writeUTF(requireNonNull(tempDragon));
                sendToAll(tempDragon);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        private boolean isConnect() {
            return this.in != null;
        }

        private void disconnect() {
            leaveChat(this);
        }
    }


}





