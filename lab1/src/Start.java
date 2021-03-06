import java.io.IOException;

/**
 * Created by song on 16-10-22.
 * <p>
 * 程序入口
 */
public class Start {

    public static void main(String[] args) throws IOException {
        String fileName = "in.txt";

        if (args.length == 1) { // 自定义输入文件路径
            fileName = args[0];
        } else if (args.length > 1) {
            System.out.println("Argument number is too much");
            System.exit(1);
        }
        // 解析源文件
        Parser.parse(fileName);
        // 输出解析结果
        Parser.output();
    }
}
