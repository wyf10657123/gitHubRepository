package my_maven_pro.maven_demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserInfo implements Serializable{
    private static final long serialVersionUID = -3316269581583481599L;
    private String userName;
    private int age;

    private BigDecimal weight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}
