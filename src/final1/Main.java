package final1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword k1 = new Keyword("熱門",5);
		Keyword k2 = new Keyword("流行",4);
		Keyword k3 = new Keyword("歌手",4);
		Keyword k4 = new Keyword("播放清單",3);
		Keyword k5 = new Keyword("排行榜",3);
		Keyword k6 = new Keyword("告示牌",3);
		Keyword k7 = new Keyword("歌曲",4);
		Keyword k8 = new Keyword("新歌",3);
		Keyword k9 = new Keyword("吳亦凡",2);
		Keyword k10 = new Keyword("王力宏",2);
		Keyword k11 = new Keyword("Kanye West",2);
		Keyword k12 = new Keyword("藝人",3);
		Keyword k13 = new Keyword("大野雄大",3);
		Keyword k14 = new Keyword("菅田将暉",2);
		Keyword k15 = new Keyword("yellow",1);
		keywords.add(k1);
		keywords.add(k2);
		keywords.add(k3);
		keywords.add(k4);
		keywords.add(k5);
		keywords.add(k6);
		keywords.add(k7);
		keywords.add(k8);
		keywords.add(k9);
		keywords.add(k10);
		keywords.add(k11);
		keywords.add(k12);
		keywords.add(k13);
		keywords.add(k14);
		keywords.add(k15);
		//root node

		//WebTree tree = new WebTree(rootPage);
		
		//build childnode


		System.out.println("Please input searchkeywords:");
		Scanner scanner = new Scanner(System.in);
		String searchkeywords = scanner.next();
		ArrayList<String> urlset = new ArrayList<String>();
		HashMap<String,String> url=new HashMap<String,String>();
		url=(HashMap<String, String>) new Google(searchkeywords).query();

		for(var v : url.keySet()) {
			String u = url.get(v);
			urlset.add(u);
		}
		WebPage webpage=new WebPage(urlset.get(0),keywords.get(1).name);
		webpage.setScore(keywords);
		System.out.println(webpage.score);

		
		scanner.close();

		}

	}

