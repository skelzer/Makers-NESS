package metrica6.artik.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import metrica6.artik.model.Bypass;
import metrica6.artik.services.BypassSvc;
import metrica6.artik.services.SvcException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/nodos")
public class Nodos {
	
	
	@Autowired
	private BypassSvc bypass_svc;
	
	//Atributos
	private static final String ATT_LISTA = "json";
	
	//URL
	private static final String SUCCESS = "nodos";

	@RequestMapping(method=RequestMethod.GET)
    public String view(/*@ModelAttribute Usuarios usuarios,*/ Model model,HttpSession session) {
		
		ArrayList<Bypass> list = new ArrayList<Bypass>();
		
		try {
			list = (ArrayList<Bypass>) bypass_svc.listar();
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Integer max = list.size();
		
		String ids ="[" ;
		String nombres ="[" ;
		for (int i=0; i<max;i++) {
			ids+= "\"" + list.get(i).getId() + "\"";
			nombres+= "\"" + list.get(i).getNombre() + "\"";
			if(i<max-1) {
				ids+=",";
				nombres+=",";
			}
		}
		ids+="],";
		nombres+="]";
		
		String json = "{\"cantidad\":" + max +",\"IDArtik\":" + ids + "\"NombreArtik\":" + nombres +"}";
				
		
		System.out.println(list);
			model.addAttribute(ATT_LISTA,json);
	
		return SUCCESS;
		
	}
}
