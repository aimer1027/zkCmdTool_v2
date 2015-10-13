package org.kylin.zhang.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by win-7 on 2015/10/7.
 */
public class zkDao {

    private String zkIP ;
    private short zkPort ;
    private CuratorFramework zkClientHandler ;

    private boolean isConnect = false ;

    public zkDao(){}

    public zkDao(String zkIP, short zkPort){
        this.zkIP = zkIP ;
        this.zkPort = zkPort;
    }

    public void connectToServer(){
        this.zkClientHandler =
                CuratorFrameworkFactory.builder().connectString(zkIP+":"+zkPort)
                        .sessionTimeoutMs(30000)
                        .connectionTimeoutMs(30000)
                        .canBeReadOnly(false)
                        .retryPolicy( new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                        .defaultData(null).build() ;

        // create connection to the zk server
        zkClientHandler.start();

        if(zkClientHandler != null ) isConnect= true ;
    }

    public void closeConnect(){
        this.zkClientHandler.close();
    }

    // ?????????????????

    public void addPath( String pathName , byte []  upLoadData ){
        if( !isConnect ){
            // ????????????????
            // ?????????????????????????

            connectToServer();
        }

        try {
            // ??????????????????????????????
            zkClientHandler.create()
                    .creatingParentsIfNeeded()
                    .forPath(pathName , upLoadData) ;


        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("failed add path to " + pathName +" in zkDao class") ;
        }

    }

    // ??????????????? zk-server ??????????
    public void deletePath(String pathName ){
        if(!isConnect){
            connectToServer();
        }

        try{
        if( zkClientHandler.checkExists().forPath(pathName) == null){
             System.out.println("path " + pathName +" does not exists , failed to delete path ") ;
            return ;
        }

            zkClientHandler.delete().deletingChildrenIfNeeded().forPath(pathName) ;

        } catch(Exception e){
            e.printStackTrace();

            throw new RuntimeException("failed delete path on "+ pathName +" in zkDao class") ;
        }
    }


    // ??????????????? zk-server ???????????????????
    public void updateData(String pathName , byte [] upLoadData){

        if( !isConnect){
            connectToServer();
        }

        try{

            if( zkClientHandler.checkExists().forPath(pathName) == null){
                System.out.println("path " + pathName+  " does not exists , failed to update data on path") ;
                return ;
            }

            zkClientHandler.setData().forPath(pathName, upLoadData) ;

            /*
            deletePath(pathName);
            addPath(pathName, upLoadData);*/

        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("failed update path data on "+ pathName+" in zkDao class") ;
        }
    }

    // ?????????????????????
   public byte [] getDataByPath(String pathName ){

       byte [] data ;

       if(isConnect){
           connectToServer();
       }

       try {
           // 1. ????????????
           if (zkClientHandler.checkExists().forPath(pathName) == null) {
               System.out.println("path " + pathName+  " does not exists , failed to get data on path") ;
               return null ;
           }

            data  = zkClientHandler.getData().forPath(pathName) ;

       } catch (Exception e){
           e.printStackTrace();
           throw new RuntimeException("failed to get data on path " + pathName +" in zkDao class") ;
       }

       return data ;
   }


    //----------------------------------------

    //------------ getter and setter ----------

    public boolean isConnect() {
        return isConnect;
    }

    public void setIsConnect(boolean isConnect) {
        this.isConnect = isConnect;
    }

    public CuratorFramework getZkClientHandler() {
        return zkClientHandler;
    }

    public void setZkClientHandler(CuratorFramework zkClientHandler) {
        this.zkClientHandler = zkClientHandler;
    }

    public String getZkIP() {
        return zkIP;
    }

    public void setZkIP(String zkIP) {
        this.zkIP = zkIP;
    }

    public short getZkPort() {
        return zkPort;
    }

    public void setZkPort(short zkPort) {
        this.zkPort = zkPort;
    }
}
