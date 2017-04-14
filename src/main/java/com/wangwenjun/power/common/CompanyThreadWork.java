package com.wangwenjun.power.common;

public class CompanyThreadWork {
	public static void main(String[] args) {
		final Print print = new Print();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<1000; i++){
					print.printB();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<1000; i++){
					print.printC();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<1000; i++){
					print.printA();
				}
			}
		}).start();
	}
	static class Print{
		private volatile char shouldABC = 'A';
		public synchronized void printA(){
			while(shouldABC != 'A'){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("A");
			shouldABC = 'B';
			this.notifyAll();
		}
		public synchronized void printB(){
			while(shouldABC != 'B'){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("B");
			shouldABC = 'C';
			this.notifyAll();
		}
		public synchronized void printC(){
			while(shouldABC != 'C'){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("C");
			shouldABC = 'A';
			this.notifyAll();
		}
	}
}