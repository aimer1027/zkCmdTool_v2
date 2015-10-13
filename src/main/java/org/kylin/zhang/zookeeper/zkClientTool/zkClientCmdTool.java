package org.kylin.zhang.zookeeper.zkClientTool;

import org.kylin.zhang.beans.ServerInClusterInfo;
import org.kylin.zhang.util.JsonPacker;
import org.kylin.zhang.zookeeper.zkDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by win-7 on 2015/10/7.
 */
public class zkClientCmdTool {
    private final String pathName ="/Aimer/listen" ;

    private zkDao zkClientHandler ;

    public zkClientCmdTool(String zkIP, short zkPort){
        zkClientHandler = new zkDao(zkIP,zkPort) ;

        zkClientHandler.connectToServer();
    }

    public List<ServerInClusterInfo> getInfoFromPath( String serverName ){

          String jsonInfoString = new String (zkClientHandler.getDataByPath(pathName+'/'+serverName)) ;


   //     System.out.println(jsonInfoString) ;

           String [] jsonStringArray  = jsonInfoString.split("&") ;

            List<ServerInClusterInfo> clusterInfosList = new ArrayList<ServerInClusterInfo>() ;

            for( int i = 0 ; i < jsonStringArray.length -1 ; i++ ){
                ServerInClusterInfo serverInClusterInfo = (ServerInClusterInfo)JsonPacker.getJsonObject(jsonStringArray[i] , ServerInClusterInfo.class) ;

                clusterInfosList.add(serverInClusterInfo) ;

            }
        return clusterInfosList ;
    }


    public void printServerInfo( String serverName ) {
        List<ServerInClusterInfo> serverInClusterInfosList = getInfoFromPath(serverName);

        if (serverInClusterInfosList.size() == 0){
            System.out.println("not receive any file yet") ;
            return;
        }

        long currentTimer = new Date().getTime();
        System.out.println("Node  Name  || FileName  \t|| Total Length || Process \t|| Time Past(s)  ");
        for (ServerInClusterInfo serverInfo : serverInClusterInfosList) {
            System.out.print(serverInfo.getSenderName() + "\t   ");
            System.out.print("(" + serverInfo.getSenderName() + ")" + serverInfo.getFileName() + "\t  ");
            System.out.print(serverInfo.getFileTotalLength() + "\t  ");
            System.out.printf("%3.1f \t\t", serverInfo.getProcess());
            //  System.out.print(serverInfo.getProcess()+"   \t\t") ;
            System.out.println(serverInfo.getSecPast() / 1000 );
        }
    }
        public void removePathFromZkServer(String serverName) {
        System.out.println("now delete path from zk-server") ;

        String cmdPrefix = "/Aimer/listen/" +serverName ;

        this.zkClientHandler.deletePath(cmdPrefix);


    }
    public void reset(){
        zkClientHandler.deletePath("/Aimer");

        System.out.println("[remote zookeeper server path is cleared]") ;
    }


    public static  void startZkCmdTool(   ) {

        zkClientCmdTool zkClientCmdTool = new zkClientCmdTool("127.0.0.1", (short) 2181);

        while (true) {

            // 1. �������ӡ��ʹ����Ϣ

            System.out.println("input command \n" +
                    "1. [list_info] [serverName] -----> list server file receive state \n" +
                    "2. [kill] [serverName]      -----> remove server  path and stop server\n" +
                    "3. [reset]-----------------------> delete zk-server path /Aimer  \n"   +
                    "4. [exit] --------------------------> exit command client\n");
            Scanner input = new Scanner(System.in);

            while (input.hasNext()) {

                String line = input.nextLine();

                String[] inputMsg = line.split(" ");

                System.out.println("==============================================");

                if (inputMsg[0].equals("list_info")) {
                    zkClientCmdTool.getInfoFromPath(inputMsg[1]);
                    zkClientCmdTool.printServerInfo(inputMsg[1]);
                } else if (inputMsg[0].equals("kill")) {
                    zkClientCmdTool.removePathFromZkServer(inputMsg[1]);

                } else if(inputMsg[0].equals("exit")){
                    System.exit(0);
                }
                else if(inputMsg[0].equals("reset")){
                    zkClientCmdTool.reset();
                }
                else{
                    System.out.println("error command ; input again") ;
                }

                System.out.println("==============================================");
                System.out.println("--------------------------------------------") ;

                System.out.println("input command \n" +
                        "1. [list_info] [serverName] -----> list server file receive state \n" +
                        "2. [kill] [serverName]      -----> remove server  path and stop server\n" +
                        "3. [reset]-----------------------> delete zk-server path /Aimer  \n"   +
                        "4. [exit] --------------------------> exit command client\n");
                System.out.println("--------------------------------------------") ;
            }


        }
    }
    }

