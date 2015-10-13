package org.kylin.zhang.beans;

/**
 * Created by win-7 on 2015/10/6.
 *
 * ������������� netty-server ��������ʱ�ļ�����Ԫ�ص�
 *
 * HashTable<String, FileInfo>
 *
 */
public class FileInfo {

    private String senderName ;  // ��Ϣ�����ߵ� ServerName
    private String fileName ;    // �ļ�����
    private int    fileTotalLength ;    // �ļ����ܳ���
    private int    receivedTotalLength ;    // �Ѿ����յ����ļ�����(���յ�����Ϣ�����ݳ���  + �����ļ�����)
    private long    lastUpdateTimer ;       // ���һ�ν��յ��ļ���ʱ���

    public FileInfo() {
    }


    public FileInfo(String fileName, int fileTotalLength, long lastUpdateTimer, int receivedTotalLength, String senderName) {
        this.fileName = fileName;
        this.fileTotalLength = fileTotalLength;
        this.lastUpdateTimer = lastUpdateTimer;
        this.receivedTotalLength = receivedTotalLength;
        this.senderName = senderName;
    }

    // ------------- getter and setter -----------------

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileTotalLength() {
        return fileTotalLength;
    }

    public void setFileTotalLength(int fileTotalLength) {
        this.fileTotalLength = fileTotalLength;
    }

    public long getLastUpdateTimer() {
        return lastUpdateTimer;
    }

    public void setLastUpdateTimer(long lastUpdateTimer) {
        this.lastUpdateTimer = lastUpdateTimer;
    }

    public int getReceivedTotalLength() {
        return receivedTotalLength;
    }

    public void setReceivedTotalLength(int receivedTotalLength) {
        this.receivedTotalLength = receivedTotalLength;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
