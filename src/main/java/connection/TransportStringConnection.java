package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TransportStringConnection implements TcpConnection<String> {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public TransportStringConnection(Socket socket) throws IOException {
        initSocket(socket);
    }

    public void initSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
    }

    public void send(String message) {
        if (this.out != null) {
            this.out.println(message);
            this.out.flush();
        }
    }

    public String receive() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            stringBuilder.append(in.readLine());
            if (in.ready()) {
                stringBuilder.append("\n");
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    public boolean isConnected() {
        return this.socket.isConnected();
    }

    public int getPort() {
        return this.socket.getPort();
    }

    public String getIp() {
        return this.socket.getInetAddress().getHostAddress();
    }

    public void close() {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
            if (this.in != null) {
                this.in.close();
            }
            if (this.out != null) {
                this.out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}