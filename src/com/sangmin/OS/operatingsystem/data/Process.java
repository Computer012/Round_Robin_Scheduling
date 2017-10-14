package com.sangmin.OS.operatingsystem.data;

public class Process {
	private String processName = null;
	private int burstTime = 0;
	private int remainigTime = 0;
	private int lastFinishTime = 0;
	private int watingTime = 0;
	private int dealingTime = 0;

	public Process(String processName, int burstTime) {
		this.processName = processName;
		this.burstTime = burstTime;
		remainigTime = burstTime;
	}
	
	public String getProcessName() {
		return processName;
	}
	
	public int getBurstTime() {
		return burstTime;
	}
	
	public int getRemainigTime() {
		return remainigTime;
	}

	public void setRemainigTime(int remainigTime) {
		this.remainigTime = remainigTime;
	}

	public int getLastFinishTime() {
		return lastFinishTime;
	}
	
	public void setLastFinishTime(int lastFinishTime) {
		this.lastFinishTime = lastFinishTime;
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
	
}
