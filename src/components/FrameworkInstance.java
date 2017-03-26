package components;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import annotations.*;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import validation.*;

public class FrameworkInstance {

	private static Validator v;
	
	public FrameworkInstance(String path){
		
		ScanResult results = new FastClasspathScanner(path).scan();		
		List<String> allResults = results.getNamesOfClassesWithAnnotation(Driver.class);
		try{
			v = new Validator();
			
			for (String s : allResults)
			{
				Class<?> c = Class.forName(s);
				
				Object o = c.newInstance();
				
				for(Field f: c.getDeclaredFields()){
					
					f.setAccessible(true);
					
					if(f.isAnnotationPresent(annotations.Manager.class)){
						
						SessionManager sm = createProxy(new SessionManager());
						
						f.set(o, sm);
						
						break;
					}
					
				}
				
				for(Method m: c.getDeclaredMethods()){
					m.setAccessible(true);
					
					if(m.isAnnotationPresent(Logic.class)){
						
						
						m.invoke(o);
						return;
					}
					
				}
				
			}
		}
		catch(Exception e){
			
			e.printStackTrace();
			
		}
		
	}
	
	
	private SessionManager createProxy(Object instance) throws Exception{
		
        Class<?> proxyType = new ByteBuddy()
                .subclass(instance.getClass())
                .method(ElementMatchers.any())             
                .intercept(InvocationHandlerAdapter.of(new ProxyInvocationHandler(instance)))
                .make()
                .load(instance.getClass().getClassLoader())
                .getLoaded();
        return(SessionManager) proxyType.newInstance();
		
	}
	
	static class ProxyInvocationHandler implements InvocationHandler
	{
		private Object original;
		
		public ProxyInvocationHandler(Object o) 
		{
			original = o;
		}
		
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub
		
			v.validate(proxy, method, args);
			
			return method.invoke(original, args);
		}
		
	}
}
