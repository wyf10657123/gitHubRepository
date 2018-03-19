package java8_demo.lambda_demo;

@FunctionalInterface
public interface Print<T> {

    public String print(T t);

}
