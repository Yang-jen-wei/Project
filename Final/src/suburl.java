
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class suburl {
    String htmlUrl;
    ArrayList<String> hrefList = new ArrayList();
    String charSet;

    public suburl(String htmlUrl) {
        // TODO 自動生成的建構函式存根
        this.htmlUrl = htmlUrl;
    }

    public ArrayList<String> parser() throws IOException {  //獲得該網頁下的超連結
    	try {
        URL url = new URL(htmlUrl);           //建立URL物件，建立連線
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "gb2312");   //建立輸入流
        BufferedReader br = new BufferedReader(isr);
        String str = null, rs = null;

        while ((str = br.readLine()) != null) {
            Pattern pattern = Pattern.compile("<a href=(.*?)>");    //識別這一行是否符合網頁的格式
            Matcher matcher = pattern.matcher(str);

            while (matcher.find()) {
                Pattern pattern1 = Pattern.compile("\"(.*?)\"");
                Matcher matcher1 = pattern1.matcher(matcher.group(1));
                if (matcher1.find()) {
                    rs = matcher1.group(1);      //將本行引號中的內容截取出來
                }
                if (rs.indexOf("https") != -1) {  //帶http的為URL
                    if (rs != null ) {
                    	hrefList.add(rs);
                    }
                }
            }
        }
        }catch(Exception e) {}
        return hrefList;
    }

    public void getURL() throws IOException {       //獲得每個超連結對應的web網頁
        ArrayList<String> URLList = parser();
    }


}
