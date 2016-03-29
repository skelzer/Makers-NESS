package metrica6.artik.controller;

import javax.servlet.http.HttpSession;

import metrica6.artik.model.Bypass;
import metrica6.artik.model.TipoEstado;
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
@RequestMapping(value = "/add")
public class Agregar {
	
	
	@Autowired
	private BypassSvc bypass_svc;
	
	@Autowired
	private TipoEstadoSvc tipo_svc;
	
	//Atributos
	private static final String ATT_LISTA = "lista";
	
	//URL
	private static final String SUCCESS = "lista";

	@RequestMapping(method=RequestMethod.GET)
    public String view(@RequestParam String name,@RequestParam String num,Model model,HttpSession session) {
		
		Bypass aux = new Bypass();
		aux.setModelo("Bypass");
		aux.setNombre(name);
		
		aux.setFinCiclo(0);
		
		TipoEstado t = new TipoEstado();
		try {
			t = tipo_svc.buscar(0);
		} catch (SvcException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		aux.setTipoEstado(t);
		aux.setNumeroSerie(Long.parseLong(num));
		
		try {
			bypass_svc.insertarModificar(aux);
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
