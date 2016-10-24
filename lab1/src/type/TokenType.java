package type;

/**
 * Created by song on 16-10-23.
 * <p>
 * Token类型
 */
public enum TokenType {
    KEYWORDS, BORDER, ID, NUMBER, OPERATOR, COMMENTS, MULTI_COMMENTS;

    public static TokenType getType(String source) {
        if (isKeywords(source)) {
            return KEYWORDS;
        } else if (isBorder(source)) {
            return BORDER;
        } else if (isID(source)) {
            return ID;
        } else if (isNumber(source)) {
            return NUMBER;
        } else if (isOperator(source)) {
            return OPERATOR;
        } else if (isComments(source)) {
            return COMMENTS;
        } else if (isMultiComments(source)) {
            return MULTI_COMMENTS;
        }

        return null;
    }

    private static boolean isKeywords(String source) {
        return Character.isLowerCase(source.charAt(0)) && KeyWords.isKeywords(source.split(" ")[0]);
    }

    private static boolean isBorder(String source) {
        return Border.isBorder(source.charAt(0));
    }

    private static boolean isID(String source) {
        return false;
    }

    private static boolean isNumber(String source) {
        return Character.isDigit(source.charAt(0));
    }

    private static boolean isOperator(String source) {

    }

    private static boolean isComments(String source) {
        return false;
    }

    private static boolean isMultiComments(String source) {
        return true;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
