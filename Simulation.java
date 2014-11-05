

import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) {
		final String PROGRAM_NAME = "Computer Simulation";

		System.out.println(PROGRAM_NAME);

		Computer computer = new Computer();

		Scanner keyboard = new Scanner(System.in);

		// System Running
		boolean running = true;
		int PIDCounter = 1; // Process ID. Increments by 1 for each process.
							// Does not repeat
							// Always increment after usage.
		while (running) {
			String userInput;
			int whichDevice = -1;

			System.out.print("Enter input: ");
			userInput = keyboard.nextLine();
			if (userInput.length() > 1 && userInput.length() < 3) {
				try {
					Integer.parseInt(userInput.substring(1, 2));
				} catch (NumberFormatException e) {
					System.out.println("Invalid input.");
				}
				whichDevice = Integer.parseInt(userInput.substring(1, 2));
				whichDevice--; // accounts for the zero element. assuming first
								// device is device 1.
			}

			if (userInput.equals("exit")) {
				System.out.println("Quitting Program.");
				running = false;
			}

			userInput = userInput.substring(0, 1);

			switch (userInput) {
			case "A": // arrival of process
				// create new PCB (process control block)
				ProcessPCB pcb = new ProcessPCB(PIDCounter++);
				computer.addProcess(pcb);
				// create a PCB for this process
				// generate a PID
				// enqueue the PCB into the Ready Queue
				break;
			case "S":
				System.out.println("Process " + computer.getCurrentProcess().getPID() + " is in the CPU.");
				System.out.println("r - Ready Queue");
				System.out.println("p - Printer Queues");
				System.out.println("d - Disk Queues");
				System.out.println("c - CDRW Queues");
				String whichQueue = keyboard.nextLine().substring(0, 1);
				switch (whichQueue) {
				case "r":
					System.out.print(computer.readyQueueToString());
					break;
				case "p":
					for(int i = 0; i < computer.getNPrinters(); i++) {
						System.out.println("Printer " + (i+1) + ":");
						System.out.print(computer.getPrinters().get(i).toString());
					}
					
					break;
				case "d":
					for(int i = 0; i < computer.getNDisks(); i++) {
						System.out.println("Disk " + (i+1) + ":");
						System.out.print(computer.getDisks().get(i).toString());
					}
					break;
				case "c":
					for (int i = 0; i < computer.getNCDRWs(); i++) {
						System.out.println("CDRW " + (i+1) + ":");
						System.out.print(computer.getCDRWs().get(i).toString());
					}
					break;
				default:
					System.out.println("Invalid input.");
				}
				// snapshot interrupt
				// wait for user input
				// get info from computer
				// and print out
				break;
			case "t":
				// terminate process in CPU
				if (computer.isOccupied() == true) {
					System.out.println("Process "
							+ computer.getCurrentProcess().getPID()
							+ " terminated.");
					computer.terminateCurrentProcess();
				} else {
					System.out.println("CPU is empty. No process to remove.");
				}

				// terminate process in cpu
				break;
			case "p":
				if (whichDevice < computer.getNPrinters()) {
					Printer p = computer.getPrinters().get(whichDevice);
					System.out.print("Please enter the file name: ");
					String fileName = keyboard.nextLine();
					System.out.print("Please enter the file size: ");
					int fileSize = 0;
					try {
						fileSize = Integer.parseInt(keyboard.nextLine());

					} catch (NumberFormatException e) {
						System.out.println("Invalid file size.");
					}
					ProcessPCB printerPCB = new ProcessPCB(PIDCounter++,
							fileName, 'w', fileSize);
					p.getQueue().add(printerPCB);
				} else {
					System.out.println("Invalid input.");
				}
				break;
			case "P":
				if (whichDevice < computer.getNPrinters()) {
					Printer p = computer.getPrinters().get(whichDevice);
					if (p.getQueue().isEmpty() == false) {
						computer.addProcess(p.getQueue().remove());
					} else {
						System.out.println("p" + (whichDevice + 1)
								+ " is empty.");
					}
				} else {
					System.out.println("Invalid input.");
				}
				break;
			case "d":
				if (whichDevice < computer.getNDisks()) {
					Disk d = computer.getDisks().get(whichDevice);
					System.out.print("Please enter the file name: ");
					String fileName = keyboard.nextLine();
					System.out
							.print("Please enter 'r' for read or 'w' for write: ");
					char readWrite = keyboard.nextLine().charAt(0);
					int fileSize = -1;
					if (readWrite == 'w') {
						System.out.print("Please enter the file size: ");
						try {
							fileSize = Integer.parseInt(keyboard.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Invalid file size.");
						}
					}
					ProcessPCB diskPCB = new ProcessPCB(PIDCounter++, fileName,
							readWrite, fileSize);
					d.getQueue().add(diskPCB);
				} else {
					System.out.println("Invalid input.");
				}
				break;
			case "D":
				if (whichDevice < computer.getNDisks()) {
					Disk d = computer.getDisks().get(whichDevice);
					if (d.getQueue().isEmpty() == false) {
						computer.addProcess(d.getQueue().remove());
					} else {
						System.out.println("d" + (whichDevice + 1)
								+ " is empty.");
					}
				} else {
					System.out.println("Invalid input.");
				}
				break;
			case "c":
				if (whichDevice < computer.getNCDRWs()) {
					CDRW c = computer.getCDRWs().get(whichDevice);
					System.out.print("Please enter the file name: ");
					String fileName = keyboard.nextLine();
					System.out
							.print("Please enter 'r' for read or 'w' for write: ");
					char readWrite = keyboard.nextLine().charAt(0);
					int fileSize = -1;
					if (readWrite == 'w') {
						System.out.print("Please eneter the file size: ");
						try {
							fileSize = Integer.parseInt(keyboard.nextLine());
						} catch (NumberFormatException e) {
							System.out.println("Invalid file size.");
						}
					}
					ProcessPCB cdrwPCB = new ProcessPCB(PIDCounter++, fileName,
							readWrite, fileSize);
					c.getQueue().add(cdrwPCB);
				} else {
					System.out.println("Invalid input.");
				}
				break;
			case "C":
				if (whichDevice < computer.getNCDRWs()) {
					CDRW c = computer.getCDRWs().get(whichDevice);
					if (c.getQueue().isEmpty() == false) {
						computer.addProcess(c.getQueue().remove());
					} else {
						System.out.println("c" + (whichDevice + 1)
								+ " is empty.");
					}
				} else {
					System.out.println("Invalid input.");
				}
				break;
			default:
				// System.out.println("Invalid input.");
			}
		}

		keyboard.close();

	}

}
