package com.geekbang.exercise.char08;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        // 1、获取本机的 InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        // 2、根据指定主机名，获取 InetAdress 对象
        InetAddress host1 = InetAddress.getByName("192.168.1.2");
        System.out.println("host1=" + host1);

        // 3、根据域名返回 InetAddress对象，比如 www.baidu.com
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2=" + host2);

        // 4、通过 InetAddress对象，获取对应的地址
        String hostAddress = host2.getHostAddress();
        System.out.println("host2 对应ip：" + hostAddress);

        // 5、通过 InetAddress 对象，获取对应的主机名/域名
        String hostName = host2.getHostName();
        System.out.println("host2 对应的主机名/域名=" + hostName);
    }
}
