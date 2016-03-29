package metrica6.artik.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import metrica6.artik.model.Bypass;
import metrica6.artik.services.BypassSvc;
import metrica6.artik.services.SvcException;
import metrica6.artik.services.TipoEstadoSvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/alarm")
public class Alarma {
	
	public int count=0;
	public int nid=0;
	public ScheduledExecutorService ses;
	
	
	@Autowired
	private BypassSvc bypass_svc;
	
	@Autowired
	private TipoEstadoSvc tipoEstado_svc;
	
	//Atributos
	private static final String ATT_LISTA = "json";
	
	//URL
	private static final String SUCCESS = "nodos";

	@RequestMapping(method=RequestMethod.GET)
	  public String view(@RequestParam Integer id,Model model,HttpSession session,@RequestParam String d) throws ParseException  {
		

		nid=id;
		
		//Separamos la fecha de la hora
		String[] parts = d.split("\\.");

		//Separamos las horas de los minutos
		String[] horas = parts[1].split(":");

		//Asignamos el dia de la semana de la url a su valor numerico
		Integer dia_url=0;
		
		switch (parts[0]) {
		case "lun":
			dia_url = 2;
			break;
		case "mar":
			dia_url = 3;
			break;
		case "mie":
			dia_url = 4;
			break;
		case "jue":
			dia_url = 5;
			break;
		case "vie":
			dia_url = 6;
			break;
		case "sab":
			dia_url = 7;
			break;
		case "dom":
			dia_url = 1;
			break;

		default:
			break;
		}
		
		
		//Pasamos la fecha de la url a una fecha en formato calendar
		Calendar alarma = Calendar.getInstance();
		
		Integer dia_actual = alarma.get(Calendar.DAY_OF_WEEK);
		Integer diferencia = dia_url-dia_actual;
		
		Integer hora_actual = alarma.get(Calendar.HOUR);
		Integer diferencia_hora = Integer.parseInt(horas[0]) - hora_actual;
		
		Integer minutos_actual = alarma.get(Calendar.MINUTE);
		Integer diferencia_minutos = Integer.parseInt(horas[1]) - minutos_actual;
		
		if(diferencia<0)
		{
			diferencia = 7 + diferencia;
		}
		
		if(diferencia_hora<0 || diferencia_hora==0 && diferencia_minutos<0)
			{
				diferencia =7;
			} 
		
			
		alarma.add(alarma.DAY_OF_YEAR, diferencia);
		
        //Troceamos la fecha para crear el string
        Integer ndia = alarma.get(Calendar.DAY_OF_MONTH);
        Integer nmes = alarma.get(Calendar.MONTH)+1;
        Integer nanyo = alarma.get(Calendar.YEAR);
        
        String cmes="0";
        
        if(nmes < 10)
        	{
        		cmes= cmes + nmes.toString();
        	}
        else 
        	{
        	 cmes = nmes.toString();
        	}
        
        String cdia="0";
        
        if(ndia<10)
        	{
        		cdia= cdia + ndia.toString();
        	}
        else {
        	 cdia = ndia.toString();
        }
        //Creamos el nuevo string con la fecha de la alarma
        String fecha_alarma = cdia+"-"+ cmes+"-"+nanyo+" "+horas[0]+":"+horas[1]+":00";
        DateFormat formatter = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        Date date = formatter.parse(fecha_alarma);
        long dateInLong = date.getTime();
        
        //Pasamos la fecha de alarma a Calendar
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateInLong);
        
        Calendar rightNow = Calendar.getInstance();
        /*
        System.out.println("-----Fecha actual--------");
        System.out.println(rightNow.getTime());
        System.out.println("Milis "+ rightNow.getTimeInMillis());
        
        System.out.println("------Fecha alarma --------");
        System.out.println(cal.getTime());
        System.out.println("Milis "+cal.getTimeInMillis());
      	
        System.out.println("--------------------------");
        System.out.println("Diferencia:");
      	*/
      	long segundos = (cal.getTimeInMillis() - rightNow.getTimeInMillis());
      	
      	//System.out.println("Milisegundos " + segundos);
      	
      //Planificador
      	
      ses = Executors.newScheduledThreadPool(1);
      	            
      Runnable accion = new Runnable() {
      	                public void run() {
      	                    count++;
      	                    //Activo el bypass en el servidor
      	                	System.out.println("----Inicio accion-----");
      	                	Bypass server = null;
      	          		
      	                	try {
      	                			server = bypass_svc.buscar(nid);
      	                			server.setTipoEstado(tipoEstado_svc.buscar(2));
      	                		} catch (SvcException e1) {
      	                			// TODO Auto-generated catch block
      	                			e1.printStackTrace();
      	                		}
      	          		
      	                	server.setFinCiclo(0);
      	                	
      	                	java.util.Date utilDate = new java.util.Date(); //fecha actual
      						server.setTInicio(utilDate.getTime()); //Guardamos la fecha actual cuando se inicia la recirculacion
      	                	
      						try {
								bypass_svc.insertarModificar(server);
							} catch (SvcException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
      	          		
      	                	if (count == 1)
      	                  {
      	                      ses.shutdown();
      	                      System.out.println( "FIN");
      	                  }
      	                }
      	            };
      
      ses.scheduleAtFixedRate(accion, segundos, 3 * 1000, TimeUnit.MILLISECONDS);
    model.addAttribute(ATT_LISTA,"Alarma programada para: "+fecha_alarma);    
	return SUCCESS;
		
	}
}