package wikidrinks;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("session")
public class ControladorWikidrinks {

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Autowired
   private TragoRepository tragoRepository;
   
   @RequestMapping("/login")
   public String login(Model model, @CookieValue(name = "idUser", required = false) Integer idUser){
	   if(idUser != null) {
		   model.addAttribute("loggedUser", usuarioRepository.findById(idUser));
	   }
	   return "login";
   }
   
   @RequestMapping("/logout")
   @ResponseBody
   public String login(HttpServletResponse response, HttpServletRequest request){
	   Cookie[] cookies = request.getCookies();
	   for (Cookie cookie : cookies) {
		if(cookie.getName().equalsIgnoreCase("idUser")){
			cookie.setValue("");
	        cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	   return "OK";
   }
   
   @RequestMapping(value = "/validar-login", produces = "text/plain")
   @ResponseBody
   public String validarLogin(@RequestParam String userMail, @RequestParam String pass, HttpServletResponse response){
	   Usuario user = usuarioRepository.findByUsername(userMail);
	   
	   if(user == null) {
		   user = usuarioRepository.findByMail(userMail);
	   }
	   if(user == null) {
		   return "No existe ese usuario.";
	   }
	   if(user.getPassword().equals(pass)) {
		   response.addCookie(new Cookie("idUser", user.getId().toString()));
		   return "OK";
	   }else {
		   return "Datos incorrectos.";
	   }
   }
   
   @RequestMapping("/registro")
   public String registro(Model model, @CookieValue(name = "idUser", required = false) Integer idUser){
	   if(idUser != null) {
		   model.addAttribute("loggedUser", usuarioRepository.findById(idUser));
	   }
	   model.addAttribute("pantallaActual", "registro");
	   return "registro";
   }
   @RequestMapping("/registrar-usuario")
   @ResponseBody
   public String registrarUsuario(@RequestBody RegistroDTO registro){
	   Usuario u = usuarioRepository.findByUsername(registro.getUsername());
	   Usuario u2 = usuarioRepository.findByMail(registro.getEmail());
	   if(u != null || u2 != null){
		   return "Ya existe ese usuario o email";
	   }
	   if(!registro.getPass().equals(registro.getPassConfirmacion())) {
		   return "Las passwords no coinciden";
	   }
	   Usuario nuevoUser = new Usuario();
	   nuevoUser.setActivo(true);
	   nuevoUser.setApellido(registro.getApellido());
	   nuevoUser.setDireccion(registro.getDireccion());
	   nuevoUser.setMail(registro.getEmail());
	   nuevoUser.setNombre(registro.getNombre());
	   nuevoUser.setPassword(registro.getPass());
	   nuevoUser.setTelefono(registro.getTelefono());
	   nuevoUser.setUsername(registro.getUsername());
	   usuarioRepository.save(nuevoUser);
	   return "OK";
   }
   
   
//   @RequestMapping(value = "/loguear-usuario", produces = "text/plain")
//   @ResponseBody
//   public String loguearUsuario(@RequestParam String userMail, @RequestParam String pass, HttpSession session) throws JsonGenerationException, JsonMappingException, IOException{
//	   Usuario user = usuarioRepository.findByUsername(userMail);
//	   if(user == null) {
//		   user = usuarioRepository.findByMail(userMail);
//	   }
//	   ObjectMapper mapper = new ObjectMapper();
//	   String jsonUser = mapper.writeValueAsString(user);
//	   return jsonUser;
//   }
   
	@RequestMapping("/inicial")
	public String pantallaInicial(@CookieValue(name = "idUser", required = false) Integer idUser, Model model){
		model.addAttribute("loggedUser", usuarioRepository.findById(idUser));
		model.addAttribute("pantallaActual", "home");
		return "pantallaInicial";
	}
	
	@RequestMapping("/tragos")
	public String tragos(@CookieValue(name = "idUser", required = false) Integer idUser, Model model){
		model.addAttribute("loggedUser", usuarioRepository.findById(idUser));
		model.addAttribute("pantallaActual", "tragos");
		return "tragos";
	}
	
	@RequestMapping("/listar-tragos")
	public String listarComprobantes(Model model){
		List<Trago> tragos = tragoRepository.findAll();
		List<Trago> tragosReturn = new ArrayList<>();
		for (Trago trago : tragos) {
			if(trago.getActivo()) {
				tragosReturn.add(trago);
			}
		}
		model.addAttribute("tragos", tragosReturn);
		return "ListaTragos";
	}
	
	@RequestMapping("/crear-trago")
	@ResponseBody
	public String crearTrago(@RequestParam String nombre, @RequestParam float grad, @RequestParam float punt, 
			@RequestParam String urlImagen, @RequestParam String vaso, @RequestParam Integer idUsuario) {
		
		Trago trago = new Trago();
		trago.setNombre(nombre);
		trago.setVaso(vaso);
		trago.setGraduacion(grad);
		trago.setPuntuacion(punt);
		trago.setImagen(urlImagen);
		trago.setActivo(true);
		Usuario u = usuarioRepository.findById(idUsuario);
		trago.setUsuario(u);
		
		tragoRepository.save(trago);
		
		return "OK";
	}
	
	@RequestMapping("/usuarios")
	public String usuarios(Model model){
		model.addAttribute("usuario", new Usuario());
		return "EditorUsuarios";
	}
//	
//	@RequestMapping("/filtrar-comprobantes")
//	public String filtrarComprobantes(Model model, @RequestParam Integer numero, @RequestParam String cuit, 
//			@RequestParam String fecha, @RequestParam String estado, HttpSession session){
//		List<Comprobante> comprobantes = comprobanteRepository.findAll();
//		List<Comprobante> comprobantes2 = new ArrayList<>();
//		for (Comprobante comprobante : comprobantes) {
//			if(pasaFiltro(comprobante, numero, cuit, fecha, estado) && !comprobante.getEstado().equalsIgnoreCase("INACTIVO")) {
//				comprobantes2.add(comprobante);
//			}
//		}
//		model.addAttribute("comprobantes", comprobantes2);
//		return "ListaComprobantes";
//	}
//	
//	@RequestMapping("/eliminar-comprobante")
//	@ResponseStatus(value = HttpStatus.OK)
//	public void eliminarComprobante(@RequestParam Integer id){
//		Comprobante c = comprobanteRepository.findById(id);
//		c.setEstado("INACTIVO");
//		comprobanteRepository.save(c);
//	}
//	
//	@RequestMapping("/enviar-mail")
//	@ResponseStatus(value = HttpStatus.OK)
//	public void enviarMail(@RequestParam Integer id) throws MessagingException{
//		Comprobante c = comprobanteRepository.findById(id);
//		MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        
//        helper.setTo("tomasfw94@gmail.com");
//        helper.setSubject("Estado de Comprobante número "+c.getId());
//        helper.setText("Su comprobante "+c.getId()+" se encuentra en estado "+c.getEstado());
//        
//        sender.send(message);
//	}
//	
//	@RequestMapping("/modificar-comprobante/{id}")
//	public String modificarComprobante(@PathVariable Integer id, Model model){
//		Comprobante comprobante = comprobanteRepository.findById(id);
//		model.addAttribute("comprobante", comprobante);
//		return "ModificarComprobante";
//	}
//	
//	@RequestMapping("/guardar-comprobante")
//	public String guardarComprobante(@ModelAttribute Comprobante comprobante){
//		Comprobante c = comprobanteRepository.findById(comprobante.getId());
//		comprobante.setFecha(c.getFecha());
//		comprobante.setProveedor(c.getProveedor());
//		comprobanteRepository.save(comprobante);
//		return comprobantes();
//	}
//	
//	@RequestMapping("/proveedores")
//	public String proveedores(){
//		return "Proveedores";
//	}
//	
//	@RequestMapping("/listar-proveedores")
//	public String listarProveedores(Model model){
//		List<Proveedor> prov = proveedorRepository.findAll();
//		List<Proveedor> prov2 = new ArrayList<>();
//		for (Proveedor proveedor : prov) {
//			if(proveedor.getActivo()) {
//				prov2.add(proveedor);
//			}
//		}
//		model.addAttribute("proveedores", prov2);
//		return "ListaProveedores";
//	}
//	
//	@RequestMapping("/filtrar-proveedores")
//	public String filtrarProveedores(Model model, @RequestParam String cuit){
//		List<Proveedor> prov = proveedorRepository.findAll();
//		List<Proveedor> prov2 = new ArrayList<>();
//		for (Proveedor proveedor : prov) {
//			if(proveedor.getCuit().equalsIgnoreCase(cuit) && proveedor.getActivo()) {
//				prov2.add(proveedor);
//			}
//		}
//		model.addAttribute("proveedores", prov2);
//		return "ListaProveedores";
//	}
//	
//	@RequestMapping("/eliminar-proveedor")
//	@ResponseStatus(value = HttpStatus.OK)
//	public void eliminarProveedor(@RequestParam Integer id){
//		Proveedor prov = proveedorRepository.findById(id);
//		prov.setActivo(false);;
//		proveedorRepository.save(prov);
//	}
//	
//	@RequestMapping("/modificar-proveedor/{id}")
//	public String modificarProveedor(@PathVariable Integer id, Model model){
//		Proveedor prov = proveedorRepository.findById(id);
//		model.addAttribute("proveedor", prov);
//		return "EditorProveedor";
//	}
//	
//	@RequestMapping("/nuevo-proveedor")
//	public String nuevoProveedor(Model model){
//		model.addAttribute("proveedor", new Proveedor());
//		return "EditorProveedor";
//	}
//	
//	@RequestMapping("/guardar-proveedor")
//	public String guardarProveedor(@ModelAttribute Proveedor proveedor){
//		proveedor.setActivo(true);
//		proveedorRepository.save(proveedor);
//		return proveedores();
//	}
//	
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
	
		
	@RequestMapping("/guardar-usuario")
	public String guardarUsuario(@ModelAttribute Usuario usuario, Model model){
		usuario.setActivo(true);
		usuarioRepository.save(usuario);
		return usuarios(model);
	}
	
//	private boolean pasaFiltro(Comprobante comprobante, Integer numero, String cuit, String fecha, String estado) {
//		Boolean pasa = true;
//		if(numero != null && !comprobante.getId().equals(numero)){
//			pasa = false;
//		}		
//		if(!cuit.isEmpty() && !comprobante.getProveedor().getCuit().equalsIgnoreCase(cuit)){
//			pasa = false;
//		}		
//		String fechaComp = new SimpleDateFormat("yyyy-MM-dd").format(comprobante.getFecha());
//		if(!fecha.isEmpty() && !fechaComp.equalsIgnoreCase(fecha)){
//			pasa = false;
//		}		
//		if(!estado.isEmpty() && !comprobante.getEstado().equalsIgnoreCase(estado)){
//			pasa = false;
//		}
//		return pasa;
//	}
}
