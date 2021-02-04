package me.yuxiaoyao.soffice.server.util;

import me.yuxiaoyao.soffice.server.exception.OfficeStateException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author kerryzhang on 2021/2/1
 */

public class NetUtil {
    public static int getRandomPort() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            int localPort = serverSocket.getLocalPort();
            serverSocket.close();
            return localPort;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new OfficeStateException("local port get failed");
    }
}
