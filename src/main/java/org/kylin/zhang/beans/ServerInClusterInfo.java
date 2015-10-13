package org.kylin.zhang.beans;

/**
 * Created by win-7 on 2015/10/6.
 *
 * �������󽫻ᱻ json ����֮���ϴ��� zk-server ������
 * 1. �ļ�����������
 * 2. �ļ�����
 * 3. �ļ����ܳ���
 * 4. ���ͽ���
 * 5. �ӽ��յ����ļ�������Ϣ��ʼ��������һ���ļ����ݵĸ����ܹ����˶��
 *
 */
public class ServerInClusterInfo {
    private String senderName ;
    private String fileName ;
    private int    fileTotalLength ;
    private double process ;
    private long     secondsPast ;

    public ServerInClusterInfo() {
    }

    public ServerInClusterInfo(String fileName, int fileTotalLength, double process, long secPast, String senderName) {
        this.fileName = fileName;
        this.fileTotalLength = fileTotalLength;
        this.process = process;
        this.secondsPast = secPast;
        this.senderName = senderName;
    }

    // ------------ getter and setter -----------------

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

    public double getProcess() {
        return process;
    }

    public void setProcess(double process) {
        this.process = process;
    }

    public long getSecPast() {
        return secondsPast;
    }

    public void setSecPast(int secPast) {
        this.secondsPast = secPast;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
