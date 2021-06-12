# Gradle - Android 应用程序构建技术

[Android Studio 配置编译脚本](https://developer.android.com/studio/build?hl=zh_cn)

[Gradle用户手册](https://docs.gradle.org/current/userguide/userguide.html)

[Gradle用户手册 | 开发自定义Gradle插件](https://docs.gradle.org/current/userguide/custom_plugins.html)





- Gradle工程结构
  - 初步认识基于Gradle的工程结构,理解Gradle是什么

- Gradle的使用方法
  - Gradle的安装、执行、升级
- Gradle的开发语言
  - 掌握Groovy的基本语法和进阶特性
- Gradle构建技术
  - 学习Gradle构建的核心技术，包括生命周期、任务等



## Gradle工程结构

### Gradle 设置文件: settings.gradle

### 顶层构建文件: build.gradle

- 配置项目全局属性

### 模块级构建文件: project/module/build.gradle

### Gradle 属性文件: xxx.properties

- gradle.properties
- local.properties



## 典型 Android 应用模块的构建流程。

<img src="https://developer.android.com/images/tools/studio/build-process_2x.png" width = "500"  alt="典型 Android 应用模块的构建流程" align=left />

典型 Android 应用模块的构建流程如图 所示按照以下常规步骤执行：

1. 编译器将您的源代码转换成 DEX 文件（Dalvik 可执行文件，其中包括在 Android 设备上运行的字节码），并将其他所有内容转换成编译后的资源。
2. APK 打包器将 DEX 文件和编译后的资源组合成单个 APK。不过，必须先为 APK 签名，然后才能将应用安装并部署到 Android 设备上。
3. APK 打包器使用调试或发布密钥库为 APK 签名：
   1. 如果您构建的是调试版应用（即专用于测试和分析的应用），则打包器会使用调试密钥库为应用签名。Android Studio 会自动使用调试密钥库配置新项目。
   2. 如果您构建的是打算对外发布的发布版应用，则打包器会使用发布密钥库为应用签名。如需创建发布密钥库，请参阅[在 Android Studio 中为应用签名](https://developer.android.com/studio/publish/app-signing?hl=zh_cn#studio)。
4. 在生成最终 APK 之前，打包器会使用 [zipalign](https://developer.android.com/studio/command-line/zipalign?hl=zh_cn) 工具对应用进行优化，以减少其在设备上运行时所占用的内存。





## 从0到1开发一个Gradle 插件

- Gradle插件介绍

  - 认识Gradle插件的概念
  - 使用场景
    - 提供具体的构建功能( Task )
    - 提高代码的复用性

- Gradle插件的使用

  - 学习Gradle插件的基本使用方法

  - 插件分类，您可以在几个地方放置插件的源代码。

    - 构建脚本

      您可以将插件的源代码直接包含在构建脚本中。这样做的好处是，无需执行任何操作即可自动编译插件并将其包含在构建脚本的类路径中。但是，该插件在构建脚本之外不可见，因此您不能在定义该构建脚本的外部重用该插件。

    - `buildSrc` 项目

      您可以将插件的源代码放在`*rootProjectDir*/buildSrc/src/main/java`目录中（`*rootProjectDir*/buildSrc/src/main/groovy`或`*rootProjectDir*/buildSrc/src/main/kotlin`根据您喜欢的语言）。Gradle将负责编译和测试插件，并使其在构建脚本的类路径中可用。该插件对于构建所使用的每个构建脚本都是可见的。但是，它在构建外部不可见，因此您不能在定义该构建的外部重用该插件。有关该项目的更多详细信息，请参见[组织Gradle](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#organizing_gradle_projects)`buildSrc`项目。

    - 独立项目/二进制插件

      您可以为插件创建一个单独的项目。该项目产生并发布了一个JAR，您可以在多个版本中使用它并与他人共享。通常，此JAR可能包含一些插件，或将几个相关的任务类捆绑到一个库中。或两者的某种组合。

  - 二进制插件的使用

    - ```groovy
      //二进制插件使用：1.添加插件的仓库地址
      repositories {
          google()
      }
      dependencies {
          //二进制插件使用：2.声明插件ID和版本号
           classpath "com.android.tools.build:gradle:4.1.3"
      }
      plugins {
          //二进制插件使用：3.应用插件
          id 'com.android.application'
      }
      //二进制插件使用：4.配置插件
      android {
          compileSdkVersion 30
          buildToolsVersion "30.0.3"
      }
      ```

  - 脚本插件的使用

    - ```groovy
      //脚本插件使用：1.添加gradle 文件
      other.gradle
      //脚本插件使用：2.导入其它gradle文件使用
      apply from: project.rootProject.file("other.gradle")
      ```

- 实战开发一个Gradle插件

  - 手把手实战开发页面路由中的Gradle插件
- 插件开发流程
  
  - 建立插件工程
    - 实现插件内部逻辑
    - 发布与使用插件
  - 页面路由框架

    - 对于一个URL ,根据映射关系表,来打开特定页面的组件
  - 标记页面
    - 收集页面
    - 生成文档
    - 注册映射
    - 打开页面

### 插件工程搭建

- 建立buildSrc 子工程

  - ```groovy
    //引入groovy 插件，编译插件工程中的代码
    apply plugin: 'groovy'
    
    //声明仓库地址
    repositories {
        jcenter()
    }
    
    //声明依赖的包
    dependencies {
        implementation gradleApi()
        implementation localGroovy()
    }
    ```

- 建立插件的入口

  - ```groovy
    //自定义插件：1.添加插件类，实现 Plugin<Project> 接口
    class RouterPlugin implements Plugin<Project> {
        //自定义插件：2.实现 apply 方法， 注入插件的逻辑
        @Override
        void apply(Project project) {
            println "RouterPlugin: apply from ${project.name}"
        }
    }
    
    //自定义插件：3. 在resources/META-INF/gradle-plugins 文件夹中创建 xxx.properties 文件用来配置插件id,xxx 为插件ID
    implementation-class=com.jay.router.groovy.RouterPlugin
      
    
    //自定义插件：4.引用插件
    apply plugin: 'com.jay.router.groovy'
    ```

- 实现参数配置

  - ```groovy
    //实现参数配置: 1.定义参数类，限定参数类型
    class RouterExtension {
        String wikiDir
    }
    //实现参数配置: 2.注册 Extension
    project.getExtensions().create("router", RouterExtension)
    //实现参数配置: 3.使用Extension
    router {
        wikiDir getRootDir().absoluteFile.toString()
    }
    //实现参数配置: 4.获取Extension
    project.afterEvaluate {
         RouterExtension routerExtension = project["router"]
         println "用户配置的wikiDir为：${routerExtension.wikiDir}"
    }
    
    ```

- 发布插件到本地

  - ```groovy
    //发布插件: 1.引入maven 插件用于发布
    apply plugin: 'maven'
    
    //发布插件: 2.配置maven发布信息
    uploadArchives {
        repositories {
            mavenDeployer {
                //设置发布路径，路径为工程根目录下的repo 文件夹
                repository(url: uri('../repo')) {
                    //设置组ID，通常为包名
                    pom.groupId = 'com.jay.router'
                    //设置插件名称
                    pom.artifactId = 'router-plugin'
                    //设置版本号
                    pom.version = '1.0.0'
                    //com.jay.router:router-plugin:1.0.0
                }
            }
        }
    }
    
    //发布插件: 3.buildSrc 子工程中无法直接发布，需要将代码复制到一个新的 model 中, 
    // router-plugin
    
    //发布插件: 4.配置发布插件的仓库地址
    maven {
        url "./repo"
    }
    //发布插件: 5.声明插件ID和版本号
    classpath "com.jay.router:router-plugin:1.0.0"
    //发布插件: 6.引用插件
    apply plugin: 'com.jay.router.groovy'
    ```

### APT采集页面路由信息

- APT是什么
  - 认识APT的概念以及使用场景
  - 注解处理器 ( Annotation Processing Tool )
- APT的技术原理
  - 了解APT的开发流程以及内部技术原理
  -  基本开发流程
    - 定义注解
    - 注解处理器
    - 调用注解与注解处理器
- APT实战应用
  - 路由信息采集功能的介绍与梳理
  - 路由信息采集功能的实现
- 关于编译时注解（APT）由浅入深有三部分
  - 自定义注解处理器
    - 例如 ButterKnife、Room 根据注解生成新的类。
  - 利用JcTree在编译时修改代码
    - 像 Lombok 自动往类中新增 getter/setter 方法、往方法中插入代码行等。 这种方式不推荐使用，因为只对 Java 代码有效，对 Kotlin 代码无效。
  - 自定义 Gradle 插件在编译时修改代码
    - 例如一些代码插桩框架、日志框架、方法耗时统计框架等。



- 定义注解
  
  - ```java
    //被保留在编译阶段的字节码阶段
    @Retention(RetentionPolicy.CLASS)
    //标记在类上面
    @Target(ElementType.TYPE)
    public @interface Destination {
        //页面路由地址，不能为空
        String url();
    
        //页面描述
        String description();
    }
    ```

- 注解处理器
  
  - 

