import java.io.Serializable;

public class FileTransfer implements Serializable {
    
    private String fileName;
    private byte[] fileData;

    public FileTransfer(String fileName, byte[] fileData) {
        this.fileName = fileName;
        this.fileData = fileData;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFileData() {
        return fileData;
    }
}
