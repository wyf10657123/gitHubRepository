package my_maven_pro.maven_demo;

import my_maven_pro.maven_demo.domain.UserInfo;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args){
        try{
            Object o="是";
            String o1=o.toString();
            System.out.println(o1);
            System.out.println(o.toString().equals("是"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play(final UserInfo userInfo){
        userInfo.setUserName("123");
        System.out.println(userInfo);
    }

}
