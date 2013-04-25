package com.example.intervaltraining;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class IntervalStatistics implements Parcelable {
	// givens
	private Date date;
	private long initialTime;
	private long lapDistance;
	private long timeDecrement;
	private long lapsCompleted;

	// calculated
	private long totalDistance;
	private double totalTime;
	private double lastLapTime;
	private double topSpeed;
	private double initialSpeed;
	private double avgSpeed;

	public IntervalStatistics(Date date, long initialTime, long lapDistance,
			long timeDecrement, long lapsCompleted) {
		this.date = date;
		this.initialTime = initialTime;
		this.lapDistance = lapDistance;
		this.timeDecrement = timeDecrement;
		this.lapsCompleted = lapsCompleted;

		calculateStats();
	}

	public Date getDate() {
		return date;
	}

	public long getInitialTime() {
		return initialTime;
	}

	public long getLapDistance() {
		return lapDistance;
	}

	public long getTimeDecrement() {
		return timeDecrement;
	}

	public long getLapsCompleted() {
		return lapsCompleted;
	}

	public long getTotalDistance() {
		return totalDistance;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public double getLastLapTime() {
		return lastLapTime;
	}

	public double getTopSpeed() {
		return topSpeed;
	}

	public double getInitialSpeed() {
		return initialSpeed;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}

	// convert m/ms -> km/h
	private double convert(double distance, double time) {
		return distance / time * 1000 * 3.6;
	}

	private double calculateLastLapTime() {
		double time = initialTime;

		for (int i = 1; i < lapsCompleted / 2.0; i++) {
			time = time * ((100 - timeDecrement) / 100.0);
		}

		return time;
	}

	private double calculateTotalTime() {
		double timeTotal = 0;
		double lapTime = initialTime;

		for (int i = 1; i <= lapsCompleted; i++) {
			if ((i != 1) && (i % 2 == 1)) {
				lapTime = lapTime * ((100 - timeDecrement) / 100.0);
			}
			timeTotal += lapTime;
		}
		return timeTotal;
	}

	private void calculateStats() {
		totalDistance = lapsCompleted * lapDistance;
		totalTime = calculateTotalTime();
		lastLapTime = calculateLastLapTime();
		topSpeed = convert(lapDistance, lastLapTime);
		initialSpeed = convert(lapDistance, initialTime);
		avgSpeed = convert(totalDistance, totalTime);
	}

	/**
	 * 
	 * Constructor to use when re-constructing object from a parcel
	 * 
	 * @param in
	 *            a parcel from which to read this object
	 */
	public IntervalStatistics(Parcel in) {
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(date.getTime());
		dest.writeLong(initialTime);
		dest.writeLong(lapDistance);
		dest.writeLong(timeDecrement);
		dest.writeLong(lapsCompleted);
		dest.writeLong(totalDistance);
		dest.writeDouble(totalTime);
		dest.writeDouble(lastLapTime);
		dest.writeDouble(topSpeed);
		dest.writeDouble(initialSpeed);
		dest.writeDouble(avgSpeed);
	}

	private void readFromParcel(Parcel in) {
		date = new Date(in.readLong());
		initialTime = in.readLong();
		lapDistance = in.readLong();
		timeDecrement = in.readLong();
		lapsCompleted = in.readLong();
		totalDistance = in.readLong();
		totalTime = in.readDouble();
		lastLapTime = in.readDouble();
		topSpeed = in.readDouble();
		initialSpeed = in.readDouble();
		avgSpeed = in.readDouble();
	}

	public static final Parcelable.Creator<IntervalStatistics> CREATOR = new Parcelable.Creator<IntervalStatistics>() {
		public IntervalStatistics createFromParcel(Parcel in) {
			return new IntervalStatistics(in);
		}

		public IntervalStatistics[] newArray(int size) {
			return new IntervalStatistics[size];
		}
	};

}
