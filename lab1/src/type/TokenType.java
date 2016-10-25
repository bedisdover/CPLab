package type;

/**
 * Created by song on 16-10-23.
 * <p>
 * Token类型
 */
public enum TokenType {
    KEYWORDS, STRING, BORDER, ID, NUMBER, OPERATOR, COMMENTS, MULTI_COMMENTS;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
