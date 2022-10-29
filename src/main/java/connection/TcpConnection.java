package connection;

import java.io.IOException;
import java.net.Socket;

public interface TcpConnection<T> {
    void initSocket(Socket socket) throws IOException;

    void send(T message);

    T receive() throws IOException;

    int getPort();

    String getIp();

    boolean isConnected();

    void close();
}