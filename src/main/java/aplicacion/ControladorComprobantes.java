package aplicacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import comprobantes.Comprobante;
import comprobantes.ComprobanteRepository;
import comprobantes.Proveedor;
import comprobantes.ProveedorRepository;
import comprobantes.Usuario;
import comprobantes.UsuarioRepository;

@Controller
public class ControladorComprobantes {
	
   private static final List<Usuario> NULL = null;

@Autowired
   private ComprobanteRepository comprobanteRepository;
   
   @Autowired
   private ProveedorRepository proveedorRepository;
   
   @Autowired
   private UsuarioRepository usuarioRepository;
   
   @Autowired
   private JavaMailSender sender;

   @RequestMapping("/login")
   public String login(){
	   return "Login";
   }
   
   @RequestMapping(value = "/loguearse", produces = "text/plain")
   @ResponseBody
   public String loguearse(@RequestParam String username, @RequestParam String pass){
	   Usuario u = usuarioRepository.findByUsername(username);
	   if(u == null) {
		   return "No existe ese usuario.";
	   }
	   if(u.getPass().equals(pass)) {
		   return "OK";
	   }else {
		   return "Datos incorrectos.";
	   }
   }
   
	@RequestMapping("/inicial")
	public String menuInicial(){
		return "PantallaInicial";
	}
	
	@RequestMapping("/comprobantes")
	public String comprobantes(){
		return "Comprobantes";
	}
	
	@RequestMapping("/listar-comprobantes")
	public String listarComprobantes(Model model){
		List<Comprobante> comprobantes = comprobanteRepository.findAll();
		List<Comprobante> comprobantes2 = new ArrayList<>();
		for (Comprobante comprobante : comprobantes) {
			if(!comprobante.getEstado().equalsIgnoreCase("INACTIVO")) {
				comprobantes2.add(comprobante);
			}
		}
		model.addAttribute("comprobantes", comprobantes2);
		return "ListaComprobantes";
	}
	
	@RequestMapping("/filtrar-comprobantes")
	public String filtrarComprobantes(Model model, @RequestParam Integer numero, @RequestParam String cuit, 
			@RequestParam String fecha, @RequestParam String estado, HttpSession session){
		List<Comprobante> comprobantes = comprobanteRepository.findAll();
		List<Comprobante> comprobantes2 = new ArrayList<>();
		for (Comprobante comprobante : comprobantes) {
			if(pasaFiltro(comprobante, numero, cuit, fecha, estado) && !comprobante.getEstado().equalsIgnoreCase("INACTIVO")) {
				comprobantes2.add(comprobante);
			}
		}
		model.addAttribute("comprobantes", comprobantes2);
		return "ListaComprobantes";
	}
	
	@RequestMapping("/eliminar-comprobante")
	@ResponseStatus(value = HttpStatus.OK)
	public void eliminarComprobante(@RequestParam Integer id){
		Comprobante c = comprobanteRepository.findById(id);
		c.setEstado("INACTIVO");
		comprobanteRepository.save(c);
	}
	
	@RequestMapping("/enviar-mail")
	@ResponseStatus(value = HttpStatus.OK)
	public void enviarMail(@RequestParam Integer id) throws MessagingException{
		Comprobante c = comprobanteRepository.findById(id);
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setTo("tomasfw94@gmail.com");
        helper.setSubject("Estado de Comprobante número "+c.getId());
        helper.setText("Su comprobante "+c.getId()+" se encuentra en estado "+c.getEstado());
        
        sender.send(message);
	}
	
	@RequestMapping("/modificar-comprobante/{id}")
	public String modificarComprobante(@PathVariable Integer id, Model model){
		Comprobante comprobante = comprobanteRepository.findById(id);
		model.addAttribute("comprobante", comprobante);
		return "ModificarComprobante";
	}
	
	@RequestMapping("/guardar-comprobante")
	public String guardarComprobante(@ModelAttribute Comprobante comprobante){
		Comprobante c = comprobanteRepository.findById(comprobante.getId());
		comprobante.setFecha(c.getFecha());
		comprobante.setProveedor(c.getProveedor());
		comprobanteRepository.save(comprobante);
		return comprobantes();
	}
	
	@RequestMapping("/proveedores")
	public String proveedores(){
		return "Proveedores";
	}
	
	@RequestMapping("/listar-proveedores")
	public String listarProveedores(Model model){
		List<Proveedor> prov = proveedorRepository.findAll();
		List<Proveedor> prov2 = new ArrayList<>();
		for (Proveedor proveedor : prov) {
			if(proveedor.getActivo()) {
				prov2.add(proveedor);
			}
		}
		model.addAttribute("proveedores", prov2);
		return "ListaProveedores";
	}
	
	@RequestMapping("/filtrar-proveedores")
	public String filtrarProveedores(Model model, @RequestParam String cuit){
		List<Proveedor> prov = proveedorRepository.findAll();
		List<Proveedor> prov2 = new ArrayList<>();
		for (Proveedor proveedor : prov) {
			if(proveedor.getCuit().equalsIgnoreCase(cuit) && proveedor.getActivo()) {
				prov2.add(proveedor);
			}
		}
		model.addAttribute("proveedores", prov2);
		return "ListaProveedores";
	}
	
	@RequestMapping("/eliminar-proveedor")
	@ResponseStatus(value = HttpStatus.OK)
	public void eliminarProveedor(@RequestParam Integer id){
		Proveedor prov = proveedorRepository.findById(id);
		prov.setActivo(false);;
		proveedorRepository.save(prov);
	}
	
	@RequestMapping("/modificar-proveedor/{id}")
	public String modificarProveedor(@PathVariable Integer id, Model model){
		Proveedor prov = proveedorRepository.findById(id);
		model.addAttribute("proveedor", prov);
		return "EditorProveedor";
	}
	
	@RequestMapping("/nuevo-proveedor")
	public String nuevoProveedor(Model model){
		model.addAttribute("proveedor", new Proveedor());
		return "EditorProveedor";
	}
	
	@RequestMapping("/guardar-proveedor")
	public String guardarProveedor(@ModelAttribute Proveedor proveedor){
		proveedor.setActivo(true);
		proveedorRepository.save(proveedor);
		return proveedores();
	}
	
	/*@RequestMapping("/listar-usuarios")
	public String listarusuarios(Model model){
		List<Usuario> usuario = usuarioRepository.findAll();
        List <Usuario> usuario2 = new ArrayList<>();
		for (Usuario user : usuario) {
			if(user.getActivo()) {
				usuario2.add(user);
			}
		}
		model.addAttribute("usuarios", usuario2);
		return "ListaUsuarios";
	}*/
	
		
	@RequestMapping("/usuarios")
	public String usuarios(Model model){
		model.addAttribute("usuario", new Usuario());
		return "EditorUsuarios";
	}
	
	@RequestMapping("/guardar-usuario")
	public String guardarUsuario(@ModelAttribute Usuario usuario, Model model){
		usuario.setActivo(true);
		usuarioRepository.save(usuario);
		return usuarios(model);
	}
	
	private boolean pasaFiltro(Comprobante comprobante, Integer numero, String cuit, String fecha, String estado) {
		Boolean pasa = true;
		if(numero != null && !comprobante.getId().equals(numero)){
			pasa = false;
		}		
		if(!cuit.isEmpty() && !comprobante.getProveedor().getCuit().equalsIgnoreCase(cuit)){
			pasa = false;
		}		
		String fechaComp = new SimpleDateFormat("yyyy-MM-dd").format(comprobante.getFecha());
		if(!fecha.isEmpty() && !fechaComp.equalsIgnoreCase(fecha)){
			pasa = false;
		}		
		if(!estado.isEmpty() && !comprobante.getEstado().equalsIgnoreCase(estado)){
			pasa = false;
		}
		return pasa;
	}
}
