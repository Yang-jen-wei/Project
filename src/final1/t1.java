import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HtmlParser1 {
    String htmlUrl;
    ArrayList<String> hrefList = new ArrayList();
    String charSet;

    public HtmlParser1(String htmlUrl) {
        // TODO 自動生成的建構函式存根
        this.htmlUrl = htmlUrl;
    }

    public ArrayList<String> parser() throws IOException {  //獲得該網頁下的超連結
        URL url = new URL(htmlUrl);           //建立URL物件，建立連線
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        String contenttype = connection.getContentType();
        charSet = getCharset(contenttype);
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
                if (rs.indexOf("http") != -1) {  //帶http的為URL
                    if (rs != null)
                        hrefList.add(rs);
                }
            }
        }
        return hrefList;
    }

    public void getURL() throws IOException {       //獲得每個超連結對應的web網頁
        ArrayList<String> URLList = parser();
        for (int i = 0; i < URLList.size(); i++) {
            URL url = new URL(URLList.get(i));      //讀取每個擷取的URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"gb2312");
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            File dest = new File("wangye/"+i+".html");   //按數字順序命名儲存
            dest.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(dest);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gb2312");
            while ((str = br.readLine()) != null) {
                outputStreamWriter.write(str);          //輸出流寫入
            }
        }
    }
    
    private String getCharset(String str) {  //獲取網頁編碼方式，有些網頁沒有提供，所以暫時不使用
        Pattern pattern = Pattern.compile("charset=.*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return matcher.group(0).split("charset=")[1];
        return null;
    }
}

public class t1{
    public static void main(String[] arg) throws IOException {   //主方法
        HtmlParser1 HP = new HtmlParser1("https://www.vogue.com.tw/fashion/");
        ArrayList<String> hrefList = HP.parser();
        for (int i = 0; i < hrefList.size(); i++)
            System.out.println(hrefList.get(i));
    }
}
