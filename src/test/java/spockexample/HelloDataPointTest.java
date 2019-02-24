package spockexample;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class HelloDataPointTest {
	@DataPoint
    public static String TARO = "Taro";

    @DataPoint
    public static String JIRO = "Jiro";

    @DataPoint
    public static String sabro() {
        return "Saburo";
    }

    @Theory
    public void helloDataPoint(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
