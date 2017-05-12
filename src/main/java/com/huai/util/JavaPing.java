package com.huai.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhonghuai.zhang on 2017/5/12.
 */
public class JavaPing {

    List ips = new ArrayList();

    public void allPing(final String ip){
        for (int i = 0; i < 255; i++) {
            final int j = i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    String this_ip = ip+"."+j;
                    if(singlePing(this_ip)){
                        ips.add(this_ip);
                        System.out.println(" this_ip is alive : "+this_ip);
                    }
                }},"r"+i).start();
        }
    }

    public boolean singlePing(String ip){
        Runtime runtime =Runtime.getRuntime(); // 获取当前程序的运行进对象
        Process process = null; //声明处理类对象
        String line = null; //返回行信息
        InputStream is = null; //输入流
        InputStreamReader isr = null;// 字节流
        BufferedReader br = null;
        boolean res = false;// 结果
        try {
            process =runtime.exec("ping " + ip); // PING
            is =process.getInputStream(); // 实例化输入流
            isr = new InputStreamReader(is);// 把输入流转换成字节流
            br = new BufferedReader(isr);// 从字节中读取文本
            while ((line= br.readLine()) != null) {
//                System.out.println(line);
//                if(line.contains("TTL")) {
                if(getCheckResult(line)) {
                    res= true;
//                    break;
                }
            }
            is.close();
            isr.close();
            br.close();
            if (res){
//                System.out.println(ip+" ping通  ...");
            } else{
                System.out.println(ip+" ping不通...");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
//            runtime.exit(1);
        }
        return true;
    }


    //若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
    private static boolean getCheckResult(String line) {  // System.out.println("控制台输出的结果为:"+line);
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=(\\d+))",    Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(3));
            System.out.println(matcher.group(4));
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        new JavaPing().allPing("10.0.24");
        new JavaPing().singlePing("10.0.24.142");
    }

}
