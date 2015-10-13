package org.kylin.zhang;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.kylin.zhang.zookeeper.zkClientTool.zkClientCmdTool;

/**
 * Created by win-7 on 2015/10/12.
 */
public class Main {
    public static void main (String []args) {

            zkClientCmdTool.startZkCmdTool();
    }

    public static boolean  isZkServerAvailable(){
        String zkIp ="127.0.0.1" ;
        short zkPort = (short)2181 ;
        CuratorFramework connectTester =   CuratorFrameworkFactory.builder().connectString(zkIp+":"+zkPort)
                .sessionTimeoutMs(90000)
                .connectionTimeoutMs(90000)
                .canBeReadOnly(false)
                .retryPolicy( new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .defaultData(null).build() ;

        // create connection to the zk server
        connectTester.start();

        return   connectTester.getZookeeperClient().isConnected() ;
    }

    }
