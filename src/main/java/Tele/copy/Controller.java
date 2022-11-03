package Tele.copy;

import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate.Param;
@SpringBootApplication(scanBasePackages=("com.Teleapps"))
@RestController
@RequestMapping("/api/")
public class Controller {
	@RequestMapping(value="testApi",method=RequestMethod.GET)
	public ResponseEntity<?>testApi(@RequestParam  Map<String,String>requestParams)throws Exception{
		requestParams.put("status","success");
		requestParams.put("DESC","test Success");
		return new ResponseEntity<>(requestParams,HttpStatus.OK);
				
	}
	

}
