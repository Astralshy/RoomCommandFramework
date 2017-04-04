package validation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public interface Handler 
{
	public void process(Object o, Method m, Object[] args) throws Exception;
}
