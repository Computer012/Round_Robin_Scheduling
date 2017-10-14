package com.sangmin.OS.operatingsystem;

import java.util.Scanner;

import com.sangmin.OS.operatingsystem.data.Process;
import com.sangmin.OS.operatingsystem.data.CircularQueue;

public class OperatingSystem {

	int nums = 0;
	int burst[] = null;
	int timeQuantum = 0;
	int contextSwitch = 0;

	int totalWatingTime = 0;
	int totalDealingTime = 0;

	CircularQueue readyQueue = null;
	CircularQueue finishQueue = null;
	Scanner sc = null;

	public OperatingSystem() {
		sc = new Scanner(System.in);

		inputData();
		processReady();
		RoundRobin();
		showProcessInfo();
	}

	private void inputData() {
		// 1. 생성할 프로세스 개수 입력 받기
		System.out.print("Process 개수 입력 : ");
		nums = sc.nextInt();

		// 2. 각 프로세스의 CPU 버스트 시간 입력 받기
		burst = new int[nums];
		System.out.print("Burst Time 입력(" + nums + "개) : ");
		for (int i = 0; i < nums; i++)
			burst[i] = sc.nextInt();

		// 3. Round-Robin Scheduling의 시간 할당량 입력 받기
		System.out.print("Time Quantum 입력 : ");
		timeQuantum = sc.nextInt();
	}

	private void processReady() {
		// 4. 준비 완료 큐에 Process들을 생성하여 넣기
		readyQueue = new CircularQueue(nums);
		finishQueue = new CircularQueue(nums);
		for (int i = 0; i < nums; i++) {
			readyQueue.enQueue(new Process("P" + (i + 1), burst[i]));
		}
	}

	private void RoundRobin() {
		Process process = null;
		int time = 0;

		// 5. Round-Robin Scheduling 알고리즘 수행
		while (!readyQueue.isEmpty()) {
			process = readyQueue.deQueue();
			contextSwitch++;
			process.setWatingTime(process.getWatingTime() + (time - process.getLastFinishTime()));

			int interrupt = 0;
			while (true) {
				if (process.getRemainigTime() == 0) {
					process.setDealingTime(process.getBurstTime() + process.getWatingTime());
					totalWatingTime += process.getWatingTime();
					totalDealingTime += process.getDealingTime();
					finishQueue.enQueue(process);
					break;
				}

				if (interrupt++ == timeQuantum) {
					process.setLastFinishTime(time);
					readyQueue.enQueue(process);
					break;
				}

				process.setRemainigTime(process.getRemainigTime() - 1);
				time++;
			}
		}
	}

	private void showProcessInfo() {
		Process process = null;
		double avgWatingTime = totalWatingTime / (double) nums;
		double avgDealingTime = totalDealingTime / (double) nums;

		// 6. 프로세스 정보를 콘솔에 출력
		System.out.println("\nProcess     BurstTime  WaitingTime  DealingTime");
		for (int i = 0; i < nums; i++) {
			process = finishQueue.deQueue();
			System.out.println("  " + process.getProcessName() + "\t:\t" + process.getBurstTime() + "\t" + "    "
					+ process.getWatingTime() + "\t\t" + process.getDealingTime());
		}
		System.out.println(
				"Average Waiting Time : " + avgWatingTime + "\nAverage Dealing Time : " + avgDealingTime);
	}
}
