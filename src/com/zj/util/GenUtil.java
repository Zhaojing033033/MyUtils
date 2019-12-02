package com.zj.util;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 飞哥的项目中，根据模板生成 domian,repository,service,service和serviceiml的文件。
 */
public class GenUtil {

    public static void main(String[] args) {

        String templatefClassName = "Data";

        String genClassName = "AddressCode111";

        String parentPacketPath = getCurrentPacketPath();

        String rootPacketPath= getParentPath(parentPacketPath);

        genDomainFile(templatefClassName, genClassName, rootPacketPath);

        genServiceFile(templatefClassName, genClassName, rootPacketPath);

        genServiceImplFile(templatefClassName, genClassName, rootPacketPath);

        genRositoryFile(templatefClassName, genClassName, rootPacketPath);

    }

    private static void genDomainFile(String templatefClassName, String genClassName, String parentPacketPath) {
        //domain包路径
        String domainPacket = parentPacketPath + "domain\\";
        //生成domain类
        String genDomainClassName = genClassName;
        String domainTemplateFilePath = domainPacket + templatefClassName + ".java";
        String domainCode = genDomainCode(domainTemplateFilePath, templatefClassName, genClassName);
        FileUtil.writeFile(domainPacket + genDomainClassName + ".java", domainCode);
    }

    private static void genServiceFile(String templatefClassName, String genClassName, String parentPacketPath) {
        //service包路径
        String serviceservicePacket = parentPacketPath + "service\\";
        //生成service
        String serviceClassName = genClassName + "Service";
        String serviceservicetemplateFilePath = serviceservicePacket + templatefClassName + "Service.java";
        String serviceCode = genServiceCode(serviceservicetemplateFilePath, templatefClassName, genClassName);
        FileUtil.writeFile(serviceservicePacket + serviceClassName + ".java", serviceCode);
    }

    private static void genServiceImplFile(String templatefClassName, String genClassName, String parentPacketPath) {
        //service\impl包路径
        String serviceImplPacket = parentPacketPath + "service\\impl\\";
        //生成serviceImpl
        String serviceImplClassName = genClassName + "ServiceImpl";
        String serviceImpltemplateFilePath = serviceImplPacket + templatefClassName + "ServiceImpl.java";
        String serviceImplCode = genServiceImplCode(serviceImpltemplateFilePath, templatefClassName, genClassName);
        FileUtil.writeFile(serviceImplPacket + serviceImplClassName + ".java", serviceImplCode);

    }

    private static void genRositoryFile(String templatefClassName, String genClassName, String parentPacketPath) {
        //repository包路径
        String repositoryPacket = parentPacketPath + "repository\\";
        //生成Repository
        String repositoryClassName = genClassName + "Repository";
        String repositorytemplateFilePath = repositoryPacket + templatefClassName + "Repository.java";
        String repositoryCode = genRepositoryCode(repositorytemplateFilePath, templatefClassName, genClassName);
        FileUtil.writeFile(repositoryPacket + repositoryClassName + ".java", repositoryCode);
    }

    private static String genRepositoryCode(String domaintemplateFilePath, String templatefClassName, String genClassName) {
        return simpleGen(domaintemplateFilePath, templatefClassName, genClassName);
    }

    private static String genServiceImplCode(String domaintemplateFilePath, String templatefClassName, String genClassName) {
        return simpleGen(domaintemplateFilePath, templatefClassName, genClassName);
    }

    private static String genServiceCode(String domaintemplateFilePath, String templatefClassName, String genClassName) {
        return simpleGen(domaintemplateFilePath, templatefClassName, genClassName);
    }

    public static String getParentPath(String templateFilePath) {
        String pathPart = FileUtil.getPathPart(templateFilePath);
        return pathPart + "\\";
    }


    public static String removeBody(String content) {
        return Pattern.compile("\\{(.*)\\}?", Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(content).replaceAll("{body}");
    }

    public static String field(int i) {
        String field = String.format("@Column(name = \"file_name%d\") \nprivate String field%d; \n", i, i);
        return field;
    }

    public static String genDomainCode(String templateFilePath, String originClassName, String genClassName) {
        String content = FileUtil.readFile(templateFilePath);
        content = replaceClassName(content, originClassName, genClassName);
        content = removeBody(content);
        StringBuilder body = new StringBuilder();
        int fieldNum = 10;
        body.append("{ \n");
        for (int i = 0; i < fieldNum; i++) {
            body.append(field(i));
        }
        body.append("}");
        return content.replace("{body}", body.toString());
    }

    private static String simpleGen(String templateFilePath, String originClassName, String genClassName) {
        String content = FileUtil.readFile(templateFilePath);
        content = replaceClassName(content, originClassName, genClassName);
        return content;
    }

    public static String replaceClassName(String content, String origin, String to) {
        return Pattern.compile(origin, Pattern.CASE_INSENSITIVE | Pattern.DOTALL).matcher(content).replaceAll(to);
    }

    /**
     * 获取当前类的的路径
     */
    public static String getCurrentPacketPath() {

        //获取项目的路径
        File directory = new File("");// 参数为空
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath() + "\\";
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(courseFile);

        //拼接maven项目 固有路径 src\main\java
        String temp = "src\\main\\java\\";

        //获取当前类的包路径
        Package aPackage = GenUtil.class.getPackage();
        String name = aPackage.getName();
        String packet = name.replaceAll("\\.", "\\\\") + "\\";

        //拼接
        String result = courseFile + temp + packet;
        System.out.printf(result);

        return result;
    }

}
