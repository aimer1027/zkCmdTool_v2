package org.kylin.zhang.beans;

/**
 * Created by win-7 on 2015/10/6.
 *
 * FileData ???§Ù?????????????§Õ???????????????????
 *
 * 1. ?????????? ??        senderName    String
 * 2. ???????????:        fileName      String
 * 3. ??????????????:      fileTotalLen int
 * 4. ??¦Ç?????????????????:    fileLength  int
 * 5. ????????????  fileContent byte []
 * 6. ?????????????  sendTimer Date
 */
public class FileData {

    private String senderName ;
    private String fileName ;
    private int   fileTotalLen ;
    private int   fileLenght ;
    private byte [] dataContent ;
    private long  sendTimer ;


    public FileData() {
    }

    public FileData(byte[] dataContent, int fileLenght, String fileName, int fileTotalLen, String senderName, long sendTimer) {
        this.dataContent = dataContent;
        this.fileLenght = fileLenght;
        this.fileName = fileName;
        this.fileTotalLen = fileTotalLen;
        this.senderName = senderName;
        this.sendTimer = sendTimer;
    }


    //------------ getter and setter

    public byte[] getDataContent() {
        return dataContent;
    }

    public void setDataContent(byte[] dataContent) {
        this.dataContent = dataContent;
    }

    public int getFileLenght() {
        return fileLenght;
    }

    public void setFileLenght(int fileLenght) {
        this.fileLenght = fileLenght;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileTotalLen() {
        return fileTotalLen;
    }

    public void setFileTotalLen(int fileTotalLen) {
        this.fileTotalLen = fileTotalLen;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public long getSendTimer() {
        return sendTimer;
    }

    public void setSendTimer(long sendTimer) {
        this.sendTimer = sendTimer;
    }
}
