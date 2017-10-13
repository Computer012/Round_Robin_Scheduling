package com.sangmin.OS.operatingsystem.data;

public class Process {
	String processName = null;
	int burstTime = 0;
	int watingTime = 0;
	int dealingTime = 0;

	public Process(String processName, int burstTime) {
		this.processName = processName;
		this.burstTime = burstTime;
	}

	public int getWatingTime() {
		return watingTime;
	}

	public void setWatingTime(int watingTime) {
		this.watingTime = watingTime;
	}

	public int getDealingTime() {
		return dealingTime;
	}

	public void setDealingTime(int dealingTime) {
		this.dealingTime = dealingTime;
	}

	public String getProcessName() {
		return processName;
	}

	public int getBurstTime() {
		return burstTime;
	}
	
}
