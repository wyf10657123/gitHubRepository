package java8_demo.lambda_demo;

import domain.UserInfo;
import java8_demo.lambda_demo.Print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Test {

    public static void main(String[] args){
        //printString("test",e->e);
        //testThread();
        //testThreadNew();
        List<UserInfo> list=new ArrayList<>();
        UserInfo userInfo=new UserInfo();
        userInfo.setId(1L);
        userInfo.setName("a");
        userInfo.setSuccess(true);
        list.add(userInfo);
        userInfo=new UserInfo();
        userInfo.setId(2L);
        userInfo.setName("b");
        userInfo.setSuccess(true);
        list.add(userInfo);
        userInfo=new UserInfo();
        userInfo.setId(3L);
        userInfo.setName("c");
        userInfo.setSuccess(false);
        list.add(userInfo);
        List<UserInfo> userInfoList=null;
        //List<String> stringList=testMap(list);//map:把原来的流（集合）的每个元素按照lambda表达式映射生成新的流
        //testForEach(list);
       /* Map<Long,UserInfo> map=testCollectToMap(list);
        System.out.println(list);
        String[] strArr=new String[]{"a","123","12"};
        List list1=new ArrayList();
        Arrays.sort(strArr,(s1,s2)->Integer.compare(s1.length(),s2.length()));
        Arrays.stream(strArr).forEach(e->{
            System.out.println(e);
            list1.add(e);
        });*/
        //eagerEvaluator(evaluate(1),evaluate(2));
        lazyEvaluator(()->evaluate(1),()->evaluate(2));//懒操作
        //test revert
        //test revert123
    }

    public static Map<Long,UserInfo> testCollectToMap(List<UserInfo> list){
        Map<Long,UserInfo> map=list.stream().collect(Collectors.toMap(UserInfo::getId,e->e));
        return map;
    }

    public static void testForEach(List<UserInfo> list){
        list.stream().forEach(o->System.out.println(o.getName()));
    }

    public static List<String> testMap(List<UserInfo> list){
        return list.stream().map(UserInfo::getName).collect(toList());
    }

    public static void testReduce(List<UserInfo> list){
        //list.stream().map(e->e).reduce();
    }

    public static List<UserInfo> testFilter(List<UserInfo> list){
        return list.stream().filter(e->e.isSuccess()).collect(toList());
    }

    public static void printString(String str,Print<String> print){
        String s=print.print(str);
        System.out.println(s);
    }

    public static void testThread(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("old thread run...");
            }
        });
        thread.start();
    }

    public static void testThreadNew(){
        Thread thread=new Thread(()->System.out.println("new thread run..."));
        thread.start();
    }

    public static void testLazy(){

    }

    public static boolean evaluate(final int value) {
        System.out.println("evaluating ..." + value);
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return value > 100;
    }

    public static void eagerEvaluator(
            final boolean input1, final boolean input2) {
        System.out.println("eagerEvaluator called...");
        System.out.println("accept?: " + (input1 && input2));
    }

    public static void lazyEvaluator(
            final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
        System.out.println("lazyEvaluator called...");
        System.out.println("accept?: " + (input1.get() && input2.get()));
    }

}
