package com.aaa.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author: 陈建
 * @Date: 2019/6/19 0019 16:26
 * @Version 1.0
 */
public class ApmAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("===============this is an agent============ ");
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                //拦截方法委托给某个类处理
                return builder
                        //拦截任意方法
                        .method(ElementMatchers.<MethodDescription>any())
                        //委托
                        .intercept(MethodDelegation.to(TimeInterceptor.class));
            }
        };
        //创建监听
        AgentBuilder.Listener listener = new AgentBuilder.Listener() {

            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }
        };
        new AgentBuilder
                .Default()
                // 指定需要拦截的类
                .type(ElementMatchers.nameStartsWith("com.aaa.controller"))
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }
}
