/**
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.test.mysql.entity;

import com.baomidou.mybatisplus.annotations.FieldStrategy;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * <p>
 * 测试用户类
 * </p>
 * 
 * @author hubin sjy
 * @Date 2016-09-09
 */
/* 表名 value 注解【 驼峰命名可无 】, resultMap 注解测试【 映射 xml 的 resultMap 内容 】 */
@TableName(resultMap = "userMap")
public class User implements Serializable {

	/* 表字段注解，false 表中不存在的字段，可无该注解 默认 true */
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/* 主键ID 注解，value 字段名，type 用户输入ID */
	@TableId(value = "test_id")
	private Long id;

	/* 测试忽略验证 */
	@TableField(validate = FieldStrategy.IGNORED)
	private String name;

	private Integer age;

	/* 测试下划线字段命名类型, 字段填充 */
	@TableField(value = "test_type", validate = FieldStrategy.FILL)
	private Integer testType;

	@TableField(el = "role.id")
	private Role role;

	private String desc = "默认描述";

	// 或@TableField(el = "role,jdbcType=BIGINT)
	@TableField(el = "phone, typeHandler=com.baomidou.mybatisplus.test.mysql.typehandler.PhoneTypeHandler")
	private PhoneNumber phone;

	public User() {

	}

	public User(String name) {
		this.name = name;
	}

	public User(Integer testType) {
		this.testType = testType;
	}

	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public User(Long id, Integer age) {
		this.id = id;
		this.age = age;
	}

	public User(Long id, String name, Integer age, Integer testType) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.testType = testType;
	}

	public User(String name, Integer age, Integer testType) {
		this.name = name;
		this.age = age;
		this.testType = testType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", testType=" + testType + ", role="
				+ role + ", phone=" + phone + ", desc=" + desc + '}';
	}

	/**
	 * 测试类型
	 */
	public static void main(String args[]) throws IllegalAccessException {
		User user = new User();
		user.setName("12306");
		user.setAge(3);
		System.err.println(User.class.getName());
		Field[] fields = user.getClass().getDeclaredFields();
		for (Field field : fields) {
			System.out.println("===================================");
			System.out.println(field.getName());
			System.out.println(field.getType().toString());
			field.setAccessible(true);
			System.out.println(field.get(user));
		}
	}
}
