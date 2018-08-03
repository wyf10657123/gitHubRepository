package my_maven_pro.maven_demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import my_maven_pro.maven_demo.domain.SolutionRule;
import my_maven_pro.maven_demo.domain.UserInfo;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class Test {

    public static void main(String[] args){
        String str=null;
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName("345");
        try{
            test(userInfo);
            System.out.println(userInfo.getUserName());
        }catch (Exception e){

        }
    }

    public static void test(UserInfo userInfo){

        userInfo.setUserName("123");
        System.out.println(userInfo.getUserName());
    }

    /**
     * 检查是否正数
     * @param input
     * @return
     */
    public static final boolean checkPositiveNumber(final String input){
        Matcher m = compile("^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$").matcher(input);
        //Matcher m=compile("^[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)$").matcher(input);
        return m.matches();
    }

    /**
     * 检查是否正整数
     * @param input
     * @return
     */
    public static final boolean checkPositiveInteger(final String input){
        Matcher m = compile("^\\d*[1-9]\\d*$").matcher(input);
        return m.matches();
    }

    /**
     * 检查是否非负数
     * @param input
     * @return
     */
    public static final boolean checkNotNegtiveNumber(final String input){
        Matcher m = compile("^\\d+(\\.\\d+)?$").matcher(input);
        return m.matches();
    }

    private void sort() {
        List<UserInfo> list=new ArrayList<>();
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(5);
        list.add(userInfo);
        userInfo=new UserInfo();
        userInfo.setAge(1);
        list.add(userInfo);
        userInfo=new UserInfo();
        userInfo.setAge(4);
        list.add(userInfo);
        userInfo=new UserInfo();
        userInfo.setAge(2);
        list.add(userInfo);
        list.sort(Comparator.comparing(UserInfo::getAge));
        //list.sort((u1,u2)->Integer.compare(u1.getAge(),u2.getAge()));
        System.out.println(list);
    }

    private static void test() {
        try{
            UserInfo userInfo=new UserInfo();
            BigDecimal bigDecimal=new BigDecimal("10.12000");
            userInfo.setWeight(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP));
            System.out.println(userInfo.getWeight());
            Field[] fields=UserInfo.class.getDeclaredFields();
            for(int i=0;i<fields.length;i++){
                Type type=fields[i].getGenericType();
                if(type.getTypeName().equals(String.class.getTypeName())){
                    System.out.println(type);
                }
                if(type.getTypeName().equals(BigDecimal.class.getTypeName())){
                    System.out.println(type);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void play(){
       int x=2^-1;
       System.out.println(x);
    }

}
