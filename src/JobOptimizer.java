/**
 * Swarm  
 */

import java.util.ArrayList;
import java.util.Scanner;



public class JobOptimizer {
	/**
	 * @param args
	 */
	static ArrayList<Cloud> clouds=Clouds.getclouds();
	static int requestedStorage=11000;
	static int requestedRam=10;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of iterations to optimize search using PSO algoritm");
		Integer iterations=sc.nextInt();
		System.out.println("Enter the number of requests");
		Integer particles=sc.nextInt();
		PSOptimizer pso = new PSOptimizer();
		if (iterations==null || particles==null) {
			//System.out.println("swarm [#iterations] [#particles]");
			System.exit(-1);
		}
		sc.close();
		pso.execute(iterations, particles);
		System.exit(0);
	}
}
