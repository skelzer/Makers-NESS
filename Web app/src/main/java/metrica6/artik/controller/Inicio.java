package metrica6.artik.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/inicio")
public class Inicio {

	@Autowired  
    private MessageSource messageSource;

	//log4j handler
	private static final Logger logger = LoggerFactory.getLogger(Inicio.class);
	
	//Atributos
	private static final String ATT_ERROR = "msg";
	
	//URL
	private static final String SUCCESS = "inicio";

	
	
	@RequestMapping(method=RequestMethod.GET)
    public String view(Model model) {
		
		try {
			logger.info("inicio");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute(ATT_ERROR, e);
		}
		return SUCCESS;		
	}
}
