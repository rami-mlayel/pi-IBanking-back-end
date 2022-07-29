package esprit.pi.SoftIB.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import esprit.pi.SoftIB.enumeration.QuestionType;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(name = "QUESTION_AND_ANSWER")
public class QuestionAndAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private TextArea content;

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

