import java.util.ArrayList;

// there are changes
public  class Cloud {
	
	int totalStorage;
	int totalRam;
	int avalilableStorage;
	int availableRam;
	//new change to this branch
	Cloud(int storage, int ram){
		setTotalStorage(storage);
		setTotalRam(ram);
		setAvailableRam(ram);
		setAvalilableStorage(storage);
	}
	
	public int getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(int totalStorage) {
		this.totalStorage = totalStorage;
	}

	public int getTotalRam() {
		return totalRam;
	}

	public void setTotalRam(int totalRam) {
		this.totalRam = totalRam;
	}

	public int getAvalilableStorage() {
		return avalilableStorage;
	}

	public void setAvalilableStorage(int avalilableStotrage) {
		this.avalilableStorage = avalilableStotrage;
	}

	public int getAvailableRam() {
		return availableRam;
	}

	public void setAvailableRam(int availableRam) {
		this.availableRam = availableRam;
	}


}

class Clouds{
	
	static int cloudSize=1000;
	static ArrayList<Cloud> getclouds() {
		ArrayList<Cloud> cloudlist=new ArrayList<Cloud>();
		int min=1;
		int max=20;

		for(int i=0;i<cloudSize;i++){
			Cloud cloud=new Cloud(1024*(int)(Math.random()*max+min), (int)(Math.random()*max+min));
			cloudlist.add(cloud);
			System.out.println("Cloud : "+(i+1)+"    Storage Available "+cloud.avalilableStorage+"    RAM Available "+ cloud.availableRam);
		}

		
		return cloudlist;
	}
	
}


