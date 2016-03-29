package metrica6.artik.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/statsq")
public class Statsq {
	

	@Autowired
	private BypassSvc bypass_svc;
	
	@Autowired
	private DatosSvc datos_svc;
	
	@Autowired
	private MesSvc mes_svc;
	
	@Autowired
	private CiclosSvc ciclos_svc;
	
	@Autowired
	private CiclosMesSvc ciclos_mes_svc;
	
	//Atributos
	private static final String ATT_LISTA = "json";
	
	//URL
	private static final String SUCCESS = "nodos";

	@RequestMapping(method=RequestMethod.GET)
    public String view(/*@ModelAttribute Usuarios usuarios,*/ @RequestParam Integer id,Model model,HttpSession session) {
		
		
		ArrayList<Datos> list = new ArrayList<Datos>();
		
		try {
			list = (ArrayList<Datos>) datos_svc.listar();
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Datos dato = new Datos();
		
		for(int i=0;i<list.size();i++) {
			
			if(list.get(i).getBypass().getId()==id && list.get(i).getAnyo()==2015) {
				dato=list.get(i);
			}
			
		}
		
		System.out.println("Id: "+ dato.getId()+" Año: "+dato.getAnyo());
		
		
		ArrayList<Ciclos> list_ciclos = new ArrayList<Ciclos>();
		
		try {
			list_ciclos = (ArrayList<Ciclos>) ciclos_svc.listar();
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Ciclos ciclo = new Ciclos();
		
		for(int i=0;i<list_ciclos.size();i++) {
			
			if(list_ciclos.get(i).getBypass().getId()==id && list_ciclos.get(i).getAnyo()==2015) {
				ciclo=list_ciclos.get(i);
			}
			
		}
		
		System.out.println("Id: "+ ciclo.getId()+" Año: "+ciclo.getAnyo());
		
		
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		
		Map<Integer, Double> map_ciclos = new HashMap<Integer, Double>();
		
		
		//Ahorro Litros
		
		//Enero
		Mes enero = new Mes();
		
		try {
			enero = mes_svc.buscar(dato.getMesByEnero().getId());
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Double ahorro_enero = 0.0;
		
		ahorro_enero = (double) (enero.getD10()+enero.getD2()+enero.getD3()+enero.getD4()+enero.getD5()+enero.getD6()+enero.getD7()+enero.getD8()+
				enero.getD9()+enero.getD10()+enero.getD11()+enero.getD12()+enero.getD13()+enero.getD14()+enero.getD15()+enero.getD16()
				+enero.getD17()+enero.getD18()+enero.getD19()+enero.getD20()+enero.getD21()+enero.getD22()+enero.getD23()+enero.getD24()
				+enero.getD25()+enero.getD26()+enero.getD27()+enero.getD28()+enero.getD29()+enero.getD30()+enero.getD31());
		map.put(1, ahorro_enero);
		
		//Febrero
				Mes febrero = new Mes();
				
				try {
			febrero = mes_svc.buscar(dato.getMesByFebrero().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_febrero = 0.0;
				
				ahorro_febrero = (double) (febrero.getD10()+febrero.getD2()+febrero.getD3()+febrero.getD4()+febrero.getD5()+febrero.getD6()+febrero.getD7()+febrero.getD8()+
				febrero.getD9()+febrero.getD10()+febrero.getD11()+febrero.getD12()+febrero.getD13()+febrero.getD14()+febrero.getD15()+febrero.getD16()
						+febrero.getD17()+febrero.getD18()+febrero.getD19()+febrero.getD20()+febrero.getD21()+febrero.getD22()+febrero.getD23()+febrero.getD24()
						+febrero.getD25()+febrero.getD26()+febrero.getD27()+febrero.getD28()+febrero.getD29()+febrero.getD30()+febrero.getD31());
				map.put(2, ahorro_febrero);
				
		//Marzo
				Mes marzo = new Mes();
				
				try {
			marzo = mes_svc.buscar(dato.getMesByMarzo().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_marzo = 0.0;
				
				ahorro_marzo = (double) (marzo.getD10()+marzo.getD2()+marzo.getD3()+marzo.getD4()+marzo.getD5()+marzo.getD6()+marzo.getD7()+marzo.getD8()+
				marzo.getD9()+marzo.getD10()+marzo.getD11()+marzo.getD12()+marzo.getD13()+marzo.getD14()+marzo.getD15()+marzo.getD16()
						+marzo.getD17()+marzo.getD18()+marzo.getD19()+marzo.getD20()+marzo.getD21()+marzo.getD22()+marzo.getD23()+marzo.getD24()
						+marzo.getD25()+marzo.getD26()+marzo.getD27()+marzo.getD28()+marzo.getD29()+marzo.getD30()+marzo.getD31());
				map.put(3, ahorro_marzo);
				
		//Abril
				Mes abril = new Mes();
				
				try {
			abril = mes_svc.buscar(dato.getMesByAbril().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_abril = 0.0;
				
				ahorro_abril = (double) (abril.getD10()+abril.getD2()+abril.getD3()+abril.getD4()+abril.getD5()+abril.getD6()+abril.getD7()+abril.getD8()+
				abril.getD9()+abril.getD10()+abril.getD11()+abril.getD12()+abril.getD13()+abril.getD14()+abril.getD15()+abril.getD16()
						+abril.getD17()+abril.getD18()+abril.getD19()+abril.getD20()+abril.getD21()+abril.getD22()+abril.getD23()+abril.getD24()
						+abril.getD25()+abril.getD26()+abril.getD27()+abril.getD28()+abril.getD29()+abril.getD30()+abril.getD31());
				map.put(4, ahorro_abril);
				
		//Mayo
				Mes mayoC = new Mes();
				
				try {
			mayoC = mes_svc.buscar(dato.getMesByMayo().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_mayoC = 0.0;
				
				ahorro_mayoC = (double) (mayoC.getD10()+mayoC.getD2()+mayoC.getD3()+mayoC.getD4()+mayoC.getD5()+mayoC.getD6()+mayoC.getD7()+mayoC.getD8()+
				mayoC.getD9()+mayoC.getD10()+mayoC.getD11()+mayoC.getD12()+mayoC.getD13()+mayoC.getD14()+mayoC.getD15()+mayoC.getD16()
						+mayoC.getD17()+mayoC.getD18()+mayoC.getD19()+mayoC.getD20()+mayoC.getD21()+mayoC.getD22()+mayoC.getD23()+mayoC.getD24()
						+mayoC.getD25()+mayoC.getD26()+mayoC.getD27()+mayoC.getD28()+mayoC.getD29()+mayoC.getD30()+mayoC.getD31());
				map.put(5, ahorro_mayoC);
					
		//Junio
				Mes junioC = new Mes();
				
				try {
			junioC = mes_svc.buscar(dato.getMesByJunio().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_junioC = 0.0;
				
				ahorro_junioC = (double) (junioC.getD10()+junioC.getD2()+junioC.getD3()+junioC.getD4()+junioC.getD5()+junioC.getD6()+junioC.getD7()+junioC.getD8()+
				junioC.getD9()+junioC.getD10()+junioC.getD11()+junioC.getD12()+junioC.getD13()+junioC.getD14()+junioC.getD15()+junioC.getD16()
						+junioC.getD17()+junioC.getD18()+junioC.getD19()+junioC.getD20()+junioC.getD21()+junioC.getD22()+junioC.getD23()+junioC.getD24()
						+junioC.getD25()+junioC.getD26()+junioC.getD27()+junioC.getD28()+junioC.getD29()+junioC.getD30()+junioC.getD31());
				map.put(6, ahorro_junioC);
		
		//Julio
				Mes julioC = new Mes();
				
				try {
			julioC = mes_svc.buscar(dato.getMesByJulio().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_julioC = 0.0;
				
				ahorro_julioC = (double) (julioC.getD10()+julioC.getD2()+julioC.getD3()+julioC.getD4()+julioC.getD5()+julioC.getD6()+julioC.getD7()+julioC.getD8()+
				julioC.getD9()+julioC.getD10()+julioC.getD11()+julioC.getD12()+julioC.getD13()+julioC.getD14()+julioC.getD15()+julioC.getD16()
						+julioC.getD17()+julioC.getD18()+julioC.getD19()+julioC.getD20()+julioC.getD21()+julioC.getD22()+julioC.getD23()+julioC.getD24()
						+julioC.getD25()+julioC.getD26()+julioC.getD27()+julioC.getD28()+julioC.getD29()+julioC.getD30()+julioC.getD31());
				map.put(7, ahorro_julioC);
		
		//Agosto
				Mes agostoC = new Mes();
				
				try {
			agostoC = mes_svc.buscar(dato.getMesByAgosto().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_agostoC = 0.0;
				
				ahorro_agostoC = (double) (agostoC.getD10()+agostoC.getD2()+agostoC.getD3()+agostoC.getD4()+agostoC.getD5()+agostoC.getD6()+agostoC.getD7()+agostoC.getD8()+
				agostoC.getD9()+agostoC.getD10()+agostoC.getD11()+agostoC.getD12()+agostoC.getD13()+agostoC.getD14()+agostoC.getD15()+agostoC.getD16()
						+agostoC.getD17()+agostoC.getD18()+agostoC.getD19()+agostoC.getD20()+agostoC.getD21()+agostoC.getD22()+agostoC.getD23()+agostoC.getD24()
						+agostoC.getD25()+agostoC.getD26()+agostoC.getD27()+agostoC.getD28()+agostoC.getD29()+agostoC.getD30()+agostoC.getD31());
				map.put(8, ahorro_agostoC);
					
		//Septiembre
				Mes septiembreC = new Mes();
				
				try {
			septiembreC = mes_svc.buscar(dato.getMesBySeptiembre().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_septiembreC = 0.0;
				
				ahorro_septiembreC = (double) (septiembreC.getD10()+septiembreC.getD2()+septiembreC.getD3()+septiembreC.getD4()+septiembreC.getD5()+septiembreC.getD6()+septiembreC.getD7()+septiembreC.getD8()+
				septiembreC.getD9()+septiembreC.getD10()+septiembreC.getD11()+septiembreC.getD12()+septiembreC.getD13()+septiembreC.getD14()+septiembreC.getD15()+septiembreC.getD16()
						+septiembreC.getD17()+septiembreC.getD18()+septiembreC.getD19()+septiembreC.getD20()+septiembreC.getD21()+septiembreC.getD22()+septiembreC.getD23()+septiembreC.getD24()
						+septiembreC.getD25()+septiembreC.getD26()+septiembreC.getD27()+septiembreC.getD28()+septiembreC.getD29()+septiembreC.getD30()+septiembreC.getD31());
				map.put(9, ahorro_septiembreC);
								
		
		//Octubre
				Mes octubreC = new Mes();
				
				try {
			octubreC = mes_svc.buscar(dato.getMesByOctubre().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_octubreC = 0.0;
				
				ahorro_octubreC = (double) (octubreC.getD10()+octubreC.getD2()+octubreC.getD3()+octubreC.getD4()+octubreC.getD5()+octubreC.getD6()+octubreC.getD7()+octubreC.getD8()+
				octubreC.getD9()+octubreC.getD10()+octubreC.getD11()+octubreC.getD12()+octubreC.getD13()+octubreC.getD14()+octubreC.getD15()+octubreC.getD16()
						+octubreC.getD17()+octubreC.getD18()+octubreC.getD19()+octubreC.getD20()+octubreC.getD21()+octubreC.getD22()+octubreC.getD23()+octubreC.getD24()
						+octubreC.getD25()+octubreC.getD26()+octubreC.getD27()+octubreC.getD28()+octubreC.getD29()+octubreC.getD30()+octubreC.getD31());
				map.put(10, ahorro_octubreC);
				
		//Noviembre
				Mes noviembreC = new Mes();
				
				try {
			noviembreC = mes_svc.buscar(dato.getMesByNoviembre().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_noviembreC = 0.0;
				
				ahorro_noviembreC = (double) (noviembreC.getD10()+noviembreC.getD2()+noviembreC.getD3()+noviembreC.getD4()+noviembreC.getD5()+noviembreC.getD6()+noviembreC.getD7()+noviembreC.getD8()+
				noviembreC.getD9()+noviembreC.getD10()+noviembreC.getD11()+noviembreC.getD12()+noviembreC.getD13()+noviembreC.getD14()+noviembreC.getD15()+noviembreC.getD16()
						+noviembreC.getD17()+noviembreC.getD18()+noviembreC.getD19()+noviembreC.getD20()+noviembreC.getD21()+noviembreC.getD22()+noviembreC.getD23()+noviembreC.getD24()
						+noviembreC.getD25()+noviembreC.getD26()+noviembreC.getD27()+noviembreC.getD28()+noviembreC.getD29()+noviembreC.getD30()+noviembreC.getD31());
				map.put(11, ahorro_noviembreC);
								
				
		//Diciembre
				Mes diciembreC = new Mes();
				
				try {
			diciembreC = mes_svc.buscar(dato.getMesByDiciembre().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_diciembreC = 0.0;
				
				ahorro_diciembreC = (double) (diciembreC.getD10()+diciembreC.getD2()+diciembreC.getD3()+diciembreC.getD4()+diciembreC.getD5()+diciembreC.getD6()+diciembreC.getD7()+diciembreC.getD8()+
				diciembreC.getD9()+diciembreC.getD10()+diciembreC.getD11()+diciembreC.getD12()+diciembreC.getD13()+diciembreC.getD14()+diciembreC.getD15()+diciembreC.getD16()
						+diciembreC.getD17()+diciembreC.getD18()+diciembreC.getD19()+diciembreC.getD20()+diciembreC.getD21()+diciembreC.getD22()+diciembreC.getD23()+diciembreC.getD24()
						+diciembreC.getD25()+diciembreC.getD26()+diciembreC.getD27()+diciembreC.getD28()+diciembreC.getD29()+diciembreC.getD30()+diciembreC.getD31());
				map.put(12, ahorro_diciembreC);
		

				//Ciclos
				
				//Enero
				CiclosMes eneroC = new CiclosMes();
				
				try {
					eneroC = ciclos_mes_svc.buscar(ciclo.getCiclosMesByEnero().getId());
				} catch (SvcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Double ahorro_eneroC = 0.0;
				
				ahorro_eneroC = (double) (eneroC.getD10()+eneroC.getD2()+eneroC.getD3()+eneroC.getD4()+eneroC.getD5()+eneroC.getD6()+eneroC.getD7()+eneroC.getD8()+
						eneroC.getD9()+eneroC.getD10()+eneroC.getD11()+eneroC.getD12()+eneroC.getD13()+eneroC.getD14()+eneroC.getD15()+eneroC.getD16()
						+eneroC.getD17()+eneroC.getD18()+eneroC.getD19()+eneroC.getD20()+eneroC.getD21()+eneroC.getD22()+eneroC.getD23()+eneroC.getD24()
						+eneroC.getD25()+eneroC.getD26()+eneroC.getD27()+eneroC.getD28()+eneroC.getD29()+eneroC.getD30()+eneroC.getD31());
				map_ciclos.put(1, ahorro_eneroC);
				
				//Febrero
						CiclosMes febreroC = new CiclosMes();
						
						try {
					febreroC = ciclos_mes_svc.buscar(ciclo.getCiclosMesByFebrero().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_febreroC = 0.0;
						
						ahorro_febreroC = (double) (febreroC.getD10()+febreroC.getD2()+febreroC.getD3()+febreroC.getD4()+febreroC.getD5()+febreroC.getD6()+febreroC.getD7()+febreroC.getD8()+
						febreroC.getD9()+febreroC.getD10()+febreroC.getD11()+febreroC.getD12()+febreroC.getD13()+febreroC.getD14()+febreroC.getD15()+febreroC.getD16()
								+febreroC.getD17()+febreroC.getD18()+febreroC.getD19()+febreroC.getD20()+febreroC.getD21()+febreroC.getD22()+febreroC.getD23()+febreroC.getD24()
								+febreroC.getD25()+febreroC.getD26()+febreroC.getD27()+febreroC.getD28()+febreroC.getD29()+febreroC.getD30()+febreroC.getD31());
						map_ciclos.put(2, ahorro_febreroC);
						
				//Marzo
						CiclosMes marzoC = new CiclosMes();
						
						try {
					marzoC = ciclos_mes_svc.buscar(ciclo.getCiclosMesByMarzo().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_marzoC = 0.0;
						
						ahorro_marzoC = (double) (marzoC.getD10()+marzoC.getD2()+marzoC.getD3()+marzoC.getD4()+marzoC.getD5()+marzoC.getD6()+marzoC.getD7()+marzoC.getD8()+
						marzoC.getD9()+marzoC.getD10()+marzoC.getD11()+marzoC.getD12()+marzoC.getD13()+marzoC.getD14()+marzoC.getD15()+marzoC.getD16()
								+marzoC.getD17()+marzoC.getD18()+marzoC.getD19()+marzoC.getD20()+marzoC.getD21()+marzoC.getD22()+marzoC.getD23()+marzoC.getD24()
								+marzoC.getD25()+marzoC.getD26()+marzoC.getD27()+marzoC.getD28()+marzoC.getD29()+marzoC.getD30()+marzoC.getD31());
						map_ciclos.put(3, ahorro_marzoC);
						
				//Abril
						CiclosMes abrilC = new CiclosMes();
						
						try {
					abrilC = ciclos_mes_svc.buscar(ciclo.getCiclosMesByAbril().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_abrilC = 0.0;
						
						ahorro_abrilC = (double) (abrilC.getD10()+abrilC.getD2()+abrilC.getD3()+abrilC.getD4()+abrilC.getD5()+abrilC.getD6()+abrilC.getD7()+abrilC.getD8()+
						abrilC.getD9()+abrilC.getD10()+abrilC.getD11()+abrilC.getD12()+abrilC.getD13()+abrilC.getD14()+abrilC.getD15()+abrilC.getD16()
								+abrilC.getD17()+abrilC.getD18()+abrilC.getD19()+abrilC.getD20()+abrilC.getD21()+abrilC.getD22()+abrilC.getD23()+abrilC.getD24()
								+abrilC.getD25()+abrilC.getD26()+abrilC.getD27()+abrilC.getD28()+abrilC.getD29()+abrilC.getD30()+abrilC.getD31());
						map_ciclos.put(4, ahorro_abrilC);
						
				//Mayo
						CiclosMes mayo = new CiclosMes();
						
						try {
					mayo = ciclos_mes_svc.buscar(ciclo.getCiclosMesByMayo().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_mayo = 0.0;
						
						ahorro_mayo = (double) (mayo.getD10()+mayo.getD2()+mayo.getD3()+mayo.getD4()+mayo.getD5()+mayo.getD6()+mayo.getD7()+mayo.getD8()+
						mayo.getD9()+mayo.getD10()+mayo.getD11()+mayo.getD12()+mayo.getD13()+mayo.getD14()+mayo.getD15()+mayo.getD16()
								+mayo.getD17()+mayo.getD18()+mayo.getD19()+mayo.getD20()+mayo.getD21()+mayo.getD22()+mayo.getD23()+mayo.getD24()
								+mayo.getD25()+mayo.getD26()+mayo.getD27()+mayo.getD28()+mayo.getD29()+mayo.getD30()+mayo.getD31());
						map_ciclos.put(5, ahorro_mayo);
							
				//Junio
						CiclosMes junio = new CiclosMes();
						
						try {
					junio = ciclos_mes_svc.buscar(ciclo.getCiclosMesByJunio().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_junio = 0.0;
						
						ahorro_junio = (double) (junio.getD10()+junio.getD2()+junio.getD3()+junio.getD4()+junio.getD5()+junio.getD6()+junio.getD7()+junio.getD8()+
						junio.getD9()+junio.getD10()+junio.getD11()+junio.getD12()+junio.getD13()+junio.getD14()+junio.getD15()+junio.getD16()
								+junio.getD17()+junio.getD18()+junio.getD19()+junio.getD20()+junio.getD21()+junio.getD22()+junio.getD23()+junio.getD24()
								+junio.getD25()+junio.getD26()+junio.getD27()+junio.getD28()+junio.getD29()+junio.getD30()+junio.getD31());
						map_ciclos.put(6, ahorro_junio);
				
				//Julio
						CiclosMes julio = new CiclosMes();
						
						try {
					julio = ciclos_mes_svc.buscar(ciclo.getCiclosMesByJulio().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_julio = 0.0;
						
						ahorro_julio = (double) (julio.getD10()+julio.getD2()+julio.getD3()+julio.getD4()+julio.getD5()+julio.getD6()+julio.getD7()+julio.getD8()+
						julio.getD9()+julio.getD10()+julio.getD11()+julio.getD12()+julio.getD13()+julio.getD14()+julio.getD15()+julio.getD16()
								+julio.getD17()+julio.getD18()+julio.getD19()+julio.getD20()+julio.getD21()+julio.getD22()+julio.getD23()+julio.getD24()
								+julio.getD25()+julio.getD26()+julio.getD27()+julio.getD28()+julio.getD29()+julio.getD30()+julio.getD31());
						map_ciclos.put(7, ahorro_julio);
				
				//Agosto
						CiclosMes agosto = new CiclosMes();
						
						try {
					agosto = ciclos_mes_svc.buscar(ciclo.getCiclosMesByAgosto().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_agosto = 0.0;
						
						ahorro_agosto = (double) (agosto.getD10()+agosto.getD2()+agosto.getD3()+agosto.getD4()+agosto.getD5()+agosto.getD6()+agosto.getD7()+agosto.getD8()+
						agosto.getD9()+agosto.getD10()+agosto.getD11()+agosto.getD12()+agosto.getD13()+agosto.getD14()+agosto.getD15()+agosto.getD16()
								+agosto.getD17()+agosto.getD18()+agosto.getD19()+agosto.getD20()+agosto.getD21()+agosto.getD22()+agosto.getD23()+agosto.getD24()
								+agosto.getD25()+agosto.getD26()+agosto.getD27()+agosto.getD28()+agosto.getD29()+agosto.getD30()+agosto.getD31());
						map_ciclos.put(8, ahorro_agosto);
							
				//Septiembre
						CiclosMes septiembre = new CiclosMes();
						
						try {
					septiembre = ciclos_mes_svc.buscar(ciclo.getCiclosMesBySeptiembre().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_septiembre = 0.0;
						
						ahorro_septiembre = (double) (septiembre.getD10()+septiembre.getD2()+septiembre.getD3()+septiembre.getD4()+septiembre.getD5()+septiembre.getD6()+septiembre.getD7()+septiembre.getD8()+
						septiembre.getD9()+septiembre.getD10()+septiembre.getD11()+septiembre.getD12()+septiembre.getD13()+septiembre.getD14()+septiembre.getD15()+septiembre.getD16()
								+septiembre.getD17()+septiembre.getD18()+septiembre.getD19()+septiembre.getD20()+septiembre.getD21()+septiembre.getD22()+septiembre.getD23()+septiembre.getD24()
								+septiembre.getD25()+septiembre.getD26()+septiembre.getD27()+septiembre.getD28()+septiembre.getD29()+septiembre.getD30()+septiembre.getD31());
						map_ciclos.put(9, ahorro_septiembre);
										
				
				//Octubre
						CiclosMes octubre = new CiclosMes();
						
						try {
					octubre = ciclos_mes_svc.buscar(ciclo.getCiclosMesByOctubre().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_octubre = 0.0;
						
						ahorro_octubre = (double) (octubre.getD10()+octubre.getD2()+octubre.getD3()+octubre.getD4()+octubre.getD5()+octubre.getD6()+octubre.getD7()+octubre.getD8()+
						octubre.getD9()+octubre.getD10()+octubre.getD11()+octubre.getD12()+octubre.getD13()+octubre.getD14()+octubre.getD15()+octubre.getD16()
								+octubre.getD17()+octubre.getD18()+octubre.getD19()+octubre.getD20()+octubre.getD21()+octubre.getD22()+octubre.getD23()+octubre.getD24()
								+octubre.getD25()+octubre.getD26()+octubre.getD27()+octubre.getD28()+octubre.getD29()+octubre.getD30()+octubre.getD31());
						map_ciclos.put(10, ahorro_octubre);
						
				//Noviembre
						CiclosMes noviembre = new CiclosMes();
						
						try {
					noviembre = ciclos_mes_svc.buscar(ciclo.getCiclosMesByNoviembre().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_noviembre = 0.0;
						
						ahorro_noviembre = (double) (noviembre.getD10()+noviembre.getD2()+noviembre.getD3()+noviembre.getD4()+noviembre.getD5()+noviembre.getD6()+noviembre.getD7()+noviembre.getD8()+
						noviembre.getD9()+noviembre.getD10()+noviembre.getD11()+noviembre.getD12()+noviembre.getD13()+noviembre.getD14()+noviembre.getD15()+noviembre.getD16()
								+noviembre.getD17()+noviembre.getD18()+noviembre.getD19()+noviembre.getD20()+noviembre.getD21()+noviembre.getD22()+noviembre.getD23()+noviembre.getD24()
								+noviembre.getD25()+noviembre.getD26()+noviembre.getD27()+noviembre.getD28()+noviembre.getD29()+noviembre.getD30()+noviembre.getD31());
						map_ciclos.put(11, ahorro_noviembre);
										
						
				//Diciembre
						CiclosMes diciembre = new CiclosMes();
						
						try {
					diciembre = ciclos_mes_svc.buscar(ciclo.getCiclosMesByDiciembre().getId());
						} catch (SvcException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Double ahorro_diciembre = 0.0;
						
						ahorro_diciembre = (double) (diciembre.getD10()+diciembre.getD2()+diciembre.getD3()+diciembre.getD4()+diciembre.getD5()+diciembre.getD6()+diciembre.getD7()+diciembre.getD8()+
						diciembre.getD9()+diciembre.getD10()+diciembre.getD11()+diciembre.getD12()+diciembre.getD13()+diciembre.getD14()+diciembre.getD15()+diciembre.getD16()
								+diciembre.getD17()+diciembre.getD18()+diciembre.getD19()+diciembre.getD20()+diciembre.getD21()+diciembre.getD22()+diciembre.getD23()+diciembre.getD24()
								+diciembre.getD25()+diciembre.getD26()+diciembre.getD27()+diciembre.getD28()+diciembre.getD29()+diciembre.getD30()+diciembre.getD31());
						map_ciclos.put(12, ahorro_diciembre);
		
	
		
		String date ="[" ;
		String value ="[" ;
		String ttvalue ="[" ;
		String tciclovalue ="[" ;
		String nciclos ="[" ;
		for (int i=1; i<=12;i++) {
			date+= "\"" + i + "\"";
	//		value+= "\"" + map.get(i) + "\"";
			value+= "\"" + map.get(i)*0.264172 + "\""; //Galones
	//		ttvalue+= "\"" + map.get(i)/20 + "\"";
			ttvalue+= "\"" + map.get(i)*0.264172/20 + "\""; //Galones
			tciclovalue+= "\"" + (map.get(i)/20)/map_ciclos.get(i)*60+ "\"";
			nciclos+= "\"" + map_ciclos.get(i) + "\"";
			if(i<=12-1) {
				date+=",";
				value+=",";
				ttvalue+=",";
				tciclovalue+=",";
				nciclos+=",";
			}
		}
		date+="],";
		value+="],";
		ttvalue+="],";
		tciclovalue+="],";
		nciclos+="]";
		
		
		
		String json = "{\"date\":" + date + "\"value\":" + value + "\"ttvalue\":" + ttvalue + "\"tciclovalue\":" + tciclovalue + "\"nciclos\":" + nciclos +"}";
				
		

			model.addAttribute(ATT_LISTA,json);
		
		return SUCCESS;
		
	}
}
