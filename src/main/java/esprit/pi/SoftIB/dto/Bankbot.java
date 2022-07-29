package esprit.pi.SoftIB.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bankbot implements Serializable {

	private String projectId;
	private List<String> texts;
	private String sessionId;
	private String languageCode;

}
