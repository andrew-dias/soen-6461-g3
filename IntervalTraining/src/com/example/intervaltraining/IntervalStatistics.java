package com.example.intervaltraining;

public class IntervalStatistics {
	private int totalDistance;
	private int totalTime;
	private double topSpeed;
	private double avgSpeed;

	public IntervalStatistics(long intervalTime, int intervalDistance, int timeDecrement, int lapsCompleted){
		
		totalDistance = lapsCompleted * intervalDistance;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}
}
