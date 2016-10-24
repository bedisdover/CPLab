package type;

/**
 * Created by song on 16-10-23.
 *
 * 操作符
 */
public enum Operator {
    ;

    private static String[] singleOperator = new String[]{
            "=", "+", "-", "*", "/", ">", "<", "!"
    };

    private static String[] doubleOperator = new String[]{
            "==", "!=", "+=", "-=", "*=", "/=", "<=", ">=", "&&", "||"
    };

    public static boolean isOperator(String string) {
        return false;
    }
}
