import io.github.jorelali.varchangelogger.LogVariable;

public class MyVarLoggerMain {
	
	public static void main(String[] args) {
		//Variable declaration
		@LogVariable int i = 0;
		
		//Variable assignment
		i += 10;
		
		//Variable compound assignment
		i = i + 20;
	}
	
}
