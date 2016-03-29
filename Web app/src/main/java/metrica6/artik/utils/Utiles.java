package metrica6.artik.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import metrica6.artik.model.Bypass;

public class Utiles {
	
	
	
	public static Date fechaActual()
	{
		Calendar fechaactual = new GregorianCalendar();
		 int anyo = fechaactual.get(Calendar.YEAR);
	     int mes =1 + fechaactual.get(Calendar.MONTH);
	     int dia = fechaactual.get(Calendar.DAY_OF_MONTH);
	     SimpleDateFormat esDate = new SimpleDateFormat("dd/MM/yyyy");
	     Date newfecha=null;
		try {
			newfecha = esDate.parse(""+dia+"/"+mes+"/"+anyo+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newfecha;
	}
	
	public static int Acumulado(Bypass aux) {
		
		double cte = 0.3333;
		int acc=0;
		Long tiempo = aux.getTFin() - aux.getTInicio();
		acc=(int) ((tiempo/1000)*cte);
		return acc;
		
	}
}
