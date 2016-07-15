/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.example.iimp_znxj_new2014.netty;

import android.util.Log;

import com.example.iimp_znxj_new2014.entity.UserInfo;
import com.google.gson.Gson;

import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.unix.NativeInetAddress;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws UnknownHostException {

        // list so the channel received the messages from others.
        System.err.println("处于活连接");
        //ctx.writeAndFlush("Welcome to  secure chat service!\n");
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // Send the received message to all channels but the current one.
        System.err.println("来自client的信息：" + msg);
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(msg, UserInfo.class);

        msg = "用户名:"+ userInfo.getUsername()+ "    密码:" + userInfo.getPassword() + "----" + ctx.channel().remoteAddress()+ "----" + msg;
        Log.d("tag",msg);
        System.out.println(ctx.channel().id());
        ctx.writeAndFlush("1\n");
        //ctx.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + '\n');

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
