import type.TokenType;

import java.io.*;

/**
 * Created by song on 16-10-23.
 * <p>
 * parser
 */
interface Parser {

    static void parse(String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.err.println("Error, the file doesn't exists");
            System.exit(1);
        }

        System.out.println("**********************************************************");
        System.out.println("Parse the file: " + fileName);
        System.out.println("**********************************************************\n");

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        int lineNo = 0;
        while ((line = reader.readLine()) != null) {
            lineNo++;
            parseLine(lineNo, line);
        }
    }

    /**
     * 解析每一行的内容
     *
     * @param lineNo  行号
     * @param content 内容
     */
    static void parseLine(int lineNo, String content) {
        String temp = "";
        char c;
        for (int i = 0; i < content.length(); i++) {
            c = content.charAt(i);
            temp = temp + c;
        }
    }

    /**
     * 从字符串中分离出第一个token序列的内容
     * 此方法将会改变参数 source 的值，去掉已分离的部分
     *
     * @param source 源字符串
     * @return 第一个token序列的内容
     */
    static String getToken(StringBuilder source) {
        char firstChar = source.charAt(0);
        String result = "" + firstChar;

        int i = 1;
        if (Character.isDigit(firstChar)) { // 数字
            while (Character.isDigit(source.charAt(i))) {
                result = result + source.charAt(i++);
            }
        } else if (Character.isAlphabetic(firstChar)) {

        } else if (Character.isLetter(firstChar)) {

        }

        source.delete(0, i);

        return result;
    }

    static void createToken(int lineNo, String value) {

    }
}
