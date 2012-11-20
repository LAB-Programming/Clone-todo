package net.clonecomputers.lab.todo;

import java.util.Timer;

public class Todo extends Thread {

	private final Timer timer;
	
	private boolean guiOpen;
	
	private static Todo running;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(running == null){
			running = new Todo();
			running.start();
		}
		running.joinOpenGui();
	}
	
	private Todo() {
		timer = new Timer("todo-timer-thread");
	}

	@Override
	public void run() {
		
	}
	
	private void joinOpenGui() {
		guiOpen = true;
		while(guiOpen) {
			this.yield();
		}
	}

}
