/**
 * 
 */


import java.util.Random;


public class PSOParticle {
	private PSOFitness fitness = new PSOFitness();
	private Position position = new Position();
	private Position best = new Position();
	private Velocity velocity = new Velocity();
	private static Random random = new Random();
	static int min = 1;
	static int max = Clouds.cloudSize;
	static int minVel = -50;
	static int maxVel = 50;

	public static class Position {
		private int x = 0;
		private int y = 0;

		public Position() {
			this.x = random.nextInt();
			this.y = random.nextInt();
		}

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void setx(int x) {
			this.x = x;
		}

		public void sety(int y) {
			this.y = y;
		}

		public void setxy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getx() {
			return x;
		}

		public int gety() {
			return y;
		}
	}

	public static class Velocity {
		private int x;
		private int y;

		public Velocity() {
			this.x = random.nextInt();
			this.y = random.nextInt();
		}

		public Velocity(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void setx(int x) {
			this.x = x;
		}

		public void sety(int y) {
			this.y = y;
		}

		public void setxy(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double getx() {
			return x;
		}

		public double gety() {
			return y;
		}
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public Velocity setVelocity(int x, int y) {
		velocity.setxy(x, y);
		return velocity;
	}

	/**
	 * TODO: Fix update velocity
	 */
	public Velocity updateVelocity(int globalx, int globaly) {
		double phi1, phi2, inertia;
		
		phi1 = random.nextInt();
		phi2 = random.nextInt();
		inertia = random.nextInt();
		velocity.x = (int)(inertia*velocity.x + (0.9*phi1*(best.x - position.x)) + (1.1*phi2*(globalx - position.x)));
		velocity.x = (velocity.x > maxVel)? maxVel : velocity.x;
		velocity.x = (velocity.x < minVel)? minVel : velocity.x;
		
	/*	phi1 = random.nextDouble();
		phi2 = random.nextDouble();
		inertia = random.nextDouble();
		velocity.y = inertia*velocity.y + (0.9*phi1*(best.y - position.y)) + (1.1*phi2*(globaly - position.y));
		velocity.y = (velocity.y > max)? max : velocity.y;
		velocity.y = (velocity.y < min)? min : velocity.y;*/
		return velocity;
	}

	public Position getPosition() {
		return position;
	}

	public Position setPosition(int x, int y) {
		position.setxy(x, y);
		return position;
	}

	/**
	 * TODO: Fix update position
	 */
	public Position updatePosition() {
		position.x = position.x + velocity.x;
		position.x = (position.x > max)? max : position.x;
		position.x = (position.x < min)? min : position.x;
		
/*		position.y = position.y + velocity.y;
		position.y = (position.y > max)? max : position.y;
		position.y = (position.y < min)? min : position.y;*/
		return position;
	}

	public PSOParticle(int x,  int k) {
		position.setx(x);
		velocity.setx(k);
	}

	public double calculateFitness() {
		return fitness.calculate(position, velocity);
	}

	public boolean updateBestFitness() {
		if (fitness.updateBestFitness()) {
			best.x = position.x;
			best.y = position.y;
			return true;
		}
		return false;
	}

	public double getFitness() {
		return fitness.getCurrentFitness();
	}

	public double getBestFitness() {
		return fitness.getBestFitness();
	}

	public Position getBestPosition() {
		return best;
	}

	public void updateInfo(int globalx, int globaly) {
		updateVelocity(globalx, globaly);
		updatePosition();
	}
}
