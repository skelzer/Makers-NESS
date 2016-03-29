package metrica6.artik.controller;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Ciclos;
import metrica6.artik.model.CiclosMes;
import metrica6.artik.model.Datos;
import metrica6.artik.model.Mes;
import metrica6.artik.services.BypassSvc;
import metrica6.artik.services.CiclosMesSvc;
import metrica6.artik.services.CiclosSvc;
import metrica6.artik.services.DatosSvc;
import metrica6.artik.services.MesSvc;
import metrica6.artik.services.SvcException;
import metrica6.artik.services.TipoEstadoSvc;
import metrica6.artik.utils.Utiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/squery")
public class Squery {
	
	@Autowired
	private BypassSvc bypass_svc;
	
	@Autowired
	private TipoEstadoSvc tipoEstado_svc;
	
	@Autowired
	private DatosSvc datos_svc;

	@Autowired
	private MesSvc mes_svc;
	
	@Autowired
	private CiclosMesSvc ciclos_Mes_svc;
	
	@Autowired
	private CiclosSvc ciclos_svc;

	//Atributos
	private static final String ATT_LISTA = "lista";
	
	//URL
	private static final String SUCCESS = "lista";
	
	//URL_ESTADO_SERVER
	private static final String GET_FRIO = "estado_frio";
	private static final String GET_CALIENTE = "estado_caliente";
	private static final String GET_RECIRCULANDO = "estado_recirculando";
	private static final String GET_ERROR = "estado_error";
	
	//URL_BOOLEAN_SERVER
	private static final String GET_TRUE = "estado_true";
	private static final String GET_FALSE = "estado_false";
	
	
	/**
	 * 
	 * @param usuarios
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
    public String view(/*@ModelAttribute Usuarios usuarios,*/@RequestParam Integer id,@RequestParam Integer s,@RequestParam Integer m,Model model,HttpSession session) {
		
		Bypass server = null;
		
		try {
			server = bypass_svc.buscar(id);
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Móvil
		if(m==1)
		{
			if(server.getFinCiclo()==1) //Ciclo acabado
			{
				if(s==0 || s== 1 || s==3) { //Estado frio / Estado caliente / Estado error
					 server.setFinCiclo(0); //Ciclo apagado
					 
					 try {
						bypass_svc.insertarModificar(server); //Insertamos en el servidor el nuevo estado
					} catch (SvcException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				switch(server.getTipoEstado().getId()) { //Devolvemos el estado
				case 0: return GET_FRIO;
				case 1: return GET_CALIENTE;
				case 2: return GET_RECIRCULANDO;
				case 3: return GET_ERROR;
				}
				}
			}
			/*Mandamos desde el movil (m=1) el estado, si es recirculando (s=2) y no esta recirculando cambiamos
			su estado */
			if((s==2) && (server.getFinCiclo()==0)) //Cambiamos de estado a recirculando
			{
				try {
					server.setTipoEstado(tipoEstado_svc.buscar(2));
					
					java.util.Date utilDate = new java.util.Date(); //fecha actual
					server.setTInicio(utilDate.getTime()); //Guardamos la fecha actual cuando se inicia la recirculacion

					
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				try {
					bypass_svc.insertarModificar(server);
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
			}
			
			switch(server.getTipoEstado().getId()) {
			case 0: return GET_FRIO;
			case 1: return GET_CALIENTE;
			case 2: return GET_RECIRCULANDO;
			case 3: return GET_ERROR;
			}
			
		}
		//Artik
		if(m==0)
			{
				try {
					server = bypass_svc.buscar(id);
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int estado_server = server.getTipoEstado().getId(); //Guardamos el estado del servidor
								
				if(estado_server==2) //El servidor esta recirculando
					{
					 if( s==2 || s==0) {
						 
						return GET_TRUE;
					 	}
					}
				
				
					if(s==1) { //La Artik indica que el bypass esta caliente
						server.setFinCiclo(1); //fin de la recirculacion
						
						java.util.Date utilDate = new java.util.Date(); //fecha actual 
						server.setTFin(utilDate.getTime()); //Añadimos la fecha de fin de ciclo al servidor
						
						
						//Fin de recirculacion - guardamos el fin
						
						//Calculamos el accumulado con el tiempo
						
						Integer id_server = server.getId();
						
						//Comprobamos la lista para ver si es un dato nuevo o no	
						ArrayList<Datos> lista_datos = new ArrayList<Datos>();
						try {
							lista_datos = (ArrayList<Datos>) datos_svc.buscarBypass(id_server);
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						ArrayList<Ciclos> lista_ciclos = new ArrayList<Ciclos>();
						
							try {
								lista_ciclos = (ArrayList<Ciclos>) ciclos_svc.buscarBypass(id_server);
							} catch (SvcException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						
						if(lista_datos.size()>0) //Tenemos un dato
						{
							 Datos dato_actual = lista_datos.get(lista_datos.size()-1);
							 
							 Ciclos ciclo_actual = lista_ciclos.get(lista_ciclos.size()-1);
							 
							 Integer anyo_actual=dato_actual.getAnyo();
							 
							 Calendar fechaactual = new GregorianCalendar();
							 int anyo = fechaactual.get(Calendar.YEAR);
							 
							 if(anyo==anyo_actual) //Estamos en el mismo año
							 	{
								 int mes = 1 + fechaactual.get(Calendar.MONTH); // Mes actual
								 Mes  mes_actual = new Mes();
								 CiclosMes ciclomes_actual = new CiclosMes();
								 switch (mes) {
									
									case 1:
										   mes_actual = dato_actual.getMesByEnero();
										   actualizarMes(mes_actual,server);	
										   ciclomes_actual = ciclo_actual.getCiclosMesByEnero();
										   actualizarCiclosMes(ciclomes_actual,server);	
										   break;
									
									case 2:
											mes_actual = dato_actual.getMesByFebrero();
											actualizarMes(mes_actual,server);
											ciclomes_actual = ciclo_actual.getCiclosMesByFebrero();
											actualizarCiclosMes(ciclomes_actual,server);	
											break;
									
									case 3:
											mes_actual = dato_actual.getMesByMarzo();
											actualizarMes(mes_actual,server);
											ciclomes_actual = ciclo_actual.getCiclosMesByMarzo();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 4:
											mes_actual = dato_actual.getMesByAbril();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByAbril();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 5:
											mes_actual = dato_actual.getMesByMayo();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByMayo();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 6:
											mes_actual = dato_actual.getMesByJunio();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByJunio();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 7:
											mes_actual = dato_actual.getMesByJulio();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByJulio();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 8:
											mes_actual = dato_actual.getMesByAgosto();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByAgosto();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 9:
											mes_actual = dato_actual.getMesBySeptiembre();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesBySeptiembre();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 10:
											mes_actual = dato_actual.getMesByOctubre();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByOctubre();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									   
									case 11:
											mes_actual = dato_actual.getMesByNoviembre();
											actualizarMes(mes_actual,server);	
											ciclomes_actual = ciclo_actual.getCiclosMesByNoviembre();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;
									  
									case 12:
											mes_actual = dato_actual.getMesByDiciembre();
											actualizarMes(mes_actual,server);
											ciclomes_actual = ciclo_actual.getCiclosMesByDiciembre();
											   actualizarCiclosMes(ciclomes_actual,server);	
											break;

									default:
										break;
									}
								 
								 
							 	}else //Año distinto 
							 	{//Futuro	
							 	}
							 
							 
						//Modificamos
						} else {
							
							//Creamos uno nuevo
							Datos nuevo_dato = new Datos();
							nuevo_dato.setBypass(server);
							
							Ciclos nuevo_ciclo = new Ciclos();
							nuevo_ciclo.setBypass(server);
							
							//Año actual
							Calendar fechaactual = new GregorianCalendar();
							int anyo = fechaactual.get(Calendar.YEAR);
							nuevo_dato.setAnyo(anyo);
							nuevo_ciclo.setAnyo(anyo);
							int mes =1 + fechaactual.get(Calendar.MONTH);
							
							//Creamos un mes nuevo 

							Mes nuevo_mes = new Mes();
							CiclosMes nuevo_ciclomes = new CiclosMes();

							actualizarMes(nuevo_mes,server);	
							actualizarCiclosMes(nuevo_ciclomes,server);	
							
							
							
							ArrayList<Mes> dias = new ArrayList<Mes>();
							try {
								dias = (ArrayList<Mes>) mes_svc.listar();
							} catch (SvcException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							ArrayList<CiclosMes> dias_ciclo = new ArrayList<CiclosMes>();
							
							try {
								dias_ciclo = (ArrayList<CiclosMes>) ciclos_Mes_svc.listar();
							} catch (SvcException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							Integer ultima_id = dias.get(dias.size()-1).getId();
							Integer ultima_id_ciclo = dias_ciclo.get(dias_ciclo.size()-1).getId();
							
							Mes ultimo_mes = null;
							try {
								ultimo_mes =  mes_svc.buscar(ultima_id);
							} catch (SvcException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							CiclosMes ultimo_ciclo_mes = null;
							try {
								ultimo_ciclo_mes= ciclos_Mes_svc.buscar(ultima_id_ciclo);
							} catch (SvcException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							switch (mes) {
							
							case 1:nuevo_dato.setMesByEnero(ultimo_mes);
								   nuevo_ciclo.setCiclosMesByEnero(ultimo_ciclo_mes);	
								   break;
							
							case 2:nuevo_dato.setMesByFebrero(ultimo_mes);
								   nuevo_ciclo.setCiclosMesByEnero(ultimo_ciclo_mes);	
								   break;
							
							case 3:nuevo_dato.setMesByMarzo(ultimo_mes);
							nuevo_ciclo.setCiclosMesByMarzo(ultimo_ciclo_mes);	
							   break;
							   
							case 4:nuevo_dato.setMesByAbril(ultimo_mes);
							nuevo_ciclo.setCiclosMesByAbril(ultimo_ciclo_mes);	
							   break;
							   
							case 5:nuevo_dato.setMesByMayo(ultimo_mes);
							nuevo_ciclo.setCiclosMesByMayo(ultimo_ciclo_mes);	
							   break;
							   
							case 6:nuevo_dato.setMesByJunio(ultimo_mes);
							nuevo_ciclo.setCiclosMesByJunio(ultimo_ciclo_mes);	
							   break;
							   
							case 7:nuevo_dato.setMesByJulio(ultimo_mes);
							nuevo_ciclo.setCiclosMesByJulio(ultimo_ciclo_mes);	
							   break;
							   
							case 8:nuevo_dato.setMesByAgosto(ultimo_mes);
							nuevo_ciclo.setCiclosMesByAgosto(ultimo_ciclo_mes);	
							   break;
							   
							case 9:nuevo_dato.setMesBySeptiembre(ultimo_mes);
							nuevo_ciclo.setCiclosMesBySeptiembre(ultimo_ciclo_mes);	
							   break;
							   
							case 10:nuevo_dato.setMesByOctubre(ultimo_mes);
							nuevo_ciclo.setCiclosMesByOctubre(ultimo_ciclo_mes);	
							   break;
							   
							case 11:nuevo_dato.setMesByNoviembre(ultimo_mes);
							nuevo_ciclo.setCiclosMesByNoviembre(ultimo_ciclo_mes);	
							   break;
							  
							case 12:nuevo_dato.setMesByDiciembre(ultimo_mes);
							nuevo_ciclo.setCiclosMesByDiciembre(ultimo_ciclo_mes);	
							   break;

							default:
								break;
							}
							
							try {
								datos_svc.insertarModificar(nuevo_dato);
							} catch (SvcException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								ciclos_svc.insertarModificar(nuevo_ciclo);
							} catch (SvcException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						
					}
					if(s==3) {
						server.setFinCiclo(1); //fin recirculado
					}
					
					try {
						server.setTipoEstado(tipoEstado_svc.buscar(s));
					} catch (SvcException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						bypass_svc.insertarModificar(server);
					} catch (SvcException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return GET_FALSE;
					
			}
		
		
		try {
			model.addAttribute(ATT_LISTA,bypass_svc.listar());
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	
	private void actualizarMes(Mes mes, Bypass server) {
		Mes aux = new Mes();	
		if(mes.getId()!=null){
		try {
				aux =	mes_svc.buscar(mes.getId());
			} catch (SvcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Calendar fechaactual = new GregorianCalendar();
		int dia = fechaactual.get(Calendar.DAY_OF_MONTH); // dia actual
		
		//Insertamos en el dia del mes el acumulado
		switch (dia) {
		case 1:
			aux.setD1(aux.getD1() + Utiles.Acumulado(server));
			break;
		case 2:
			aux.setD2(aux.getD2() +Utiles.Acumulado(server));
			break;
		case 3:
			aux.setD3(aux.getD3() +Utiles.Acumulado(server));
			break;
		case 4:
			aux.setD4(aux.getD4() +Utiles.Acumulado(server));
			break;
		case 5:
			aux.setD5(aux.getD5() +Utiles.Acumulado(server));
			break;
		case 6:
			aux.setD6(aux.getD6() +Utiles.Acumulado(server));
			break;
		case 7:
			aux.setD7(aux.getD7() +Utiles.Acumulado(server));
			break;
		case 8:
			aux.setD8(aux.getD8() +Utiles.Acumulado(server));
			break;
		case 9:
			aux.setD9(aux.getD9() +Utiles.Acumulado(server));
			break;
		case 10:
			aux.setD10(aux.getD10() +Utiles.Acumulado(server));
			break;
		case 11:
			aux.setD11(aux.getD11() +Utiles.Acumulado(server));
			break;
		case 12:
			aux.setD12(aux.getD12() +Utiles.Acumulado(server));
			break;
		case 13:
			aux.setD13(aux.getD13() +Utiles.Acumulado(server));
			break;
		case 14:
			aux.setD14(aux.getD14() +Utiles.Acumulado(server));
			break;
		case 15:
			aux.setD15(aux.getD15() +Utiles.Acumulado(server));
			break;
		case 16:
			aux.setD16(aux.getD16() +Utiles.Acumulado(server));
			break;
		case 17:
			aux.setD17(aux.getD17() +Utiles.Acumulado(server));
			break;
		case 18:
			aux.setD18(aux.getD18() +Utiles.Acumulado(server));
			break;
		case 19:
			aux.setD19(aux.getD19() +Utiles.Acumulado(server));
			break;
		case 20:
			aux.setD20(aux.getD20() +Utiles.Acumulado(server));
			break;
		case 21:
			aux.setD21(aux.getD21() +Utiles.Acumulado(server));
			break;
		case 22:
			aux.setD22(aux.getD22() +Utiles.Acumulado(server));
			break;
		case 23:
			aux.setD23(aux.getD23() +Utiles.Acumulado(server));
			break;
		case 24:
			aux.setD24(aux.getD24() +Utiles.Acumulado(server));
			break;
		case 25:
			aux.setD25(aux.getD25() +Utiles.Acumulado(server));
			break;
		case 26:
			aux.setD26(aux.getD26() +Utiles.Acumulado(server));
			break;
		case 27:
			aux.setD27(aux.getD27() +Utiles.Acumulado(server));
			break;
		case 28:
			aux.setD28(aux.getD28() +Utiles.Acumulado(server));
			break;
		case 29:
			aux.setD29(aux.getD29() +Utiles.Acumulado(server));
			break;
		case 30:
			aux.setD30(aux.getD30() +Utiles.Acumulado(server));
			break;
		case 31:
			aux.setD31(aux.getD31() +Utiles.Acumulado(server));
			break;
		default:
			break;
		}
		
		try {
			mes_svc.insertarModificar(aux);
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void actualizarCiclosMes(CiclosMes ciclomes, Bypass server) {
		CiclosMes aux = new CiclosMes();	
		if(ciclomes.getId()!=null){
		try {
				aux =	ciclos_Mes_svc.buscar(ciclomes.getId());
			} catch (SvcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Calendar fechaactual = new GregorianCalendar();
		int dia = fechaactual.get(Calendar.DAY_OF_MONTH); // dia actual
		
		//Insertamos en el dia del mes el acumulado
		switch (dia) {
		case 1:
			aux.setD1(aux.getD1() +1);
			break;
		case 2:
			aux.setD2(aux.getD2() +1);
			break;
		case 3:
			aux.setD3(aux.getD3() +1);
			break;
		case 4:
			aux.setD4(aux.getD4() +1);
			break;
		case 5:
			aux.setD5(aux.getD5() +1);
			break;
		case 6:
			aux.setD6(aux.getD6() +1);
			break;
		case 7:
			aux.setD7(aux.getD7() +1);
			break;
		case 8:
			aux.setD8(aux.getD8() +1);
			break;
		case 9:
			aux.setD9(aux.getD9() +1);
			break;
		case 10:
			aux.setD10(aux.getD10() +1);
			break;
		case 11:
			aux.setD11(aux.getD11() +1);
			break;
		case 12:
			aux.setD12(aux.getD12() +1);
			break;
		case 13:
			aux.setD13(aux.getD13() +1);
			break;
		case 14:
			aux.setD14(aux.getD14() +1);
			break;
		case 15:
			aux.setD15(aux.getD15() +1);
			break;
		case 16:
			aux.setD16(aux.getD16() +1);
			break;
		case 17:
			aux.setD17(aux.getD17() +1);
			break;
		case 18:
			aux.setD18(aux.getD18() +1);
			break;
		case 19:
			aux.setD19(aux.getD19() +1);
			break;
		case 20:
			aux.setD20(aux.getD20() +1);
			break;
		case 21:
			aux.setD21(aux.getD21() +1);
			break;
		case 22:
			aux.setD22(aux.getD22() +1);
			break;
		case 23:
			aux.setD23(aux.getD23() +1);
			break;
		case 24:
			aux.setD24(aux.getD24() +1);
			break;
		case 25:
			aux.setD25(aux.getD25() +1);
			break;
		case 26:
			aux.setD26(aux.getD26() +1);
			break;
		case 27:
			aux.setD27(aux.getD27() +1);
			break;
		case 28:
			aux.setD28(aux.getD28() +1);
			break;
		case 29:
			aux.setD29(aux.getD29() +1);
			break;
		case 30:
			aux.setD30(aux.getD30() +1);
			break;
		case 31:
			aux.setD31(aux.getD31() +1);
			break;
		default:
			break;
		}
		
		try {
			ciclos_Mes_svc.insertarModificar(aux);
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Recogemos los datos del formulario y lo comprobamos su validacion
	 * @param usuario nuestro usurio relleno
	 * @param result
	 * @param model
	 * @param locale
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
    public String execute(/*Usuarios usuarios, */BindingResult result, Model model, Locale locale){//, HttpSession session) {
		
					
		try {
			model.addAttribute(ATT_LISTA,bypass_svc.listar());
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
}
