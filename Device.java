

import java.util.LinkedList;
import java.util.Queue;

public class Device {
	private String name; // consists of a letter and an integer

	Queue<ProcessPCB> queue = new LinkedList<ProcessPCB>();

	public Queue<ProcessPCB> getQueue() {
		return queue;
	}

	public void setQueue(Queue<ProcessPCB> queue) {
		this.queue = queue;
	}

	public Device() {

	}

	public Device(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		String processes = "";
		for(ProcessPCB p : queue) {
			processes += "PID: " + p.getPID() + ", File Name: " + p.getFileName() + ", Read/Write: " + p.getReadWrite() + ", File Size: " + p.getFileSize() + "\n";
		}
		return processes;
	}

}
