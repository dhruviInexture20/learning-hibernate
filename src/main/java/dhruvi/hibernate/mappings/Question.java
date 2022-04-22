package dhruvi.hibernate.mappings;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="question")
public class Question {
	
	@Id
	@GeneratedValue
	private int queId;
	private String question;
	
	@OneToMany(mappedBy= "question", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Answer> answers;

	public int getQueId() {
		return queId;
	}

	public void setQueId(int queId){
		this.queId = queId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
	
	
}
