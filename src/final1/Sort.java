package final1;

import java.util.ArrayList;


public class Sort {
	private ArrayList<TreeRootList> lst;
	
	public Sort(){
		this.lst = new ArrayList<TreeRootList>();
    }
	
	public void add(TreeRootList keyword){
		lst.add(keyword);
    }
	
	//Quick sort
	public void sort(){
		if(lst.size() == 0)
		{
			System.out.println("InvalidOperation");
		}
		else 
		{
			quickSort(0, lst.size()-1);
		}
	}
	
	private void quickSort(int leftbound, int rightbound){
		//1. Implement QuickSort algorithm
		 if(leftbound < rightbound) {
	            int i = leftbound; // 由左至右的索引
	            int j = rightbound + 1; // 由右至左的索引
	            while(true) {
	                while( i+1 < lst.size() && lst.get(i++).count < lst.get(leftbound).count); // 向右找, 直到找到比基準值大的數
	                while( j-1 >= 0 && lst.get(--j).count> lst.get(leftbound).count); // 向左找, 值到找到比基準值小的數
	                if( i >= j) break; // 若i,j重疊或i超過j後則退出循環
	                swap(i , j);
	            }
	            swap(leftbound , j); // 基準點與 j 交換
	            quickSort(leftbound , j - 1); // 遞迴排序基準點左子序列
	            quickSort(j + 1 , rightbound); // 遞迴排序基準點右子序列
	            
	        }
		

	}
	
	private void swap(int aIndex, int bIndex){
		TreeRootList temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	
	public void output(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = lst.size()-1; i > 0; i--){
			System.out.println(lst.get(i).name+" "+lst.get(i).count);
			/*TreeRootList k = lst.get(i);
			if(i > 0)sb.append(" ");
			sb.append(k.toString());*/
		}
		
		//System.out.println(sb.toString());	
	}
}
