package final1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		try {
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
			String retVal = "";
		
			String line = null;
			while ((line = br.readLine()) != null){
			    retVal = retVal + line + "\n";
			}
			return retVal;
	    }catch(Exception e) {
	    	return "         ";
	    }
    }
    
    public int BoyerMoore(String T, String P){
    	int n=T.length();
    	int m=P.length();
    	int i = m -1;
        int j = m -1;
        int count=0;
		do {
			if (T.charAt(i)==P.charAt(j)) {
    			if (j==0) {
    				i=i+1;
    				count=count+1;
    			}else {
    				i=i-1;
    				j=j-1;}
			}else {
    		i=i+m-min(j,1+last(T.charAt(i),P));
    		j=m-1;}
				
		 }while (i<=n-1); 
		if(count>0) {
			return count;
		}else {
			return -1;
		}
        
        // Bonus: Implement Boyer-Moore Algorithm     

		}
   
   

    public int last(char c, String P){
    	// Bonus: Implement last occurence function
    	int d;
    	d=P.lastIndexOf(c);
        return d;
    	}
  
    

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0; 

			if(BoyerMoore(content, keyword)>=0) {

				retVal=retVal+BoyerMoore(content, keyword);
			}
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm)

		return retVal;
		
    }
}
