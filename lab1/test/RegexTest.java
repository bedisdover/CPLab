import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by song on 16-10-25.
 * <p>
 * 测试正则表达式
 */
public class RegexTest {

    @Test
    public void testRegex() {
        Pattern pattern = Pattern.compile("^[A-Za-z_]\\w*");
        Matcher matcher = pattern.matcher("_input_234 a123123 b");

        System.out.println(matcher.find());
        System.out.println(matcher.end());
    }

    @Test
    public void testParseBorder() {
        Pattern pattern = Pattern.compile("[,{}\'\";:().\\[\\]]");

        System.out.println(pattern.matcher(",").find());
        System.out.println(pattern.matcher("{").find());
        System.out.println(pattern.matcher("}").find());
        System.out.println(pattern.matcher("'").find());
        System.out.println(pattern.matcher("\"").find());
        System.out.println(pattern.matcher(";").find());
        System.out.println(pattern.matcher(":").find());
        System.out.println(pattern.matcher("(").find());
        System.out.println(pattern.matcher(")").find());
        System.out.println(pattern.matcher(".").find());
        System.out.println(pattern.matcher("[").find());
        System.out.println(pattern.matcher("123").find());
    }

    @Test
    public void testParseString() {
        Pattern pattern = Pattern.compile("^\".*\"");
        Matcher matcher = pattern.matcher("\"123\"1");

        System.out.println(matcher.find());
        System.out.println(matcher.end());
    }

    @Test
    public void testParseOperator() {
        Pattern pattern = Pattern.compile("^([*=+-/><!]=?)|(&&)|(\\|\\|)");

        System.out.println(pattern.matcher("&&").find());
        System.out.println(pattern.matcher("*").find());
        System.out.println(pattern.matcher("=").find());
        System.out.println(pattern.matcher("+").find());
        System.out.println(pattern.matcher("-").find());
        System.out.println(pattern.matcher("/").find());
        System.out.println(pattern.matcher(">").find());
        System.out.println(pattern.matcher("<").find());
        System.out.println(pattern.matcher("!").find());
        System.out.println(pattern.matcher("==").find());
        System.out.println(pattern.matcher("*=").find());
        System.out.println(pattern.matcher(">=").find());
    }

    @Test
    public void testParseKeywords() {
        Pattern pattern = Pattern.compile("^((import)|(public)|(class)|(extends)|(static)|(void)|(throws)|(if)|(eles)|(this)|(return))\\b");

        System.out.println(pattern.matcher("impor1tif").find());
    }

    @Test
    public void testParseComments() {
        Pattern pattern = Pattern.compile("^\\*/");
        System.out.println(pattern.matcher("*/").find());
        System.out.println(pattern.matcher("12*/").find());
    }
}
