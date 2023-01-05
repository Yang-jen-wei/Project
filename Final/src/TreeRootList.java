

public class TreeRootList {
		public String name;
	    public double count;
	    public String url;
	    
	    public TreeRootList(String name, double count,String url){
			this.name = name;
			this.count = count;
			this.url = url;
	    }
	    
	    @Override
	    public String toString(){
	    	return "["+name+","+count+"]";
	    }
	}