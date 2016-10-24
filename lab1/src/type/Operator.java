package type;

/**
 * Created by song on 16-10-23.
 * <p>
 * 操作符
 */
public enum Operator {
    SINGLE_OPERATOR, DOUBLE_OPERATOR, OTHER;

    private static String[] singleOperator = new String[]{
            "=", "+", "-", "*", "/", ">", "<", "!"
    };

    private static String[] doubleOperator = new String[]{
            "==", "!=", "+=", "-=", "*=", "/=", "<=", ">=", "&&", "||"
    };

    public static Operator isOperator(String string) {
        if (string.length() == 1) {
            for (String aSingleOperator : singleOperator) {
                if (aSingleOperator.equals(string)) {
                    return SINGLE_OPERATOR;
                }
            }
        } else {
            for (String operator : doubleOperator) {
                if (operator.equals(string.substring(0, 2))) {
                    return DOUBLE_OPERATOR;
                }
            }

            for (String operator : singleOperator) {
                if (operator.equals(string.substring(0, 1))) {
                    return SINGLE_OPERATOR;
                }
            }
        }

        return OTHER;
    }
}
