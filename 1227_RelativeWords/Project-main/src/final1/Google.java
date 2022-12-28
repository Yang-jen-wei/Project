package final1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Google
{
	public String searchKeyword;
	public String url;
	public String content;
	
	public Google(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		return retVal;
	}
	
	public  ArrayList<String> RelativeWords() {
		String InputWord = searchKeyword;
    	ArrayList<String> RelativeWords = new ArrayList<String>();
        try {
            Document doc = Jsoup.connect(url).get();    //connect to the link
            Elements elements = doc.body().select("b");     //select all elements in the website's body                     
            for (Element ele : elements)                    //get all elements
                if (!ele.ownText().equals("") &&  findLCS(ele.ownText().toUpperCase(), InputWord.toUpperCase()) < InputWord.length()) {              //if the text in a element is not "" or input_word
                	RelativeWords.add(ele.ownText()); //then add it to RelativeWords
                	if(RelativeWords.size()==3)
                		return RelativeWords;
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(words);
        return RelativeWords;
    }
	
	private int findLCS(String x, String y){
		//1. Implement the LCS algorithm
		int[][] L = new int[x.length()+1][y.length()+1];;
		for(int i=0; i<x.length(); i++) {
			L[i][0] = 0;
		}
		for(int j=0; j<y.length(); j++) {
			L[0][j] = 0;
		}
		for(int i=1; i<x.length()+1; i++) {
			for(int j=1; j<y.length()+1; j++) {
				if(x.substring(i-1,i).equals(y.substring(j-1,j))) {
					L[i][j] =L[i-1][j-1]+1;
				}else if(L[i][j-1] >L[i-1][j]) {
					L[i][j] =L[i][j-1];
				}else {
					L[i][j] =L[i-1][j];
				}
			}
		}
		return L[x.length()][y.length()]; 
	}
	
	public HashMap<String, String> query() throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		

		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis)
		{
			try 
			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) 
				{
					continue;
				}
				
				//System.out.println(citeUrl.substring(citeUrl.indexOf("https://")));
				
				//put title and pair into HashMap
				citeUrl = "https://www.google.com/" + citeUrl;
				retVal.put(title, citeUrl);
				
				//if (testFetchContent(citeUrl) ) {}

			} catch (Exception e) 
			{
//				e.printStackTrace();
			}
		}
		return retVal;
	}
    private String getCharset(String str) {  //獲取網頁編碼方式，有些網頁沒有提供，所以暫時不使用
        Pattern pattern = Pattern.compile("charset=.*");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return matcher.group(0).split("charset=")[1];
        return null;
    }

}
