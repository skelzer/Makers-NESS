package metrica6.artik.services;

import java.util.List;

import metrica6.artik.model.Bypass;
import metrica6.artik.services.SvcException;

public interface BypassSvc {


		public List<Bypass> listar() throws SvcException;

		public Bypass buscar(Integer id) throws SvcException;

		public void insertarModificar(Bypass bypass) throws SvcException;
		
		public void eliminar(Integer id) throws SvcException;

	
}
