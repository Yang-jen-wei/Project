
import java.util.Iterator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
	private ArrayList<Keyword> keywords;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        keywords = new ArrayList<Keyword>();
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
		
		ArrayList<String> urlset = new ArrayList<String>();
		Sort lst = new Sort();
		String keyW = request.getParameter("keyword");
		ArrayList<String> RelativeWords = new Google(keyW).FindRelativeWords();
		ArrayList<String> InputWords = RelativeWords;
		InputWords.add(keyW);
		HashMap<String,String> tmp=new HashMap<String,String>();

		for(String word :InputWords) {
			tmp=(HashMap<String, String>) new Google(word).query();
			for(String v : tmp.keySet()) {
				String u = tmp.get(v);
				urlset.add(u);
			}
			//root node
			for(int i=0;i<10;i++) {
				WebPage rootPage = new WebPage(urlset.get(i), "tree"+i);		
				WebTree tree = new WebTree(rootPage);
		       		tree.setPostOrderScore(keywords);
		    		//tree.eularPrintTree();
		    		lst.add(new TreeRootList(urlset.get(i), tree.root.nodeScore));
				lst.sort();
				}
		}
		
		String[][] query = new String[lst.lst.size()][2];
		for (int i = lst.lst.size()-1; i >= 0 ; i--) {
			String title;
			for (Entry<String, String> e : tmp.entrySet()) {
				if(lst.lst.get(i).name == e.getValue()) {
					title =  String.valueOf(e.getKey());
					query[lst.lst.size()-1-i][0] = title;
					query[lst.lst.size()-1-i][1] = lst.lst.get(i).name;
				}
			}
		}

		request.setAttribute("query", query);
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
