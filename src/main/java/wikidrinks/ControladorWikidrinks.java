package wikidrinks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String listarTragos(@CookieValue(name = "idUser", required = false) Integer idUser, Model model, @RequestParam String nombre, @RequestParam Float grad, @RequestParam BigDecimal punt){
		List<Trago> tragos = tragoRepository.findAll();
		List<Trago> tragosReturn = new ArrayList<>();
		for (Trago trago : tragos) {
			if(trago.getActivo() && (StringUtils.isBlank(nombre) || trago.getNombre().toUpperCase().contains(nombre.toUpperCase())) && (grad == null || trago.getGraduacion() >= grad) && (punt == null || trago.getPuntuacion() == null || trago.getPuntuacion().compareTo(punt) == 1 || trago.getPuntuacion().compareTo(punt) == 0) ) {
				tragosReturn.add(trago);
			}
		}
		model.addAttribute("tragos", tragosReturn);
		model.addAttribute("loggedUser", usuarioRepository.findById(idUser));
		return "listaTragos";
	}
	
	@RequestMapping("/guardar-trago")
	@ResponseBody
	public String guardarTrago(@CookieValue(name = "idUser", required = false) Integer idUser, @RequestBody TragoDTO tragoDto) {
		Trago trago = tragoDto.getTrago();
		
		if(!tragoDto.esMedidaValida()) {
			return "La suma de las medidas no puede superar el 100%";
		}
		
		Usuario u = usuarioRepository.findById(idUser);
		trago.setUsuario(u);
		
		tragoRepository.save(trago);
		
		return "OK";
	}
	
	@RequestMapping("/editar-trago")
	public String editarTrago(@RequestParam Integer idTrago, Model model){
		Trago trago = tragoRepository.findById(idTrago);
		TragoDTO tDto = trago.getDto();
		model.addAttribute("tragoDto", tDto);
		return "editarTragoModal";
	}
	
	@RequestMapping("/eliminar-trago")
	@ResponseBody
	public String eliminarTrago(@RequestParam Integer idTrago){
		Trago t = tragoRepository.findById(idTrago);
		t.setActivo(false);
		tragoRepository.save(t);
		return "OK";
	}
	
	@RequestMapping("/pasos-trago")
	public String pasosTrago(@RequestParam Integer idTrago, Model model){
		Trago trago = tragoRepository.findById(idTrago);
		model.addAttribute("trago", trago);
		return "pasosModal";
	}
	
	@RequestMapping("/consejos-trago")
	public String consejosTrago(@RequestParam Integer idTrago, Model model){
		Trago trago = tragoRepository.findById(idTrago);
		model.addAttribute("trago", trago);
		return "consejosModal";
	}
	
	@RequestMapping("/agregar-consejo")
	public String agregarConsejo(@RequestParam Integer idTrago, @RequestParam String consejoText, Model model, @CookieValue(name = "idUser", required = false) Integer idUser){
		Trago trago = tragoRepository.findById(idTrago);
		
		Consejo c = new Consejo();
		c.setUsername(usuarioRepository.findById(idUser).getUsername());
		c.setTexto(consejoText);
		
		trago.getConsejos().add(c);
		tragoRepository.save(trago);
		model.addAttribute("trago", trago);
		return "consejosModal";
	}
	
	@RequestMapping("/agregar-puntuacion")
	@ResponseBody
	public String agregarPuntuacion(@RequestParam Integer idTrago, @RequestParam float puntuacion, Model model, @CookieValue(name = "idUser", required = false) Integer idUser){
		Trago t = tragoRepository.findById(idTrago);
		Puntuacion p = new Puntuacion();
		p.setUsuario(usuarioRepository.findById(idUser));
		p.setFecha(new Date());
		p.setPuntuacion(puntuacion);
		t.getPuntuaciones().add(p);
		tragoRepository.save(t);
		return t.getPuntuacion().toString();
	}
	
	
	@RequestMapping("/perfil/{idUser}")
	public String usuarios(Model model, @PathVariable Integer idUser, @CookieValue(name = "idUser", required = false) Integer idLoggedUser){
		
		List<Trago> tragosUser = new ArrayList<>();
		
		for (Trago trago : tragoRepository.findAll()) {
			if(trago.getUsuario().getId().equals(idUser)) {
				tragosUser.add(trago);
			}
		}
		
		model.addAttribute("tragos", tragosUser);
		model.addAttribute("usuario", usuarioRepository.findById(idUser));
		model.addAttribute("loggedUser", usuarioRepository.findById(idLoggedUser));
		model.addAttribute("pantallaActual", "perfil");
		return "perfilUsuario";
	}
	
	@RequestMapping("/no-se-que-tomar")
	public String noSeQueTomar(Model model, @CookieValue(name = "idUser", required = false) Integer idLoggedUser){
		model.addAttribute("loggedUser", usuarioRepository.findById(idLoggedUser));
		return "noSeQueTomar";
	}
	
	@RequestMapping("/listar-tragos-para-tomar")
	public String listarTragosParaTomar(@CookieValue(name = "idUser", required = false) Integer idUser, Model model,  @RequestBody TragoDTO tragoDto){
		
		List<Trago> tragos = tragoRepository.findAll();
		List<Trago> tragosReturn = new ArrayList<>();
		
		for (Trago trago : tragos) {
			if(trago.getActivo()) {
				if(trago.getGraduacion().compareTo(tragoDto.getGraduacionMin()) > 0 && trago.getGraduacion().compareTo(tragoDto.getGraduacionMax()) < 0) {
					Boolean tiposOk = false;
					if(tragoDto.getTipos() == null || tragoDto.getTipos().isEmpty()) {
						tiposOk = true;
					}else {
						for (String tipoDTO : tragoDto.getTipos()) {
							if(trago.getNombreTipos().contains(tipoDTO.toUpperCase().trim())) {
								tiposOk = true;
								break;
							}
						}
					}
					if(tiposOk) {
						double cantIngTrago = trago.getIngredientes().size();
						double cantIngCoincidencias = 0;
						for (String ingDto : tragoDto.getNombresIngredientesDTO()) {
							if(trago.getNombresIngredientes().contains(ingDto.toUpperCase().replaceAll("\\s",""))) {
								cantIngCoincidencias++;
							}
						}
						if(cantIngCoincidencias/cantIngTrago >= 0.5) {
							tragosReturn.add(trago);
						}
					}
				}
			}
		}
		model.addAttribute("tragos", tragosReturn);
		model.addAttribute("pantallaActual", "tragosRecomendados");
		model.addAttribute("loggedUser", usuarioRepository.findById(idUser));
		return "listaTragos";
	}
}
