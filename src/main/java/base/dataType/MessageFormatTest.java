package base.dataType;

import java.text.MessageFormat;

public class MessageFormatTest {

    public static void main(String[] args){
        MessageFormat messageFormat=new MessageFormat("这里是'{0}'{0}{1}");
        String[] strings={"浙江","杭州"};
        System.out.println(messageFormat.format(strings));

    }

}
