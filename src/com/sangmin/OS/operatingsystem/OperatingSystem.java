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
		// 1. ������ ���μ��� ���� �Է� �ޱ�
		System.out.print("Process ���� �Է� : ");
		nums = sc.nextInt();

		// 2. �� ���μ����� CPU ����Ʈ �ð� �Է� �ޱ�
		burst = new int[nums];
		System.out.print("Burst Time �Է�(" + nums + "��) : ");
		for (int i = 0; i < nums; i++)
			burst[i] = sc.nextInt();

		// 3. Round-Robin Scheduling�� �ð� �Ҵ緮 �Է� �ޱ�
		System.out.print("Time Quantum �Է� : ");
		timeQuantum = sc.nextInt();
	}

	private void processReady() {
		// 4. �غ� �Ϸ� ť�� Process���� �����Ͽ� �ֱ�
		readyQueue = new CircularQueue(nums);
		finishQueue = new CircularQueue(nums);
		for (int i = 0; i < nums; i++) {
			readyQueue.enQueue(new Process("P" + (i + 1), burst[i]));
		}
	}

	private void RoundRobin() {
		Process process = null;
		int time = 0;

		// 5. Round-Robin Scheduling �˰��� ����
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

		// 6. ���μ��� ������ �ֿܼ� ���
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
