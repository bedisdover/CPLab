import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by song on 16-10-23.
 * <p>
 * parser
 */
public class Parser {

    private static List<String> TOKEN_LIST = new ArrayList<>();

    /**
     * 标记是否为多行注释
     */
    private static boolean multi_comments = false;

    private Parser() {
        /*do nothing*/
    }

    static void parse(String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.err.println("Error, the file doesn't exists");
            System.exit(1);
        }

        System.out.println("********************************************************************");
        System.out.println("Parse the file: " + fileName);
        System.out.println("********************************************************************\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        int lineNo = 0;
        while ((line = reader.readLine()) != null) {
            parseLine(++lineNo, line.trim());
        }
    }

    static void output() throws IOException {
        File file = new File("output.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        StringBuilder builder = new StringBuilder();
        TOKEN_LIST.forEach((builder::append));
        writer.write(builder.toString());

        System.out.println("Parsing done!!!");
        writer.flush();
        writer.close();
    }

    /**
     * 解析每一行的内容
     *
     * @param lineNo  行号
     * @param content 内容
     */
    private static void parseLine(int lineNo, String content) {
        int tokenLength = parseComments(lineNo, content);

        if (tokenLength == -1) {
            tokenLength = parseString(lineNo, content);
            if (tokenLength == -1) {
                tokenLength = parseKeywords(lineNo, content);
                if (tokenLength == -1) {
                    tokenLength = parseBorder(lineNo, content);
                    if (tokenLength == -1) {
                        tokenLength = parseID(lineNo, content);
                        if (tokenLength == -1) {
                            tokenLength = parseNumber(lineNo, content);
                            if (tokenLength == -1) {
                                tokenLength = parseOperator(lineNo, content);
                            }
                        }
                    }
                }
            }
        }

        if (tokenLength != -1 && tokenLength != content.length()) {
            parseLine(lineNo, content.substring(tokenLength).trim());
        }
    }

    /**
     * 解析注释，包括多行注释与单行注释
     *
     * @param lineNo 行号
     * @param string 解析内容
     * @return 解析的内容长度，若解析失败，返回-1
     */
    private static int parseComments(int lineNo, String string) {
        if (multi_comments) {
            if (match(lineNo, "MULTI_COMMENTS", string, "^\\*/") != -1) {
                multi_comments = false;
                return 2;
            } else {
                return match(lineNo, "MULTI_COMMENTS", string, ".*");
            }
        } else {
            if (match(lineNo, "MULTI_COMMENTS", string, "^/\\*.*") != -1) {
                multi_comments = true;

                return string.length();
            }

            return match(lineNo, "COMMENTS", string, "^//.*");
        }
    }

    private static int parseString(int lineNo, String string) {
        return match(lineNo, "STRING", string, "^\".*\"");
    }

    private static int parseBorder(int lineNo, String string) {
        return match(lineNo, "BORDER", string, "^[,{}\'\";:().\\[\\]]");
    }

    private static int parseKeywords(int lineNo, String string) {
        return match(lineNo, "KEYWORDS", string,
                "^((import)|(public)|(class)|(extends)|(static)|(void)|(throws)|(if)|(else)|(this)|(return))\\b");
    }

    private static int parseID(int lineNo, String string) {
        return match(lineNo, "ID", string, "^[A-Za-z_]\\w*");
    }

    static int parseNumber(int lineNo, String string) {
        return match(lineNo, "NUMBER", string, "^\\d+");
    }

    private static int parseOperator(int lineNo, String string) {
        return match(lineNo, "OPERATOR", string, "^([*=+-/><!]=?)|(&&)|(\\|\\|)");
    }

    /**
     * 匹配正则
     * 若匹配，创建相应的token序列
     *
     * @param lineNo    行号
     * @param tokenType token类型
     * @param string    待匹配的文本
     * @param pattern   正则表达式
     * @return 若匹配成功，返回匹配的长度，否则返回-1
     */
    private static int match(int lineNo, String tokenType, String string, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(string);

        if (matcher.find()) {
            createToken(lineNo, tokenType, string.substring(0, matcher.end()));

            return matcher.end();
        }

        return -1;
    }

    /**
     * 创建token
     *
     * @param lineNo    行号
     * @param tokenType token类型
     * @param value     值
     */
    private static void createToken(int lineNo, String tokenType, String value) {
        TOKEN_LIST.add(String.format("%3d:  ", lineNo) + String.format("%-20s", tokenType) + value + "\n");
    }
}
