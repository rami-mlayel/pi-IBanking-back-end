package esprit.pi.SoftIB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esprit.pi.SoftIB.entities.QuestionAndAnswer;

@Repository
public interface QuestionAndAnswerRepository extends JpaRepository<QuestionAndAnswer, Long> {

}
