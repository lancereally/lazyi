package lancereally;

import java.io.*;

public class Replace {

//    public static void main(String[] args) throws Exception {
//        File srcDir = new File("D:/Java");
//
//        if(!(srcDir.exists() && srcDir.isDirectory()))
//            throw new Exception("目录不存在");
//
//        File[] files = srcDir.listFiles(
//                (dir, name) -> name.endsWith(".java")
//        );
//
//        System.out.println(files.length);
//        //目标文件夹
//        File destDir = new File("D:/Java/jad");
//        if(!destDir.exists()) destDir.mkdir();
//        for(File f :files){
//            FileInputStream  fis = new FileInputStream(f);
//            String destFileName = f.getName().replaceAll(".java", ".jad");
//            FileOutputStream fos = new FileOutputStream(new File(destDir,destFileName));
//            copy(fis,fos);
//            fis.close();
//            fos.close();
//        }
//    }
//
//    private static void copy(InputStream ips, OutputStream ops) throws Exception{
//        int len = 0;
//        byte[] buf = new byte[1024];
//        while((len = ips.read(buf)) != -1){
//            ops.write(buf,0,len);
//        }
//
//    }
public static void main(String[] args) throws IOException {
    File myMMD = new File("D:/Game_Steam/steamapps/workshop/content/431960");
    File recent = new File("D:/Game_Steam/steamapps/workshop/content/431960/1400694356");
    File before = new File("D:/Game_Steam/steamapps/workshop/content/431960/1986397845");
    Long end = recent.lastModified();
    Long start = before.lastModified();

    if(!myMMD.exists()&&myMMD.isDirectory()) return;

    File[] temp = myMMD.listFiles(
            (file, s) -> s.endsWith(".mp4")
    );
    if (temp.length<1) return;

    File store = new File("D:/Compress_7Zip/Qmdownload/MMD/Approval");
    for (File f : temp){
        if(f.lastModified() < end && f.lastModified() > start){
            FileInputStream  fis = new FileInputStream(f);
            String destFileName = f.getName();
            FileOutputStream fos = new FileOutputStream(new File(store,destFileName));
            copy(fis,fos);
            fis.close();
            fos.close();
        }
    }
}

    public static void copy(FileInputStream in, FileOutputStream out) throws IOException {
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = in.read(b)) != -1)
            out.write(b,0,len);
    }
}