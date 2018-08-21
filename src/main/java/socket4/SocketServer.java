package socket4;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by shun on 2018/8/17.
 * 模拟服务端压力过大，无法及时响应客户端请求，导致客户端连接失败
 */

public class SocketServer {
    public static void main(String args[]) throws IOException, InterruptedException {
        // 监听指定的端口
        int port = 55533;
        ServerSocket server = new ServerSocket(port);
        // server将一直等待连接的到来
        System.out.println("server将一直等待连接的到来");

        int countConnect = 0;
        while(true){
            System.out.println("等待连接");
            Socket socket = server.accept();
            // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }
            Thread.sleep(100L);
            System.out.println("get message from client: " + sb);
            inputStream.close();
            socket.close();
            countConnect++;
            System.out.println("连接关闭,已处理连接数：" + countConnect);
        }

    }
}