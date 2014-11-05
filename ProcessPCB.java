

public class ProcessPCB {

	private int PID;
	private int fileSize;
	private char readWrite;
	private String fileName;

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public char getReadWrite() {
		return readWrite;
	}

	public void setReadWrite(char readWrite) {
		this.readWrite = readWrite;
	}

	public ProcessPCB() {
		PID = -1;
	}

	public ProcessPCB(int PID) {
		this.PID = PID;
	}
	
	public ProcessPCB(int PID, String fileName, char readWrite, int fileSize) {
		this.PID = PID;
		this.fileName = fileName;
		this.readWrite = readWrite;
		this.fileSize = fileSize;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
