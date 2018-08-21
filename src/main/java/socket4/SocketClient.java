package socket4;

import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shun on 2018/8/17.
 */

public class SocketClient {
    public static void main(String[] args) throws Exception {
       new SocketClient().testClinet();
    }

    public void testClinet() throws InterruptedException {
        ClientRunnable runnable = new ClientRunnable();
        List<Thread> threads = new ArrayList<>();
        int n = 1;
        // 线程数多余50会有被服务端拒绝连接的可能
        while (n++ < 70) {
            Thread thread = new Thread(runnable, "thread-" + n);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("客户端socket连接失败的线程数：" + runnable.getConnectCount());
        System.out.println("------------------client主程序执行结束！-------------------");
    }

    private class ClientRunnable implements Runnable{
        private volatile int connectCount = 0;
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
                outputStream.close();
                socket.close();
                System.out.println("线程：" + Thread.currentThread().getName() + "连接成功，执行结束！断开socket连接。");
            } catch (Exception e) {
                connectCount++;
                System.out.println("client请求socket连接server失败,客户端线程：" + Thread.currentThread().getName());
            }
        }

        public int getConnectCount() {
            return connectCount;
        }
    }
}
