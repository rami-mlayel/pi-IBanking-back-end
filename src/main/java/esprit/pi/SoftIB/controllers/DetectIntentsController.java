package esprit.pi.SoftIB.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.gax.rpc.ApiException;

import esprit.pi.SoftIB.dialogflow.DetectIntentTexts;
import esprit.pi.SoftIB.dto.Bankbot;

@RestController
@RequestMapping("/test")
public class DetectIntentsController {

	@Autowired
	private DetectIntentTexts detectIntentTexts;

	@PostMapping(value = "/bankbot", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> callDetectIntents(@RequestBody Bankbot bankbot) throws ApiException, IOException {
		Map<String, String> detectedIntents = detectIntentTexts.detectIntentTexts(bankbot.getProjectId(),
				bankbot.getTexts(), bankbot.getSessionId(), bankbot.getLanguageCode());

		return new ResponseEntity(detectedIntents, HttpStatus.OK);
	}
}
