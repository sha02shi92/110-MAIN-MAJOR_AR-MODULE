package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.binding.CitizenApp;
import in.ashokit.entity.CitizenAppEntity;
import in.ashokit.repository.CitizenAppRepo;

@Service
public class CitizenAppRegServiceImpl implements CitizenAppRegisterService {
	
	@Autowired
	private CitizenAppRepo citizenAppRepo;
	
	private static final String Rest_Urls="http://ssawebapi-env.eba-k88bsahp.ap-south-1.elasticbeanstalk.com/ssn/{ssn}"; 

	@Override
	public String registerCitizenApp(CitizenApp	 app) {
		 Long ssn = app.getSsn();
		 
		       WebClient webClient = WebClient.create();
		       
		     String stateName =  webClient.get()
		                .uri(Rest_Urls, ssn)
		                .retrieve()
		                .bodyToMono(String.class)
		                .block();
		       if("Rhode Island".equals(stateName)) {
		    	   
		    	   // insert data into db table
		    	   CitizenAppEntity entity=new CitizenAppEntity();
		    	   BeanUtils.copyProperties(app, entity);
		    	  entity = citizenAppRepo.save(entity);
		    	   
//		    	   return "Citizen app created,App ID : {appId}";
	    	    return "Citizen app created,App ID : " + entity.getAppId();
		       }
		       return "Citizen not belong to RI...";
	
	}

}
