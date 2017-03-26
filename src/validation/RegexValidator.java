package validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import annotations.Regex;

@ValidationAnnotation(target = Regex.class)
public class RegexValidator implements ValidationHandler {

	@Override
	public void process(Object o, Method m, Object[] args) throws Exception {
		
		System.out.println("yo");

	}

}
