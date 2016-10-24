import type.TokenType;

/**
 * Created by song on 16-10-22.
 * <p>
 * Token对象
 */
class Token {

    /**
     * 行号
     */
    private final int lineNo;

    /**
     * Token类型
     */
    private final TokenType tokenType;

    /**
     * 值
     */
    private final String value;

    Token(int lineNo, TokenType tokenType, String value) {
        this.lineNo = lineNo;
        this.tokenType = tokenType;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%3d:  ", lineNo)
                + String.format("%-20s", tokenType)
                + value + "\n";
    }
}
