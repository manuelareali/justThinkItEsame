package exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import beanweb.BachecaPersonaleBoundary;

public class Trigger {

	
	
	 public  boolean isNumeric(String str) throws MyException, NumberFormatException { 
		  if (str.isEmpty()) {
				throw new MyException("Devi selezionare una riga della tabella",MyException.CAMPI_VUOTI);

		  }else{
			 if(Float.parseFloat(str)== 0) {	
					throw new MyException("Alcuni campi sono vuoti.", MyException.CAMPI_VUOTI);
			 	}
		  }
		    return true;     
		}
	 
	 
	 public  boolean isNumerico(String str) throws MyException, NumberFormatException { 
		  if (str.isEmpty()) {
				throw new MyException(" Seleziona una riga della tabella",MyException.CAMPI_VUOTI);
 
		  }else{
			 if(Float.parseFloat(str)== 0) {	
					throw new MyException("L'id passato non è corretto",MyException.CARITAS_ERROR);
}  
		  }
		    return true;     
		}
}
