import org.junit.Test;

/**
 * Created by song on 16-10-23.
 *
 * 测试Parser
 */
public class ParserTest {
    @Test
    public void getToken() throws Exception {
        StringBuilder builder = new StringBuilder("-123abc");

        System.out.println(Parser.getToken(builder));
        System.out.println(builder.toString());
    }

}