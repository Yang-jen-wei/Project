package final1;

public class TreeRootList {
		public String name;
	    public double count;
	    
	    public TreeRootList(String name, double count){
			this.name = name;
			this.count = count;
	    }
	    
	    @Override
	    public String toString(){
	    	return "["+name+","+count+"]";
	    }
	}