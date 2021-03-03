package test;

import java.io.InputStream;

public class PathTest {

    public static void main(String[] args) {
        InputStream inputStream =
                ClassLoader.getSystemClassLoader().getResourceAsStream("classpath:/properties/rocket_mq.properties");
        if (inputStream == null) {
            System.out.println("not exist");
        }else{
            System.out.println("exit");
        }

    }
}
