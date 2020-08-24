package com.gyp.springboot.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.Properties;

/**
 * @author     ：_my
 * @date       ：Created in 2020/6/30 15:48
 * @description：SFP文件上传类
 * @modified By：`
 * @version: 1.0
 */
public class FtpUtils {

    private final static Logger log = LoggerFactory.getLogger(FtpUtils.class);

    private Session session;
    private ChannelSftp channelSftp;

    /**
     * 超时时间
     */
    private final static int    TIMEOUT     = 60000;
    private final static int    BYTE_LENGTH = 1024;


    public static void main(String[] args) {
        FtpUtils ftpUtils = new FtpUtils("root", "gyp210125", "49.235.226.115:22");
        boolean connected = ftpUtils.isConnected();
        System.out.println(connected);
    }


    /**
     * 构造器
     * @param userName
     * @param password
     * @param host
     */
    public FtpUtils(String userName, String password, String host){
        try{
            String[] arr = host.split(":");
            String ip = arr[0];
            int port = arr.length > 1 ? Integer.parseInt(arr[1]) : 22;
            JSch jsch = new JSch();
            Session session = jsch.getSession(userName, ip, port);
            if(null != password){
                session.setPassword(password);
            }
            session.setTimeout(TIMEOUT);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);

        }catch (Exception e){
            log.error("init host:{},userName:{},password:{} error:{}",host,userName,password, e);
        }
    }

    public boolean connection(){
        try{
           if(!isConnected()){
               session.connect();
               ChannelSftp sftp = (ChannelSftp)session.openChannel("sftp");
               sftp.connect();
               log.info("connected to host:{},userName:{}", session.getHost(), session.getUserName());
           }
           return true;
        }catch (JSchException e){
            log.error("connection to sftp host:{} error:{}", session.getHost(), e);
            return false;
        }
    }

    public boolean isConnected(){
        if(session.isConnected() && channelSftp.isConnected()){
            return true;
        }
        log.info("sftp server:{} is not connected",session.getHost());
        return false;
    }


    /**
     * 利用JSch包实现SFTP上传文件
     * @param bytes 文件字节流
     * @param fileName 文件名
     * @throws Exception
     */
    public static void sshSftp(byte[] bytes,String fileName) throws Exception{
        //指定的服务器地址
        String ip = "服务器ip地址";
        //用户名
        String user = "用户名";
        //密码
        String password = "密码";
        //服务器端口 默认22
        int port = 22;
        //上传文件到指定服务器的指定目录 自行修改
        String path = "/root";
        Session session = null;
        Channel channel = null;
        JSch jsch = new JSch();

        if(port <=0){
            //连接服务器，采用默认端口
            session = jsch.getSession(user, ip);
        }else{
            //采用指定的端口连接服务器
            session = jsch.getSession(user, ip ,port);
        }
        //如果服务器连接不上，则抛出异常
        if (session == null) {
            throw new Exception("session is null");
        }

        //设置登陆主机的密码
        session.setPassword(password);
        //设置第一次登陆的时候提示，可选值：(ask | yes | no)
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆超时时间
        session.connect(30000);

        OutputStream outstream = null;
        try {
            //创建sftp通信通道
            channel = (Channel) session.openChannel("sftp");
            channel.connect(1000);
            ChannelSftp sftp = (ChannelSftp) channel;
            //进入服务器指定的文件夹
            sftp.cd(path);
            //以下代码实现从本地上传一个文件到服务器，如果要实现下载，对换以下流就可以了
            outstream = sftp.put(fileName);
            outstream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关流操作
            if(outstream != null){
                outstream.flush();
                outstream.close();
            }
            if(session != null){
                session.disconnect();
            }
            if(channel != null){
                channel.disconnect();
            }
        }
    }
}
