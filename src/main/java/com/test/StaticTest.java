package com.test;

import com.google.common.net.InetAddresses;

import java.io.*;
import java.net.InetAddress;

/**
 * Created by bilibili on 2017/7/18.
 */
public class StaticTest {

    public static void main(String[] args) throws IOException {
        InetAddress.getByName("p.biligame.com").isReachable(3000);
    }

}
