# java_socket
## socke代码测试，[参考博客](https://www.cnblogs.com/yiwangzhibujian/p/7107785.html#q1)

package:socket1中是最简单的socket服务端和客户端测试，客户端发送信息，服务端接受并打印信息  
package:socket2中，客户端发送信息，服务端接受并打印信息，然后发送给客户端一个反馈信息  
package:socket3中，利用输出流，客户端分开发送多条消息给服务端  
package:socket4中，使用多线程的方式，模拟多个客户端请求一个服务端的情况，线程数多余50的时候，服务端会处理不过来，出现拒绝服务，客户端请求失败的情况  
package:socket5中，使用了线程池，匿名内部类，客户端和服务端都可以并行处理
