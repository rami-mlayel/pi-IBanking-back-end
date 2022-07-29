package esprit.pi.SoftIB.entities;

import esprit.pi.SoftIB.enumeration.QuestionType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "QUESTION_AND_ANSWER")
public class QuestionAndAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT", nullable = false, length = 255)
    private String content;

    @Column(name = "TYPE", nullable = false)
    private QuestionType type;

    @Column(name = "IS_ANSWERED")
    private boolean isAnswered;

    @ManyToOne
    @JoinColumn(name = "idAgent", referencedColumnName = "id", insertable=false, updatable=false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "id", insertable=false, updatable=false)
    private Customer customer;
}

