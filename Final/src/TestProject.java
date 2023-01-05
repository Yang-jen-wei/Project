
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword k1 = new Keyword("youtube",4);
		Keyword k2 = new Keyword("流行音樂",5);
		Keyword k3 = new Keyword("歌手",4);
		Keyword k4 = new Keyword("播放清單",3);
		Keyword k5 = new Keyword("歌詞",5);
		Keyword k6 = new Keyword("專輯",5);
		Keyword k7 = new Keyword("歌曲",10);
		Keyword k8 = new Keyword("新歌",3);
		Keyword k9 = new Keyword("鋼琴",2);
		Keyword k10 = new Keyword("vocal",2);
		Keyword k11 = new Keyword("旋律",2);
		Keyword k12 = new Keyword("管弦",3);
		Keyword k13 = new Keyword("翻唱",3);
		Keyword k14 = new Keyword("kkbox",2);
		Keyword k15 = new Keyword("情歌",1);
		Keyword k16 = new Keyword("wikipedia",-1);
		Keyword k17 = new Keyword("music",5);
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
		keywords.add(k17);
		
		String searchkeywords = request.getParameter("keyword");
		ArrayList<String> urlset = new ArrayList<String>();
		ArrayList<String> nameset = new ArrayList<String>();
		Sort lst = new Sort();
		ArrayList<String> RelativeWords = new Google(searchkeywords).FindRelativeWords();
		ArrayList<String> InputWords = RelativeWords;
		String Inputkeywords = searchkeywords+ "+音樂";
		InputWords.add(Inputkeywords);
				
		for(String word :InputWords) {
			int start = urlset.size();
			HashMap<String,String> url=new HashMap<String,String>();
			url=(HashMap<String, String>) new Google(word).query();
			for(String v : url.keySet()) {
				String u = url.get(v);
				if( !nameset.contains(v)  ) {
					urlset.add(u);
					nameset.add(v);
				}
			}
			//root node
			for(int i=start;i<start+10;i++) {
				if(i>urlset.size()-1){
					break;
				}
				WebPage rootPage = new WebPage(urlset.get(i), "tree"+i);		
				WebTree tree = new WebTree(rootPage);
		       	tree.setPostOrderScore(keywords);
		    		//tree.eularPrintTree();
		    	lst.add(new TreeRootList(urlset.get(i), tree.root.nodeScore , nameset.get(i) ));
			}
		}
		
		lst.sort();
		int c = 0;
		for(int i = lst.lst.size()-1; i > 0; i--){
			if(lst.lst.get(i).count >0.01)
				c += 1;
		}
		
		String[][] s = new String[c][2];
		int num = 0;
		request.setAttribute("query", s);
		for(int i = lst.lst.size()-1; i > 0; i--){
			if(lst.lst.get(i).count >0.01) {
				s[num][0] = lst.lst.get(i).url;
				s[num][1] = lst.lst.get(i).name;
				num++;
			}
		}

		String requestUri = request.getRequestURI();
		request.setAttribute("requestUri", requestUri);
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
