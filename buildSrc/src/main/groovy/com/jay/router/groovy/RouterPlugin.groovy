package com.jay.router.groovy

import org.gradle.api.Plugin
import org.gradle.api.Project

//自定义插件：1.添加插件类，实现 Plugin<Project> 接口
class RouterPlugin implements Plugin<Project> {
    //自定义插件：2.实现 apply 方法， 注入插件的逻辑
    @Override
    void apply(Project project) {
        println "RouterPlugin: apply from ${project.name}"


        //实现参数配置: 2.注册 Extension
        project.getExtensions().create("router", RouterExtension)

        //实现参数配置: 4.获取Extension
        project.afterEvaluate {
            RouterExtension routerExtension = project["router"]
            println "用户配置的wikiDir为：${routerExtension.wikiDir}"
        }
    }
}