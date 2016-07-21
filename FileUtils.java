package UC;

/**
 * Created by carrot on 16/7/21.
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtils {
    /**
     * 将文本文件中的内容读入到buffer中
     *
     * @param buffer   buffer
     * @param filePath 文件路径
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        line = bufferedReader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = bufferedReader.readLine(); // 读取下一行
        }
        bufferedReader.close();
        inputStream.close();
    }

    /**
     * 读取文本文件内容
     *
     * @param filePath 文件所在路径
     * @return 文本内容
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        FileUtils.readToBuffer(stringBuffer, filePath);
        return stringBuffer.toString();
    }

    public static void main(String[] args) {

        String filePath = "/Users/carrot/file.txt";
        String subString = "UCanUup";
        int count = 0;

        try {
            String fileString = FileUtils.readFile(filePath);
            //System.out.println(fileString);

            while(-1 != fileString.indexOf(subString)) {
                Pattern p = Pattern.compile(subString);
                Matcher m = p.matcher(fileString);
                fileString = m.replaceFirst("");

                count++;
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            System.out.println("count: " + count);
        }
    }
}
