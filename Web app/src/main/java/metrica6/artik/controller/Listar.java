package metrica6.artik.controller;

import javax.servlet.http.HttpSession;
import metrica6.artik.services.BypassSvc;
import metrica6.artik.services.SvcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/lista")
public class Listar {
	

	@Autowired
	private BypassSvc bypass_svc;
	
	//Atributos
	private static final String ATT_LISTA = "lista";
	
	//URL
	private static final String SUCCESS = "lista";

	@RequestMapping(method=RequestMethod.GET)
    public String view(/*@ModelAttribute Usuarios usuarios,*/ Model model,HttpSession session) {
		
		try {
			model.addAttribute(ATT_LISTA,bypass_svc.listar());
		} catch (SvcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
}
