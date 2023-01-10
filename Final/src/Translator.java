

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.qcloud.Utilities.Json.JSONArray;
import com.qcloud.Utilities.Json.JSONObject;


/**
 * @author GOOGLE翻译
 *
 */
public class Translator {

  static {
  }

  public static String translate(String word) throws Exception {
	  String apiKey = "YOU-API-KEY";
      String url = 
              "https://translation.googleapis.com/language/translate/v2/detect?" +
              	 "key=" + apiKey + "&q=" + URLEncoder.encode(word, "UTF-8");

      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestProperty("User-Agent", "Mozilla/5.0");

      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
      }
      in.close();
      String lang = parse(response.toString());
     
      url = "https://translation.googleapis.com/language/translate/v2/?" +
              	 "key=" + apiKey + "&source=" + lang + "&target=zh-Tw" + "&q=" + URLEncoder.encode(word, "UTF-8");

      obj = new URL(url);
      con = (HttpURLConnection) obj.openConnection();
      con.setRequestProperty("User-Agent", "Mozilla/5.0");

      in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
      }
      in.close();
      
      return parseResult(response.toString());
  }

  public static String parse(String inputJson) throws Exception {
	  JSONObject jsonArray = new JSONObject(inputJson);
	  String language;
	  language = jsonArray.getJSONObject("data").getJSONArray("detections")
			  .getJSONArray(0).getJSONObject(0).getString("language");
      return language;
  }
  
  public static String parseResult(String inputJson) throws Exception {
	  JSONObject jsonArray = new JSONObject(inputJson);
	  String tr;
	  tr = jsonArray.getJSONObject("data").getJSONArray("translations")
			  .getJSONObject(0).getString("translatedText");
      return tr;
  }
}
