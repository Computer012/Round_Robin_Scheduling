package com.sangmin.OS.operatingsystem.data;

public class ReadyQueue {
	private int front = 0;
	private int rear = 0;
	private int size = 0;
	private Process temp = null;
	private Process[] readyQueue = null;
	
	public ReadyQueue(int size) {
		/* Circular Queue는 size보다 하나 적은 수 만큼의 원소를 가질 수 있으므로 size만큼의 원소를 할당해주기 위해 size+1을 해준다. */  
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
