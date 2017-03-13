/*
 * Copyright(C) 2007-2014 China Talent Group Talent Management Outsourcing Management Consultant Limited Company, http://www.ctgtmo.com.cn
 *
 * 化繁为简 无限创新
 * Great simplifying, Unlimited innovation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * TODO 
 * @author wuyanhui 
 * 2017年3月8日 上午10:45:08 
 * 2017 www.ctgtmo.com Inc. All rights reserved. 
 * @version V1.0   
*/
package com.example.bean;

/**  
 * @ClassName: Test  
 * @Description: TODO 
 * @author wuyanhui yanhui.wu@ctgtmo.com  
 * @since 2017年3月8日 上午10:45:08  
 */
public class Test {

   public static void main(String[] args) {
	   中国式编程 p = new 中国式编程();
	   p.set姓名("wu");
	   p.set年龄(10);
	   p.set性别("男");
	   
	   System.out.println(p.get姓名());
   }
}
