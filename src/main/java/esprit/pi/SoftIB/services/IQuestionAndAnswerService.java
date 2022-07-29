package esprit.pi.SoftIB.services;

import java.util.List;

import esprit.pi.SoftIB.entities.QuestionAndAnswer;

public interface IQuestionAndAnswerService {

	QuestionAndAnswer getQuestionAndAnswer(Long id);

	List<QuestionAndAnswer> getQuestionsAndAnswers();

	public QuestionAndAnswer addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer);

	QuestionAndAnswer updateQuestionAndAnswer(QuestionAndAnswer questionAndAnswer);

	void deleteQuestionAndAnswer(Long id);
}
