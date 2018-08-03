package base.dataType;

import domain.UserInfo;

import java.util.*;

public class BaseDataType {

    private int i;

    public static void main(String[] args){
        BaseDataType baseDataType=new BaseDataType();
        System.out.println(baseDataType.i);
        String str="[{\"lon\":120.008089,\"speed\":0,\"bearing\":0,\"lat\":30.279938,\"satellites\":12,\"accuracy\":3.3,\"locationType\":1,\"timestamp\":1493201388880}]";
        long l=3*24*3600*1000;
        System.out.println(l);
        Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        Calendar c2=Calendar.getInstance();
        c2.setTime(new Date());
        c2.add(Calendar.DATE,3);
        System.out.println(c2.getTimeInMillis()-c.getTimeInMillis());
        UserInfo userInfo=new UserInfo();
        userInfo.setId(null);
        System.out.println(userInfo.getId());
    }

    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    private static void typeConvert() {
        byte b=1;
        int i=b+1;
        Integer t=i;
        int a =t;
        System.out.println(i);
    }

    private static void boxing() {
        int a=200;
        Integer b=null;
        Integer c=new Integer(100);
        System.out.println(a==b);
        System.out.println(b==c);
        Integer num1 = 100;
        Integer num2 = 200;
        Integer num4=200;
        Long num3 = 300L;
        System.out.println("num2==num4:"+(num2==num4));
        System.out.println("num2.equals(num4):"+(num2.equals(num4)));
        System.out.println(num3 == (num1 + num2));
        System.out.println(num3.equals(num1 + num2));
    }

}
