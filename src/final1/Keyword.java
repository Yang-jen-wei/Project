package final1;

import java.util.ArrayList;


public class Keyword {
	public String name;
	public double weight;
	
	public Keyword(String name, double weight){
		this.name = name;
		this.weight = weight;
	}
	
	@Override
	public String toString(){
		return "["+name+","+weight+"]";
	}

}
