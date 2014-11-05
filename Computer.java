

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Computer {

	private int nPrinters;
	private int nDisks;
	private int nCDRW;

	private ProcessPCB currentProcess;

	Queue<ProcessPCB> readyQueue = new LinkedList<ProcessPCB>();

	private ArrayList<Printer> printers = new ArrayList<Printer>();
	private ArrayList<Disk> disks = new ArrayList<Disk>();
	private ArrayList<CDRW> cdrws = new ArrayList<CDRW>();

	private Scanner keyboard;

	public Computer() {
		init();
		getUserData();
		createPrinterQueue();
		createDiskQueue();
		createCDRWQueue();
	}

	public Queue<ProcessPCB> getReadyQueue() {
		return readyQueue;
	}

	public void setReadyQueue(Queue<ProcessPCB> readyQueue) {
		this.readyQueue = readyQueue;
	}

	public void init() {
		keyboard = new Scanner(System.in);
		nPrinters = 0;
		nDisks = 0;
		nCDRW = 0;
		currentProcess = null;
	}

	private void createPrinterQueue() {
		for (int i = 1; i <= nPrinters; i++) {
			printers.add(new Printer("p" + i));
		}
	}

	private void createDiskQueue() {
		for (int i = 1; i <= nDisks; i++) {
			disks.add(new Disk("d" + i));
		}
	}

	private void createCDRWQueue() {
		for (int i = 1; i <= nCDRW; i++) {
			cdrws.add(new CDRW("c" + i));
		}
	}

	public void getUserData() {
		do {
			System.out.print("Enter the number of printers: ");
			while (!keyboard.hasNextInt()) {
				System.out
						.print("Invalid input. Enter the number of printers: ");
				keyboard.next();
			}
			nPrinters = keyboard.nextInt();
		} while (nPrinters < 0);

		do {
			System.out.print("Enter the number of disks: ");
			while (!keyboard.hasNextInt()) {
				System.out.print("Invalid input. Enter the number of disks: ");
				keyboard.next();
			}
			nDisks = keyboard.nextInt();
		} while (nDisks < 0);

		do {
			System.out.print("Enter the number of CDRWs: ");
			while (!keyboard.hasNextInt()) {
				System.out.print("Invalid input. Enter the number of CDRWs: ");
				keyboard.next();
			}
			nCDRW = keyboard.nextInt();
		} while (nCDRW < 0);
	}

	public void setProcess(ProcessPCB pcb) {
		currentProcess = pcb;
	}

	public void terminateCurrentProcess() {
		if(readyQueue.isEmpty() == false) {
			currentProcess = readyQueue.remove();
		} else {
			currentProcess = null;
			System.out.println("Empty ready queue.");
		}
		
	}

	public int getNPrinters() {
		return nPrinters;
	}

	public void setNPrinters(int nPrinters) {
		this.nPrinters = nPrinters;
	}

	public int getNDisks() {
		return nDisks;
	}

	public void setnDisks(int nDisks) {
		this.nDisks = nDisks;
	}

	public int getNCDRWs() {
		return nCDRW;
	}

	public void setnCDRW(int nCDRW) {
		this.nCDRW = nCDRW;
	}

	public boolean isOccupied() {
		if(currentProcess == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public ProcessPCB getCurrentProcess() {
		return currentProcess;
	}

	public void setCurrentProcess(ProcessPCB currentProcess) {
		this.currentProcess = currentProcess;
	}

	public ArrayList<Printer> getPrinters() {
		return printers;
	}

	public void setPrinters(ArrayList<Printer> printerQueue) {
		this.printers = printerQueue;
	}

	public ArrayList<Disk> getDisks() {
		return disks;
	}

	public void setDisks(ArrayList<Disk> diskQueue) {
		this.disks = diskQueue;
	}

	public ArrayList<CDRW> getCDRWs() {
		return cdrws;
	}

	public void setCdrws(ArrayList<CDRW> cdrwQueue) {
		this.cdrws = cdrwQueue;
	}

	public void swapProcess(ProcessPCB newProcess) {
		if (currentProcess != null) {
			currentProcess = newProcess;
		} else {
			readyQueue.add(currentProcess);
			currentProcess = newProcess;
		}
	}

	public void addProcess(ProcessPCB newProcess) {
		if (currentProcess == null) {
			currentProcess = newProcess;
			System.out.println("Process " + newProcess.getPID()
					+ " has been added to the CPU.");
		} else {
			readyQueue.add(newProcess);
			System.out.println("CPU is occupied.");
			System.out.println("Process " + newProcess.getPID()
					+ " has been added to the Ready Queue.");
		}
	}

	public String readyQueueToString() {
		String processes = "";
		for(ProcessPCB p: readyQueue) {
			processes += "Process PID: " + p.getPID() + "\n";
		}
		return processes;
	}
}
