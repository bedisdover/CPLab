import org.junit.Test;

/**
 * Created by song on 16-10-23.
 *
 * 测试Parser
 */
public class ParserTest {
    @Test
    public void getToken() throws Exception {
        Parser.parseNumber(1, "123");
        Parser.parseNumber(1, "awer");

    }
}