package final1;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Main {
	
	public static void main(String[] args) throws IOException {
	
		
	ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword k1 = new Keyword("MV",5);
		Keyword k2 = new Keyword("流行音樂",4);
		Keyword k3 = new Keyword("歌手",4);
		Keyword k4 = new Keyword("播放清單",3);
		Keyword k5 = new Keyword("歌詞",3);
		Keyword k6 = new Keyword("專輯",3);
		Keyword k7 = new Keyword("交響",4);
		Keyword k8 = new Keyword("新歌",3);
		Keyword k9 = new Keyword("鋼琴",2);
		Keyword k10 = new Keyword("vocal",2);
		Keyword k11 = new Keyword("旋律",2);
		Keyword k12 = new Keyword("drum",3);
		Keyword k13 = new Keyword("翻唱",3);
		Keyword k14 = new Keyword("kkbox",2);
		Keyword k15 = new Keyword("情歌",1);
		Keyword k16 = new Keyword("wikipedia",-0.1);
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
		keywords.add(k16);
		

		System.out.println("Please input searchkeywords:");
		Scanner scanner = new Scanner(System.in);
		String  searchkeywords = scanner.next();
		ArrayList<String> urlset = new ArrayList<String>();
		ArrayList<String> Relatives = new Google(searchkeywords).RelativeWords();
		ArrayList<String> InputList = Relatives;
		InputList.add(searchkeywords);
		Sort lst = new Sort();
		
		for(var input : InputList) {
			HashMap<String,String> url=new HashMap<String,String>();
			url=(HashMap<String, String>) new Google(input).query();
			
			for(var v : url.keySet()) {
				String u = url.get(v);
				urlset.add(u);
			}
//			System.out.println(urlset);
		
			//root node
			
			for(int i=0;i<10;i++) {
				WebPage rootPage = new WebPage(urlset.get(i), "tree"+i);		
				WebTree tree = new WebTree(rootPage);
		       		tree.setPostOrderScore(keywords);
		    		//tree.eularPrintTree();
		    		lst.add(new TreeRootList(urlset.get(i), tree.root.nodeScore));
		    		
				}
		}

		lst.sort();
		lst.output();




		scanner.close();

		}

	}

