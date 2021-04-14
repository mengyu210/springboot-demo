package com.gyp.springboot.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author     ：guoyapeng
 * @date       ：Created in 2020/8/23 15:48
 * @description：SFP文件上传类
 * @modified By：`
 * @version: 1.0
 */
public class FtpUtils implements Closeable {

    private final static Logger log = LoggerFactory.getLogger(FtpUtils.class);

    private Session session;
    private ChannelSftp channelSftp;


    /**
     * 超时时间
     */
    private final static int    TIMEOUT     = 60000;
    private final static int    BYTE_LENGTH = 1024;


    public static void main(String[] args) {
        FtpUtils ftpUtils = new FtpUtils("root", "gyp210125.", "49.235.226.115:22");
        boolean connection = ftpUtils.connection();
        System.out.println(connection);
        Vector ls = ftpUtils.ls("/");
        ftpUtils.createDir("/ccicall/");
        System.out.println(ls);
    }


    /**
     * 构造器
     * @param userName  用户名
     * @param password  密码
     * @param host   IP:端口
     */
    public FtpUtils(String userName, String password, String host){
        try{
            String[] arr = host.split(":");
            String ip = arr[0];
            int port = arr.length > 1 ? Integer.parseInt(arr[1]) : 22;
            JSch jsch = new JSch();
            session = jsch.getSession(userName, ip, port);
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
               channelSftp = (ChannelSftp)session.openChannel("sftp");
               channelSftp.connect();
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
     * 列出指定目录下文件列表
     * @param remotePath  文件路径
     * @return Vector
     */
    public Vector ls(String remotePath){
        Vector vector = null;
        if(isConnected()){
            try {
                vector = channelSftp.ls(remotePath);
            } catch (SftpException e) {
                log.error("ls remotePath:{} , error:{}",remotePath,e);
            }
        }
        return vector;
    }

    /**
     * 从sftp服务器删除指定文件
     * @param remoteFile  文件路径
     * @return boolean
     */
    public boolean delFile(String remoteFile) {
        if (isConnected()) {
            try {
                channelSftp.rm(remoteFile);
                return true;
            } catch (SftpException e) {
                log.error("delFile remoteFile:{} , error:{}", remoteFile, e);
            }
        }
        return false;
    }

    /**
     * 从sftp服务器下载指定文件到本地指定目录
     *
     * @param remoteFile 文件的绝对路径+fileName
     * @param localPath 本地临时文件路径
     * @return  布尔
     */
    public boolean get(String remoteFile, String localPath) {
        if (isConnected()) {
            try {
                channelSftp.get(remoteFile, localPath);
                return true;
            } catch (SftpException e) {
                log.error("get remoteFile:{},localPath:{}, error:{}", remoteFile, localPath, e);
            }
        }
        return false;
    }

    /**
     * 读取sftp上指定文件数据
     *
     * @param remoteFile  文件路径
     * @return  数组
     */
    public byte[] getFileByte(String remoteFile) {
        byte[] fileData;
        int rc;
        try (InputStream inputStream = channelSftp.get(remoteFile)) {
            byte[] ss = new byte[BYTE_LENGTH];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            while ((rc = inputStream.read(ss, 0, BYTE_LENGTH)) > 0) {
                byteArrayOutputStream.write(ss, 0, rc);
            }
            fileData = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("getFileData remoteFile:{},error:{}", remoteFile, e);
            fileData = null;
        }
        return fileData;
    }


    /**
     * 读取sftp上指定（文本）文件数据,并按行返回数据集合
     *
     * @param remoteFile  文件路径
     * @param charsetName 字符集
     * @return
     */
    public List<String> getFileLines(String remoteFile, String charsetName) {
        List<String> fileData;
        try (InputStream inputStream = channelSftp.get(remoteFile);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream,charsetName);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String str;
            fileData = new ArrayList<>();
            while((str = bufferedReader.readLine()) != null){
                fileData.add(str);
            }
        } catch (Exception e) {
            log.error("getFileData remoteFile:{},error:{}", remoteFile, e);
            fileData = null;
        }
        return fileData;
    }

    /**
     * 上传本地文件到sftp服务器指定目录
     *
     * @param localFile
     * @param remoteFile
     * @return
     */
    public boolean put(String localFile, String remoteFile) {
        if (isConnected()) {
            try {
                channelSftp.put(localFile, remoteFile);
                return true;
            } catch (SftpException e) {
                log.error("put localPath:{}, remoteFile:{},error:{}", localFile, remoteFile, e);
                return false;
            }
        }
        return false;
    }

    /**
     * 列出指定目录下文件列表
     * @param remotePath   文件路径
     * @param filenamePattern  文件名字正则表达式
     * @return
     * 排除./和../等目录和链接,并且排除文件名格式不符合的文件
     */
    public List<ChannelSftp.LsEntry> lsFiles(String remotePath, Pattern filenamePattern){
        List<ChannelSftp.LsEntry> lsEntryList = null;
        if(isConnected()){
            try {
                Vector<ChannelSftp.LsEntry> vector = channelSftp.ls(remotePath);
                if(vector != null) {
                    lsEntryList = vector.stream().filter(x -> {
                        boolean match = true;
                        if(filenamePattern != null){
                            Matcher mtc = filenamePattern.matcher(x.getFilename());
                            match = mtc.find();
                        }
                        return match && !x.getAttrs().isDir() && !x.getAttrs().isLink();
                    }).collect(Collectors.toList());
                }
            } catch (SftpException e) {
                lsEntryList = null;
                log.error("lsFiles remotePath:{} , error:{}",remotePath,e);
            }
        }
        return lsEntryList;
    }


    /**
     * 创建一个文件目录
     * @param createpath
     */
    public void createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                channelSftp.cd(createpath);
            }
            String[] pathArry = createpath.split("/");
            StringBuilder filePath = new StringBuilder("/");
            for (String path : pathArry) {
                if ("".equals(path)) {
                    continue;
                }
                filePath.append(path).append("/");
                if (isDirExist(filePath.toString())) {
                    channelSftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    channelSftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    channelSftp.cd(filePath.toString());
                }
            }
            channelSftp.cd(createpath);
        } catch (SftpException e) {
            log.error("lsFiles createpath:{} , error:{}",createpath,e);
        }
    }

    /**
     * 判断目录是否存在
     * @param directory  文件目录
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpAttrs = channelSftp.lstat(directory);
            isDirExistFlag = true;
            return sftpAttrs.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }




    @Override
    public void close() {
        if (channelSftp != null && channelSftp.isConnected()) {
            channelSftp.quit();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        log.info("session and channel is closed");
    }
}
