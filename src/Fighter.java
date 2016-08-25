
public class Fighter {
	public String FighterName = "";
	public static final int STATS_TOTAL = 100;
	
	private int Might;
	private int Speed;
	private int Sanity;
	
	public Fighter(String name){
		this.FighterName = name;
		this.setMight(0);
		this.setSpeed(0);
		this.setSanity(0);
		
	}
	
	public int getMight() {
		return this.Might;
	}

	public boolean setMight(int mightVal) {
		boolean result = false;
		
		if (mightVal>0){
			this.Might = mightVal;
			result = true;
		}
		else{
			this.Might = 0;
		}
		
		return result;
	}
	
	public int getSpeed() {
		return this.Speed;
	}

	public boolean setSpeed(int speedVal) {
		boolean result = false;
		
		if (speedVal>0){
			this.Speed = speedVal;
			result = true;
		}
		else{
			this.Speed = 0;
		}
		
		return result;
	}


	public int getSanity() {
		return this.Sanity;
	}

	public boolean setSanity(int sanityVal) {
		boolean result = false;
		
		if (sanityVal>0){
			this.Sanity = sanityVal;
			result = true;
		}
		else{
			this.Sanity = 0;
		}
		
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
