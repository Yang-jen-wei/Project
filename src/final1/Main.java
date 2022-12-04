package final1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Main {
	public static void main(String[] args) throws IOException {
		//root node
			
		//WebTree tree = new WebTree(rootPage);
		
		//build childnode


		System.out.println("Please input (1)num of keywords (2)name and weight:");
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()){
			int numOfKeywords = scanner.nextInt();
			ArrayList<Keyword> keywords = new ArrayList<Keyword>();
			for(int i = 0; i < numOfKeywords; i++)
			{
				String name = scanner.next();
				double weight = scanner.nextDouble();
				Keyword k = new Keyword(name, weight);
				keywords.add(k);
			}
			ArrayList<String> urlset = new ArrayList<String>();
			HashMap<String,String> url=new HashMap<String,String>();
			url=(HashMap<String, String>) new Google("keywords").query();
			for(var v : url.keySet()) {
				String u = url.get(v);
				u = u.substring(u.indexOf("https://"));
				urlset.add(u);}

		}
		scanner.close();
		}


	}

