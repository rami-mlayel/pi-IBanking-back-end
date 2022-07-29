package esprit.pi.SoftIB.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esprit.pi.SoftIB.entities.QuestionAndAnswer;
import esprit.pi.SoftIB.repository.QuestionAndAnswerRepository;

@Service
public class QuestionAndAnswerServiceImpl implements IQuestionAndAnswerService {

	@Autowired
	private QuestionAndAnswerRepository questionAndAnswerRepository;

	@Override
	public QuestionAndAnswer getQuestionAndAnswer(Long id) {
		return questionAndAnswerRepository.findById(id).get();
	}

	@Override
	public List<QuestionAndAnswer> getQuestionsAndAnswers() {
		return (List<QuestionAndAnswer>) questionAndAnswerRepository.findAll();
	}

	@Override
	public QuestionAndAnswer addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
		return questionAndAnswerRepository.save(questionAndAnswer);
	}

	@Override
	public QuestionAndAnswer updateQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
		return questionAndAnswerRepository.save(questionAndAnswer);
	}

	@Override
	public void deleteQuestionAndAnswer(Long id) {
		questionAndAnswerRepository.deleteById(id);
	}

}
