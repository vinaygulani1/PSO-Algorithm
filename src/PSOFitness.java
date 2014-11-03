import java.util.ArrayList;
/**
 * 
 */



public class PSOFitness {
	private int current = Integer.MAX_VALUE;
	private double best = Integer.MAX_VALUE;

	public int calculate(PSOParticle.Position p, PSOParticle.Velocity v) {
		int x = p.getx();
		int notFit=999999999;
		ArrayList<Cloud> cloudslist=new ArrayList<Cloud>();
		 cloudslist= JobOptimizer.clouds;
		Cloud cloud=cloudslist.get(x-1);
		int storageOptimumValue=cloud.getAvalilableStorage()-JobOptimizer.requestedStorage;
		int ramOptimumValue=cloud.getAvailableRam()-JobOptimizer.requestedRam;
		
		if(storageOptimumValue<0 || ramOptimumValue<0){
			current=notFit;
		}
		else{
			current=-(storageOptimumValue+ramOptimumValue);
		}
		/* Objective function 
		ArrayList<Cloud> cloudslist= Clouds.getclouds();
		
		current = y-(cloudslist.get((int)x).HD);
		//current = Math.sin(Math.pow(x, 2) + Math.pow(y, 2));*/	
	return current;
	}

	public boolean updateBestFitness() {
		if (Double.compare(current, best) < 0) {
			best = current;
			return true;
		}
		return false;
	}

	public double getBestFitness() {
		return best;
	}

	public double getCurrentFitness() {
		return current;
	}
}
