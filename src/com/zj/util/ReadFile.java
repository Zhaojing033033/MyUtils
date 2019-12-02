package com.zj.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReadFile {
    /**
     * 查找该目录下的文件名和目录 （递归） 如： C:\Users\AAA\Desktop\巡视报告
     * @param parentPath C:\Users\AAA\Desktop
     * @param currentPath \巡视报告
     * @return  返回 Map。 key为文件（目录）名，value为该目录下的文件或目录 。若value为空，则是文件。
     */
    public  Map<String,Map> getFileList(String parentPath,String currentPath) {

        String path=parentPath+currentPath;

        Map<String,Map> ret= new HashMap<>();//返回的map。
        HashMap<String,Map> sub = new HashMap();//该目录下的记录。一个文件或目录，就是一条记录


        File file = new File(path);
        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {

            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                //System.out.println("文件：" + fileName);
                sub.put(fileName,null);
            }

            if (fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                //System.out.println("目录：" + fileName);
                Map<String, Map> fileList1 = getFileList(path, "\\" + fileName);
                ret.put(fileName,fileList1.get(fileName));
            }

        }
        ret.put(currentPath.substring(1),sub);
        return ret;
    }

    public static void main(String[] args) {
        ReadFile rf = new ReadFile();
        Map<String, Map> fileList = rf.getFileList("C:\\Users\\AAA\\Desktop", "\\巡视报告");
        System.out.println(fileList.size());
    }
}
