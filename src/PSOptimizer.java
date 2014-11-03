/**
 * 
 */


import java.util.ArrayList;
import java.util.Random;

public class PSOptimizer {
	ArrayList<PSOParticle> particles = new ArrayList<PSOParticle>();
	static Integer globalBestStabilizedCounter = 0; 
	static Integer globalBest = null;
	Random random = new Random();

	static double min = 1; 
	static double max = Clouds.cloudSize;

	public void execute(int maxIterations, int numberOfParticles) {
		boolean stop = false; 
		
		System.out.println("Initializing all particles ");
		initializeParticles(numberOfParticles);
		int iteration = 0;
		System.out.println("Iterations loop start here");
		do {
				//calculating all particles fitnesses
			calculateAllFitness();
			
				//Updating all particle's best
			updateParticlesBest();
			
				//Updating global best value
			stop = updateGlobalBest();
			
				//moving all particles
			moveParticles();
				
				//Display the progress
			showProgress(iteration, numberOfParticles);
			
			/* updateGlobalBest return true 
			 * if Best value has been stabilized*/
			if (stop) {
				System.out.println(" *** Stop! Best fitness has been stabilized.");
				iteration = maxIterations;
			}
		} while (iteration++ < maxIterations);
		showFinalResult();
	}

	private void showFinalResult() {
		
		System.out.println("Final Best Cloud No. Selected : "+ particles.get(globalBest).getBestPosition().getx()+ " and  it's best value is " + particles.get(globalBest).getBestFitness() );
		/*System.out.println("( " + particles.get(globalBest).getPosition().getx() + " ^2) " +
		           "( " + particles.get(globalBest).getPosition().gety() + " ^2) " +
		           " = " + particles.get(globalBest).getBestFitness());*/
		
	}

	private void initializeParticles(Integer particlesListSize) {
		int posX,  velX; 
		for (double i = 0; i < particlesListSize; i++) {
			int k=(int)Math.random();
			//double k = random.nextInt();
			
			posX = (int)(Math.random()*max+min);
			System.out.println("Position  : "+posX);
			
			k=(int)Math.random();
			velX = (int)(Math.random()*max+min);
			System.out.println("Velocity : "+velX);
			
			particles.add(new PSOParticle(posX, velX));
		}
	}

	private void moveParticles() {
		for (int i = 0; i < particles.size(); i++) {
					particles.get(i).updateInfo(
					particles.get(globalBest).getPosition().getx(),
					particles.get(globalBest).getPosition().gety());
		}
	}

	private boolean updateGlobalBest() {		
		if (globalBest == null)
			globalBest = 0;
		
		double globalB = globalBest;
		
		for (int i = 0; i < particles.size(); i++)
			if (Double.compare(particles.get(i).getBestFitness(),
					particles.get(globalBest).getBestFitness()) < 0)
				globalBest = i;
		
		/* Stop iterations by returning true 
		 * when global best fitness doesn't change anymore */
		if (Double.compare(globalB, globalBest) == 0) {
			globalBestStabilizedCounter++;
			if (globalBestStabilizedCounter >= 500)
				return true;
			else
				return false;
		}
			
		globalBestStabilizedCounter=0;
		return false;		
	}

	private void updateParticlesBest() {
		double currentFitness;
		for (int i = 0; i < particles.size(); i++) {
			currentFitness = particles.get(i).getBestFitness();
			if (particles.get(i).updateBestFitness()) {
				System.out.println("Best fitness of particle " + i
						+ " has been updated from " + currentFitness
						+ " to " + particles.get(i).getFitness());
			}
		}
	}

	private void calculateAllFitness() {
		for (int i = 0; i < particles.size(); i++)
			particles.get(i).calculateFitness();
	}

	private void showProgress(int iteration, int numberOfParticles) {
		System.out.println("Global Best Cloud  : " +  
				particles.get(globalBest).getBestPosition().getx() + "  Global best fitness value" +
 				particles.get(globalBest).getBestFitness());

		for (int i=0; i<numberOfParticles; i++)
			System.out.println("Iteration  No. : "+iteration + 
								"     Request No. " + i + " " +
								"     best fit cloud no. " + particles.get(i).getBestPosition().getx()+ " best fit value "+particles.get(i).getBestFitness()
								+"     current cloud no. " + particles.get(i).getPosition().getx()+ " best fit value "+particles.get(i).getFitness());
	}
}