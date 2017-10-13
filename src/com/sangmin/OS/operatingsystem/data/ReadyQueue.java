package com.sangmin.OS.operatingsystem.data;

public class ReadyQueue {
	private int front = 0;
	private int rear = 0;
	private int size = 0;
	private Process temp = null;
	private Process[] readyQueue = null;
	
	public ReadyQueue(int size) {
		/* Circular Queue�� size���� �ϳ� ���� �� ��ŭ�� ���Ҹ� ���� �� �����Ƿ� size��ŭ�� ���Ҹ� �Ҵ����ֱ� ���� size+1�� ���ش�. */  
		this.size = size+1;
		readyQueue = new Process[this.size];
	}

	public boolean isEmpty() {
		return front == rear ? true : false;
	} 

	public boolean isFull() {
		return front == (rear + 1) % size ? true : false;
	}

	public void enQueue(Process process) {
		if (!isFull()) {
			readyQueue[rear] = process; 
			rear = (rear + 1) % size;
			return;
		}
		System.out.println("Queue is Full.");
	}

	public Process deQueue() {
		if (!isEmpty()) {
			temp = readyQueue[front];
			front = (front + 1) % size;
			return temp;
		}
		System.out.println("Queue is Empty.");
		return null;
	}
}
