import type.TokenType;

/**
 * Created by song on 16-10-22.
 * <p>
 * Token对象
 */
public class Token {

    /**
     * 行号
     */
    private int lineNo;

    /**
     * Token类型
     */
    private TokenType tokenType;

    /**
     * 值
     */
    private String value;

    public Token(int lineNo, TokenType tokenType, String value) {
        this.lineNo = lineNo;
        this.tokenType = tokenType;
        this.value = value;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
