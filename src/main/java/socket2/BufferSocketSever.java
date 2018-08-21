package socket2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by shun on 2018/8/17.
 */

public class BufferSocketSever {
    public static void main(String[] args) throws IOException {
        int port = 55533;

        ServerSocket socketServer = new ServerSocket(port);
        System.out.println("server waiting for client!");
        Socket socket = socketServer.accept();

        InputStream inputStream = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = br.readLine()) != null && !"end".equals(line)) {
            sb.append(line);
        }
        System.out.println(line);
        System.out.println("The message form client:" + sb);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("Got it, client!".getBytes("UTF-8"));

        inputStream.close();
        outputStream.close();
        socket.close();
        socketServer.close();

    }
}
