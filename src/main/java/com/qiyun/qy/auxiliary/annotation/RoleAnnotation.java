package com.qiyun.qy.auxiliary.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

@Target(ElementType.METHOD)//作用在方法
@Retention(RetentionPolicy.RUNTIME)//仅在运行时
@Documented
public @interface RoleAnnotation {
	
	public static enum TYPE {
		/**
		 * <b>“且”关系的Role</b>
		 * <p>必须指定type</p>
		 */
		AND,
		
		/**
		 * <b>“或”关系的Role</b>
		 */
		OR,
		
		/**
		 * <b>“混合”关系的Role</b>
		 * <p>必须指定type</p>
		 */
		MIXED
	}
	
	/**
	 * <b>Role之间的关联方式</b>
	 */
	TYPE type() default TYPE.OR;

	/**
	 * <b>“且”关系的Role</b>
	 * <p>必须指定type</p>
	 */
	String[] and() default {};
	
	/**
	 * <b>“或”关系的Role</b>
	 */
	@AliasFor("value")
	String[] or() default {};
	
	@AliasFor("or")
	String[] value() default {};
	
	/**
	 * <b>“混合”关系的Role</b>
	 * <p>必须指定type</p>
	 */
	String mixed() default "";// ( (hr && fe) || admin )
	
}
