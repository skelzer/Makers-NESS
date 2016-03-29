package metrica6.artik.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.Ciclos;
import metrica6.artik.model.Datos;
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
@RequestMapping(value = "/remove")
public class Eliminar {
	
	
	@Autowired
	private BypassSvc bypass_svc;
	
	@Autowired
	private CiclosSvc ciclos_svc;
	
	@Autowired
	private CiclosMesSvc ciclosMes_svc;
	
	@Autowired
	private DatosSvc datos_svc;
	
	@Autowired
	private MesSvc mes_svc;
	
	//Atributos
	private static final String ATT_LISTA = "lista";
	
	//URL
	private static final String SUCCESS = "eliminar";

	@RequestMapping(method=RequestMethod.GET)
    public String view(Model model,HttpSession session,@RequestParam Integer id) {
		
		Bypass aux=null;
		try {
			aux = bypass_svc.buscar(id);
		} catch (SvcException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			ArrayList<Ciclos> l_ciclos = (ArrayList<Ciclos>) ciclos_svc.buscarBypass(id);
			for (int i = 0; i < l_ciclos.size(); i++) {
				ciclos_svc.eliminar(l_ciclos.get(i).getId());	
			}
		} catch (SvcException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		try {
			ArrayList<Datos> l_datos = (ArrayList<Datos>) datos_svc.buscarBypass(id);
			for (int i = 0; i < l_datos.size(); i++) {
				datos_svc.eliminar(l_datos.get(i).getId());	
			}
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
		try {
			bypass_svc.eliminar(id);
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			model.addAttribute(ATT_LISTA,bypass_svc.listar());
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
}
