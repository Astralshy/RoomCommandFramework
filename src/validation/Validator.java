package validation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import annotations.*;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;


public class Validator 
{
	private HashMap<Class<?>, ValidationHandler> map = new HashMap<Class<?>,ValidationHandler>();
	
	
	public Validator() throws Exception
	{
		// init map by scanning
		//System.out.println(RegexValidator.class.getCanonicalName());
		ScanResult results = new FastClasspathScanner("validation").scan();		
		List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
		//System.out.println(allResults);
		//System.out.println(results.getNamesOfAllClasses());
		for (String s : allResults)
		{
			Class<?> c = Class.forName(s);
			ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);				
			//System.out.println(va);				
			map.put(va.target(), (ValidationHandler) c.newInstance());
		}
	
	}
	
	public Validator(String path) throws Exception
	{
		// init map by scanning
		//System.out.println("valid");
		ScanResult results = new FastClasspathScanner(path).scan();		
		List<String> allResults = results.getNamesOfClassesWithAnnotation(ValidationAnnotation.class);
		System.out.println(allResults);
		for (String s : allResults)
		{
			Class<?> c = Class.forName(s);
			ValidationAnnotation va = (ValidationAnnotation) c.getAnnotation(ValidationAnnotation.class);				
			//System.out.println(va);				
			map.put(va.target(), (ValidationHandler) c.newInstance());
		}
	
	}
	
	
	public void validate(Object o, Method m, Object[] args) throws Exception
	{
		//System.out.println(m.getName());
		
		Annotation[] alist = m.getAnnotations();
		for (Annotation a : alist)
		{
			//System.out.println(a.annotationType().getName());
			ValidationHandler vh = map.get(a.annotationType());
			if (vh!=null)
			{
				vh.process(o, m, args);
			}
		}
	}

	

	

	
	
	
	
}
