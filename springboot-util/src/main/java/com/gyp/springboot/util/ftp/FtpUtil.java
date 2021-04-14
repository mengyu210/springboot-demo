package com.gyp.springboot.util.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: guoyapeng
 * @TODO: 说明
 * @Date: Created in 18:03 - 2020/8/26
 */
public class FtpUtil {

    private final static Logger logger = LoggerFactory.getLogger(FtpUtil.class);


    private final String USERNAME="wangxiao";
    private final String PASSWORD="ftp@wangxiao";
    private final int  PORT= 21;
    private final String HOST="10.1.176.101";
    private final String BREAK = "/";

    public FTPClient ftp;



    /**
     * 获取FTPClient对象
     * @param ftpHost 服务器IP
     * @param ftpPort 服务器端口号
     * @param ftpUserName 用户名
     * @param ftpPassword 密码
     * @return FTPClient
     */
    public FTPClient getFtpClient(String ftpHost, int ftpPort,
                                  String ftpUserName, String ftpPassword) {
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(ftpHost, ftpPort);
            // 设置用户名和密码
            ftp.login(ftpUserName, ftpPassword);
            // 设置连接超时时间,5000毫秒
            ftp.setConnectTimeout(50000);
            // 设置中文编码集，防止中文乱码
            ftp.setControlEncoding("UTF-8");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftp.disconnect();
            } else {
                logger.info("FTP连接成功");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置");
        }
        return ftp;
    }


    /**
     * 改变工作目录
     * @param directory 目录
     * @return boolean
     */
    public boolean changeWorkingDirectoryL(String directory) {
        boolean flag = true;
        try {
            flag = ftp.changeWorkingDirectory(directory);
            if (flag) {
                logger.debug("进入文件夹" + directory + " 成功！");

            } else {
                logger.debug("进入文件夹" + directory + " 失败！");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }


    /**
     * 创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
     * @param remote  多级文件目录
     * @return boolean
     */
    public boolean createDirecroty(String remote) {
        boolean success = false;
        String directory = remote + BREAK;
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!BREAK.equalsIgnoreCase(directory) && !changeWorkingDirectoryL(directory)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith(BREAK)) {
                start = 1;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {
                String subDirectory = new String(remote.substring(start, end).getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
                path = path + BREAK + subDirectory;
                if (!existFile(path)) {
                    if (mkdirFolder(subDirectory)) {
                        changeWorkingDirectoryL(subDirectory);
                    } else {
                        logger.debug("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectoryL(subDirectory);
                    }
                } else {
                    changeWorkingDirectoryL(subDirectory);
                }
                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    success =true;
                    break;
                }
            }
        }
        return success;
    }

    /**
     * 判断ftp服务器文件是否存在
     * @param  path  文件路径
     * @return boolean
     */
    public boolean existFile(String path) {
        boolean flag = false;
        try{
            FTPFile[] ftpFileArr = ftp.listFiles(path);
            if (ftpFileArr.length > 0) {
                flag = true;
            }
        }catch (IOException e){
           e.printStackTrace();
           logger.error("找不到文件");
        }
        return flag;
    }




    /**
     * 关闭FTP方法
     * @return boolean
     */
    public boolean closeFtp(){
        try {
            ftp.logout();
        } catch (Exception e) {
            logger.error("FTP关闭失败");
        }finally{
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTP关闭失败");
                }
            }
        }
        return false;
    }



    /**
     * 下载FTP下指定文件
     * @param filePath FTP文件路径
     * @param fileName 文件名
     * @param downPath 下载保存的目录
     * @return boolean
     */
    public boolean downLoadFtp( String filePath, String fileName,
                               String downPath) {
        // 默认失败
        boolean flag = false;
        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(filePath);
            // 获取目录下文件集合
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {
                    File downFile = new File(downPath + File.separator
                            + file.getName());
                    OutputStream out = new FileOutputStream(downFile);
                    // 绑定输出流下载文件,需要设置编码集，不然可能出现文件为空的情况
                    flag = ftp.retrieveFile(new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), out);
                    // 下载成功删除文件,看项目需求
                    // ftp.deleteFile(new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
                    out.flush();
                    out.close();
                    if(flag){
                        logger.info("下载成功");
                    }else{
                        logger.error("下载失败");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("下载失败");
        }
        return flag;
    }


    /**
     * FTP文件上传工具类
     * @param filePath 本地文件路径
     * @param ftpPath  服务器文件路径
     * @return boolean
     */
    public boolean uploadFile(String filePath,String ftpPath){
        boolean flag = false;
        InputStream in = null;
        try {
            // 设置PassiveMode传输
            ftp.enterLocalPassiveMode();
            //设置二进制传输，使用BINARY_FILE_TYPE，ASC容易造成文件损坏
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            //判断FPT目标文件夹时候存在不存在则创建
            if(!ftp.changeWorkingDirectory(ftpPath)){
                ftp.makeDirectory(ftpPath);
            }
            //跳转目标目录
            ftp.changeWorkingDirectory(ftpPath);
            //上传文件
            File file = new File(filePath);
            in = new FileInputStream(file);

            String tempName = ftpPath+"/"+file.getName();
            System.out.println(tempName);
            flag = ftp.storeFile(new String (tempName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1),in);
            if(flag){
                logger.info("上传成功");
            }else{
                logger.error("上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传失败");
        }finally{
            try {
                if(null != in){
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return flag;
    }


    /**
     * FPT上文件的复制
     * @param olePath 原文件地址
     * @param newPath 新保存地址
     * @param fileName 文件名
     * @return boolean
     */
    public boolean copyFile(String olePath, String newPath,String fileName) {
        boolean flag = false;
        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(olePath);
            //设置连接模式，不设置会获取为空
            ftp.enterLocalPassiveMode();
            // 获取目录下文件集合
            FTPFile[] files = ftp.listFiles();
            ByteArrayInputStream  in = null;
            ByteArrayOutputStream out = null;
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {
                    //读取文件，使用下载文件的方法把文件写入内存,绑定到out流上
                    out = new ByteArrayOutputStream();
                    ftp.retrieveFile(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"), out);
                    in = new ByteArrayInputStream(out.toByteArray());
                    //创建新目录
                    ftp.makeDirectory(newPath);
                    //文件复制，先读，再写
                    //二进制
                    ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                    flag = ftp.storeFile(newPath+File.separator+(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1")),in);
                    out.flush();
                    out.close();
                    in.close();
                    if(flag){
                        logger.info("转存成功");
                    }else{
                        logger.error("复制失败");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("复制失败");
        }
        return flag;
    }

    /**
     * 实现文件的移动，这里做的是一个文件夹下的所有内容移动到新的文件，
     * 如果要做指定文件移动，加个判断判断文件名
     * 如果不需要移动，只是需要文件重命名，可以使用ftp.rename(oleName,newName)
     * @param oldPath  老路径
     * @param newPath  新路径
     * @return boolean
     */
    public boolean moveFile(String oldPath,String newPath){
        boolean flag = false;

        try {
            ftp.changeWorkingDirectory(oldPath);
            ftp.enterLocalPassiveMode();
            //获取文件数组
            FTPFile[] files = ftp.listFiles();
            //新文件夹不存在则创建
            if(!ftp.changeWorkingDirectory(newPath)){
                ftp.makeDirectory(newPath);
            }
            //回到原有工作目录
            ftp.changeWorkingDirectory(oldPath);
            for (FTPFile file : files) {

                //转存目录
                flag = ftp.rename(new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1), newPath+File.separator+new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                if(flag){
                    logger.info(file.getName()+"移动成功");
                }else{
                    logger.error(file.getName()+"移动失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("移动文件失败");
        }
        return flag;
    }

    /**
     * 删除FTP上指定文件夹下文件及其子文件方法，添加了对中文目录的支持
     * @param ftpFolder 需要删除的文件夹
     * @return boolean
     */
    public boolean deleteByFolder(String ftpFolder){
        boolean flag = false;
        try {
            ftp.changeWorkingDirectory(new String(ftpFolder.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                //判断为文件则删除
                if(file.isFile()){
                    ftp.deleteFile(new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                }
                //判断是文件夹
                if(file.isDirectory()){
                    String childPath = ftpFolder + File.separator+file.getName();
                    //递归删除子文件夹
                    deleteByFolder(childPath);
                }
            }
            //循环完成后删除文件夹
            flag = ftp.removeDirectory(new String(ftpFolder.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            if(flag){
                logger.info(ftpFolder+"文件夹删除成功");
            }else{
                logger.error(ftpFolder+"文件夹删除成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除失败");
        }
        return flag;

    }

    /**
     * 创建目录
     * @param folderPath 文件路径
     * @return boolean
     */
    public boolean mkdirFolder(String folderPath){
        boolean flag = false;
        try {
            //新文件夹不存在则创建
            if(!ftp.changeWorkingDirectory(folderPath)){
                ftp.makeDirectory(folderPath);
            }
            logger.info("目录创建成功"+folderPath);
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
            logger.error("目录创建失败");
        }
        return flag;
    }


    /**
     * 遍历解析文件夹下所有文件
     * @param folderPath 需要解析的的文件夹
     * @return boolean
     */
    public boolean readFileByFolder(String folderPath){
        boolean flag = false;
        InputStream in ;
        BufferedReader reader ;
        try {
            ftp.changeWorkingDirectory(new String(folderPath.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            //设置FTP连接模式
            ftp.enterLocalPassiveMode();
            //获取指定目录下文件文件对象集合
            FTPFile files[] = ftp.listFiles();

            for (FTPFile file : files) {
                //判断为txt文件则解析
                if(file.isFile()){
                    String fileName = file.getName();
                    if(fileName.endsWith(".txt")){
                        in = ftp.retrieveFileStream(new String(file.getName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
                        reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        String temp;
                        StringBuffer buffer = new StringBuffer();
                        while((temp = reader.readLine())!=null){
                            buffer.append(temp);
                        }
                        reader.close();
                        in.close();
                        //ftp.retrieveFileStream使用了流，需要释放一下，不然会返回空指针
                        ftp.completePendingCommand();
                        //这里就把一个txt文件完整解析成了个字符串，就可以调用实际需要操作的方法
                        System.out.println(buffer.toString());
                    }
                }
                //判断为文件夹，递归
                if(file.isDirectory()){
                    String path = folderPath+File.separator+file.getName();
                    readFileByFolder(path);
                }
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("文件解析失败");
        }
        return flag;
    }


    public boolean uploadDirectory(String localDirectory,
                                   String remoteDirectoryPath) {
        File src = new File(localDirectory);

        File[] allFile = src.listFiles();
        for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
            if (!allFile[currentFile].isDirectory()) {
                String srcName = allFile[currentFile].getPath().toString();
                uploadFile(srcName, remoteDirectoryPath);
            }
        }
        for (int currentFile = 0;currentFile < allFile.length;currentFile++) {
            if (allFile[currentFile].isDirectory()) {
                // 递归
                uploadDirectory(allFile[currentFile].getPath().toString(),
                        remoteDirectoryPath);
            }
        }
        return true;
    }



    public static void main(String[] args) {
        FtpUtil ftpUtil = new FtpUtil();
        FTPClient ftpClient = ftpUtil.getFtpClient("10.1.176.101", 21, ftpUtil.USERNAME, ftpUtil.PASSWORD);
        System.out.println(ftpClient);
     //   ftpUtil.mkdirFolder("/ccicllll");
       // ftpUtil.readFileByFolder(ftpClient, "/");
      //  ftpUtil.CreateDirecroty(ftpClient,"/ccicall/test");
        //ftpUtil.mkdirFolder(ftpClient,"/test/test");
      //  ftpUtil.downLoadFtp(ftpClient,"/","application-line.properties","C:\\Users\\guoyapeng\\Desktop\\weixin");
      //  ftpUtil.uploadFile(ftpClient,"C:\\Users\\guoyapeng\\Desktop\\weixin\\application-line.properties","/test/");
        //test.copyFile(ftp, "/file", "/txt/temp", "你好.txt");
        //test.uploadFile(ftp, "C:\\下载\\你好.jpg", "/");
        //test.moveFile(ftp, "/file", "/txt/temp");
        //test.deleteByFolder(ftp, "/txt");
        ftpUtil.uploadDirectory("C:\\Users\\guoyapeng\\Desktop\\fsdownload","/test/ccic/");
        ftpUtil.closeFtp();
    }

}
