package com.brucepang.demo;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.model.ApplicationModel;
import org.apache.dubbo.rpc.model.FrameworkModel;

/**
 * @Description: Dubbo中的扩展点机制SPI,
 *      1.为什么获取扩展点要通过Model来获取?
 *          - 做范围的隔离
 *      2.为什么能够通过不同的Model获取到ExtensionLoader?
 *          - 因为继承了ExtensionAccessor接口, 意味着每个Model都具备了访问ExtensionLoader的能力
 *          - 通过ExtensionAccessor接口作为每个Model获取ExtensionLoader的入口
 *      3.ExtensionLoader的作用?
 *          - 用于加载指定的扩展点
 *      4.ExtensionLoader的加载过程?
 *          - 1.通过ClassLoader加载指定目录下的扩展点配置文件到内存中
 *          - 2.解析配置文件, 获取到扩展点的名称和实现类
 *          - 3.加载过程中会判断扩展点之间是否会有依赖关系, 如果有会进行依赖注入(Injector)[]
 *           - 3.1 依赖注入有几种情况(相当于Dubbo持有4种IOC容器的实现方式)
 *              - 3.1.1 SPIExtensionInjector: 从SPI扩展点容器中获取ExtensionLoader
 *              - 3.1.2 AdaptiveExtensionInjector: 从自适应扩展点容器中获取ExtensionLoader
 *              - 3.1.3 SpringExtensionInjector: 从Spring容器中获取ExtensionLoader
 *              - 3.1.4 ScopeBeanExtensionInjector: 从ScopeBean容器(Dubbo自定义的Bean容器,相当于Spring的IOC容器)中获取ExtensionLoader
 *          - 4.ExtensionPostProcessor: 扩展点的后置处理器, 用于在扩展点加载完成后进行一些操作
 *          - 5.实例化策略 InstantiationStrategy: 用于实例化扩展点的策略, 在这一步才将上面的Class实例化为对象
 *
 *
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

        FrameworkModel.defaultModel().getExtensionLoader(Protocol.class).getSupportedExtensions().forEach(System.out::println);
    }
}
