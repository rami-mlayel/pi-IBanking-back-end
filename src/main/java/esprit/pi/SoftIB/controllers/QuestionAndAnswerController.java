package esprit.pi.SoftIB.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esprit.pi.SoftIB.entities.QuestionAndAnswer;
import esprit.pi.SoftIB.services.IQuestionAndAnswerService;

@RestController
@RequestMapping("/questionAndAnswer")
public class QuestionAndAnswerController {

	@Autowired
	private IQuestionAndAnswerService questionAndAnswerService;

	@GetMapping(value = "/getQuestionAndAnswer/{id}")
	public ResponseEntity getQuestionAndAnswer(@PathVariable Long id) {
		QuestionAndAnswer questionAndAnswer = null;
		try {
			questionAndAnswer = questionAndAnswerService.getQuestionAndAnswer(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(questionAndAnswer);
	}

	@GetMapping(value = "/getQuestionsAndAnswers")
	public ResponseEntity getQuestionsAndAnswers() {
		List<QuestionAndAnswer> questionsAndAnswers = new ArrayList<>();
		try {
			questionsAndAnswers = questionAndAnswerService.getQuestionsAndAnswers();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(questionsAndAnswers);
	}
}
