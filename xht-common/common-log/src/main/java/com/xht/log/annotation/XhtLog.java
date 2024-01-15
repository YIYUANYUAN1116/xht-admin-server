package com.xht.log.annotation;

import com.xht.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : YIYUANYUAN
 * @description :
 * RetentionPolicy.SOURCE : 仅存在于源代码中，编译阶段会被丢弃，不会包含于class字节码文件中。@Override, @SuppressWarnings都属于这类注解。
 * RetentionPolicy.CLASS : 默认策略，在class字节码文件中存在，在类加载的时被丢弃，运行时无法获取到。
 * RetentionPolicy.RUNTIME : 始终不会丢弃，可以使用反射获得该注解的信息。自定义的注解最常用的使用方式。
 *
 * Documented，表示是否将此注解的相关信息添加到javadoc文档中。
 *
 * Inherited，定义该注解和子类的关系，使用此注解声明出来的自定义注解，在使用在类上面时，子类会自动继承此注解，
 * 否则，子类不会继承此注解。注意，使用Inherited声明出来的注解，只有在类上使用时才会有效，对方法，属性等其他无效。
 * @date: 2023/12/29  17:31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface XhtLog {
    public String title();
    public OperatorType operatorType() default OperatorType.MANAGE;	// 操作人类别

    public int businessType() ;     // 业务类型（0其它 1新增 2修改 3删除）

    public boolean isSaveRequestData() default true;   // 是否保存请求的参数
    public boolean isSaveResponseData() default true;  // 是否保存响应的参数

}
