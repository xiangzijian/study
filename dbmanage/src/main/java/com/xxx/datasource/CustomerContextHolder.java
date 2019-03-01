package com.xxx.datasource;


/**
 * 
 * @author Zero
 * @mail baozilaji@126.com
 *
 * Aug 26, 2014
 */
public class CustomerContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static String getCustomerType() {
		return (String) contextHolder.get();
	}
	/**
	 * 通过字符串选择数据源
	 * @param customerType
	 */
	public static void setCustomerType(String customerType) {
		System.out.println("db ===="+customerType);
		contextHolder.set(customerType);
	}
}
