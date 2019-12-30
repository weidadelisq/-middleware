package com.neo;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("hello word");
	}
	@Test
	public void test() {
		// 输出的是5
		System.out.println(AviatorEvaluator.execute("string.length('hello')"));
		// 输出的是true
		System.out.println(AviatorEvaluator.execute("string.contains(\"test\", string.substring('sword', 0, 1))"));

		// 注册函数使用addFunction，移除函数使用removeFunction
		AviatorEvaluator.addFunction(new AddFunction());
		// 输出的是3.0
		System.out.println(AviatorEvaluator.execute("add(1, 2)"));
		// 输出的是103.0
		System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)"));
	}
	class AddFunction extends AbstractFunction {
		@Override
		public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
			Number left = FunctionUtils.getNumberValue(arg1, env);
			Number right = FunctionUtils.getNumberValue(arg2, env);
			return new AviatorDouble(left.doubleValue() + right.doubleValue());
		}
		public String getName() {
			return "add";
		}
	}
}
