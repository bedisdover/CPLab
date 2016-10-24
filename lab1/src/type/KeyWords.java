package type;

/**
 * Created by song on 16-10-22.
 * <p>
 * Token类型
 */
enum KeyWords {
    IMPORT, FROM, CLASS, EXTENDS, CONST, IF, ELSE, THIS, RETURN;

    /**
     * 判断一个字符串是否是关键字
     */
    public static boolean isKeywords(String string) {
        for (KeyWords keyWord : KeyWords.values()) {
            if (keyWord.toString().equals(string)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
