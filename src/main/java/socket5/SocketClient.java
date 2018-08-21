package socket5;

import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by shun on 2018/8/17.
 */

public class SocketClient {
    public static void main(String[] args) throws Exception {
       SocketClient socketClient = new SocketClient();
//       socketClient.testClinet();
       ExecutorService executorService = Executors.newFixedThreadPool(100);
       int n = 100;
       while (n-- > 0) {
           executorService.submit(socketClient.new ClientRunnable());
       }
        System.out.println("client主线程执行完毕！");
    }

    private class ClientRunnable implements Runnable{
//        private volatile int connectCount = 0;
        @Override
        public void run() {
            String host = "127.0.0.1";
            int port = 55533;
            try {
                Socket socket = new Socket(host, port);
                OutputStream outputStream = socket.getOutputStream();
                String message = "你好，server,来自线程：" + Thread.currentThread().getName();
                byte[] bytes = message.getBytes("UTF-8");
                outputStream.write(bytes);
                outputStream.flush();
                System.out.println("---------------------" + Thread.currentThread().getName() + "睡眠开始------------");
                Thread.sleep(10000L);
                System.out.println("---------------------" + Thread.currentThread().getName() + "睡眠结束------------------");
                outputStream.close();
                socket.close();
                System.out.println("线程：" + Thread.currentThread().getName() + "连接成功，执行结束！断开socket连接。");
            } catch (Exception e) {
//                connectCount++;
                System.out.println("client请求socket连接server失败,客户端线程：" + Thread.currentThread().getName());
            }
        }

//        public int getConnectCount() {
//            return connectCount;
//        }
    }
}
