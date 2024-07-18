package com.brucepang.demo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.model.ApplicationModel;

/**
 * @Description: Dubbo中的扩展点机制SPI
 * @Author: BrucePang
 * @Email: brucepang0618@gmail.com
 * @Date: 2024/7/18 16:56
 */
public class SpiDemo {
    public static void main(String[] args) {
        // 获取LoadBalance的ExtensionLoader
        ExtensionLoader<LoadBalance> balanceExtensionLoader = ApplicationModel.defaultModel()
                .getExtensionLoader(LoadBalance.class) // 获取筛选范围
                ;
        // 获取支持的所有关于LoadBalance的扩展点
        System.out.println(balanceExtensionLoader.getSupportedExtensions());

        // 获取指定的LoadBalance的扩展点
        LoadBalance loadBalance = ApplicationModel.defaultModel()
                .getExtensionLoader(LoadBalance.class) // 获取筛选范围
                .getExtension("brucerandom"); // 获取指定的扩展点
        System.out.println(loadBalance);

        // 获取Protocol的ExtensionLoader
        Protocol dubbo = ApplicationModel.defaultModel()
                .getExtensionLoader(Protocol.class) // 获取筛选范围
                .getExtension("dubbo"); // 获取指定的协议扩展点
        System.out.println(dubbo);
    }
}
