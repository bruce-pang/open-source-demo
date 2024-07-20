1-InstantiationStrategy执行时要做的事情
 - 1.1-拿到已经被ClassLoader加载到内存的Class对象
 - 1.2-InstantiationStrategy根据Class对象的信息，选择不同的策略实现
 - 1.3-策略实现不同, 则使用不同的Injector拿到ExtensionLoader,然后注入


Dubbo扩展点编写思路:
 1. 需要扩展点的统一访问接口(为外部访问提供统一的入口) -> ExtensionAccessor
 2. 针对扩展点有访问的作用域范围(框架级Framework,应用级Application等) ,为了统一管理, 提供了一个管理类 -> ExtensionDirector(存储了Scope以及对应的扩展点)
3. 在ExtensionDirector中, 维护了一个Map<Class<?>, ExtensionLoader<?>>的结构, 用于存储扩展点的加载器