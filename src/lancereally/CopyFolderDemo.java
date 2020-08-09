package lancereally;

import java.io.*;

public class CopyFolderDemo {
    public static void main(String[] args) throws IOException {
// 封装数据源File
        //这里填源文件夹路径
        File myMMD = new File("D:/Game_Steam/steamapps/workshop/content/431960");
//        File recent = new File("D:/Game_Steam/steamapps/workshop/content/431960/1400694356");
//        File before = new File("D:/Game_Steam/steamapps/workshop/content/431960/1995882711");
        //5.13
        File recent = new File("D:/Game_Steam/steamapps/workshop/content/431960/2091434421");
        File before = new File("D:/Game_Steam/steamapps/workshop/content/431960/2079159585");
        Long end = recent.lastModified();
        Long start = before.lastModified();
        copyFolder(myMMD, end, start);
//copyFile(srcFile);
    }

    private static void copyFolder(File srcFile, Long end, Long start)
            throws IOException {
        File[] ff=srcFile.listFiles();
        for(File f:ff){
            if(f.isDirectory()){
                copyFolder(f, end, start);
            }else{ ///11111
                String suffix = f.getName();
                if (suffix.endsWith(".mp4") && f.lastModified() >= start)
                copyFile(f);
            }
        }
    }
    //用字节缓冲流实现文件复制
    private static void copyFile(File srcFile) throws IOException {
        File destFile = new File("D:/Compress_7Zip/Qmdownload/MMD/Approval",srcFile.getName()); //这里写目的文件夹路径
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                srcFile));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destFile));
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }
        bos.close();
        bis.close();
    }
}
