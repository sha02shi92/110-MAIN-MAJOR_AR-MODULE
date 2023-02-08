package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CitizenApp;
import in.ashokit.service.CitizenAppRegisterService;

@RestController
public class CitizenAppRegRestController {
	
	@Autowired
	private CitizenAppRegisterService service;
	
	@PostMapping("/citizenApp")
	public ResponseEntity<String> registerCitizen (@RequestBody CitizenApp app){
		         String response = service.registerCitizenApp(app);
				return new ResponseEntity<String>(response,HttpStatus.OK);
	}

}
