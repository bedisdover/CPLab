package type;

/**
 * Created by song on 16-10-23.
 * <p>
 * 边界符
 */
enum Border {
    ;

    private static char[] borderList = new char[]{
            ',', '{', '}', '\'', '\"', ';', ':', '(', ')'
    };

    public static boolean isBorder(char c) {
        for (char aBorderList : borderList) {
            if (aBorderList == c) {
                return true;
            }
        }

        return false;
    }
}
