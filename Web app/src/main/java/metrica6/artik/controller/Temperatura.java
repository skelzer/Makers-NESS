package metrica6.artik.controller;

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
@RequestMapping(value = "/temp")
public class Temperatura {
	
	
	@Autowired
	private BypassSvc bypass_svc;
	
	@Autowired
	private TipoEstadoSvc tipo_svc;
	
	//Atributos
	private static final String ATT_LISTA = "temp";
	
	//URL
	private static final String SUCCESS = "temp";

	@RequestMapping(method=RequestMethod.GET)
    public String view(@RequestParam Integer id,@RequestParam Integer t,Model model,HttpSession session) {
		
		Bypass server = null;
		
		try {
			server = bypass_svc.buscar(id);
		} catch (SvcException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		server.setTemp(t);
		
		try {
			bypass_svc.insertarModificar(server);
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute(ATT_LISTA,server.getTemp());
		return SUCCESS;
		
	}
}