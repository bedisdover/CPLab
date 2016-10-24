import type.Border;
import type.KeyWords;
import type.Operator;
import type.TokenType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 16-10-23.
 * <p>
 * parser
 */
class Parser {

    private static List<Token> TOKEN_LIST = new ArrayList<>();

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

        TOKEN_LIST.forEach((token -> {
            try {
                writer.write(token.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));

        System.out.println("Parse is successful!!!");
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
        if (content == null || content.length() == 0) {
            return;
        }

        if (multi_comments) {
            parseComments(lineNo, content);
            return;
        }

        int i = parseString(lineNo, content);

        if (i == -1) {
            i = parseKeywords(lineNo, content);
            if (i == -1) {
                i = parseBorder(lineNo, content);
                if (i == -1) {
                    i = parseID(lineNo, content);
                    if (i == -1) {
                        i = parseNumber(lineNo, content);
                        if (i == -1) {
                            i = parseComments(lineNo, content);
                            if (i == -1) {
                                i = parseOperator(lineNo, content);
                            }
                        }
                    }
                }
            }
        }

        if (i != -1 && i != content.length()) {
            parseLine(lineNo, content.substring(i).trim());
        }
    }

    private static int parseComments(int lineNo, String string) {
        if (string.length() < 2) {
            if (multi_comments) {
                createToken(lineNo, TokenType.MULTI_COMMENTS, string);
                return string.length();
            } else {
                return -1;
            }
        }

        String start = string.substring(0, 2);

        if (multi_comments) {
            if (start.equals("*/")) { // 多行注释结束
                multi_comments = false;
                createToken(lineNo, TokenType.MULTI_COMMENTS, string.substring(0, 2));

                return 2;
            } else { // 多行注释内
                createToken(lineNo, TokenType.MULTI_COMMENTS, string);

                return string.length();
            }
        } else {
            switch (start) {
                case "/*":
                    multi_comments = true;
                    createToken(lineNo, TokenType.MULTI_COMMENTS, string);

                    return string.length();
                case "//":
                    createToken(lineNo, TokenType.COMMENTS, string);

                    return string.length();
                default:
                    return -1;
            }

        }
    }

    private static int parseString(int lineNo, String string) {
        if (string.charAt(0) == '"') {
            int result = string.indexOf('"', 1);
            createToken(lineNo, TokenType.STRING, string.substring(0, result + 1));

            return result + 1;
        }

        return -1;
    }

    private static int parseBorder(int lineNo, String string) {
        char firstChar = string.charAt(0);

        if (Border.isBorder(firstChar)) {
            createToken(lineNo, TokenType.BORDER, firstChar + "");

            return 1;
        }

        return -1;
    }

    private static int parseKeywords(int lineNo, String string) {
        String firstPart = string.split(" ")[0];

        if (KeyWords.isKeywords(firstPart)) {
            createToken(lineNo, TokenType.KEYWORDS, firstPart);

            return firstPart.length();
        }

        return -1;
    }

    private static int parseID(int lineNo, String string) {
        char firstChar = string.charAt(0);

        if (Character.isLetter(firstChar) || firstChar == '_') {
            int i = 1;

            if (string.length() > 1) {
                while (Character.isLetterOrDigit(string.charAt(i)) || string.charAt(i) == '_') {
                    i++;
                }
            }

            createToken(lineNo, TokenType.ID, string.substring(0, i));

            return i;
        }


        return -1;
    }

    private static int parseNumber(int lineNo, String string) {
        int i = 0;

        while (Character.isDigit(string.charAt(i))) {
            i++;
        }

        if (i != 0) {
            createToken(lineNo, TokenType.NUMBER, string.substring(0, i));
            return i;
        }

        return -1;
    }

    private static int parseOperator(int lineNo, String string) {
        Operator operator = Operator.isOperator(string);

        switch (operator) {
            case SINGLE_OPERATOR:
                createToken(lineNo, TokenType.SINGLE_OPERATOR, string.substring(0, 1));
                return 1;
            case DOUBLE_OPERATOR:
                createToken(lineNo, TokenType.DOUBLE_OPERATOR, string.substring(0, 2));
                return 2;
            default:
                return -1;
        }
    }

    private static void createToken(int lineNo, TokenType tokenType, String value) {
        TOKEN_LIST.add(new Token(lineNo, tokenType, value));
    }
}
