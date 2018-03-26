package branham.joel;

public class Square{

	private boolean updated;
	private int num;

	public Square(){
		this(0);
	}
	
	public Square(int num){
		this.num = num;
		this.updated = false;
	}
	
	public int getNum(){
		return num;
	}
	
	public boolean isUpdated(){
		return updated;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	public void setUpdated(boolean b){
		updated = b;
	}
	
}