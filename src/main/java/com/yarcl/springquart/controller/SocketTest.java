package com.yarcl.springquart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xiaozhi on 2019/12/5.
 */
@RestController
@RequestMapping("/socket")
public class SocketTest {

    @RequestMapping("/test.do")
    public void testSocket(@RequestParam String requestXml) throws Exception{
        //设置Url和端口， 127.0.0.1代表本机
        String url = "10.25.95.245";
        int port = 8888;
        //与服务端建立连接
        Socket socket = new Socket(url,port);
        System.out.println("已经连接到服务端");
        //建立连接后输出流到服务端
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(requestXml.getBytes("UTF-8"));

        InputStream in = socket.getInputStream();
        int len = 0;

        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while((len=in.read(bytes)) != -1) {
            sb.append(new String(bytes, 1, len, "UTF-8"));
            if(len < 1024) {
                break;
            }
        }
        System.out.println("Server says "+ sb.toString());
        outputStream.close();
        in.close();
        socket.close();
    }

}
