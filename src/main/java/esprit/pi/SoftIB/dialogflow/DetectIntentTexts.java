package esprit.pi.SoftIB.dialogflow;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Maps;

import esprit.pi.SoftIB.entities.QuestionAndAnswer;
import esprit.pi.SoftIB.enumeration.QuestionType;
import esprit.pi.SoftIB.repository.QuestionAndAnswerRepository;

@Service
public class DetectIntentTexts {

	@Autowired
	QuestionAndAnswerRepository questionAndAnswerRepo;

	// DialogFlow API Detect Intent with text inputs.
	@Transactional
	public Map<String, String> detectIntentTexts(String projectId, List<String> texts, String sessionId,
			String languageCode) throws IOException, ApiException {

		boolean isFallback = false;

		Map<String, String> queryResults = Maps.newTreeMap();

		// Instantiates a client
		try (SessionsClient sessionsClient = SessionsClient.create()) {
			// Set the session name using the sessionId (UUID) and projectID (my-project-id)
			SessionName session = SessionName.of(projectId, sessionId);
			System.out.println("Session Path: " + session.toString());

			// Detect intents for each text input
			for (String text : texts) {
				// Set the text and language code for the query
				TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

				// Build the query with the TextInput
				QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

				// Performs the detect intent request
				DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

				// Display the query result
				QueryResult queryResult = response.getQueryResult();

				System.out.println("====================");
				System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
				System.out.format("Detected Intent: %s (confidence: %f)\n", queryResult.getIntent().getDisplayName(),
						queryResult.getIntentDetectionConfidence());

				// check if isFallback true
				isFallback = queryResult.getIntent().getIsFallback();
				if (isFallback) {
					// create a question
					QuestionAndAnswer question = new QuestionAndAnswer();
					question.setContent(text);
					question.setTitle("Client's question");
					question.setType(QuestionType.OTHER);
					questionAndAnswerRepo.save(question);
					String responseFallback = "Sorry, I don't have an answer to your question. However, I have"
							+ " submitted it, an agent will review and answer it as soon as possible";

					queryResults.put("Question", text);
					queryResults.put("Response", responseFallback);
					queryResults.put("Submitted question-id to the agent", question.getId().toString());

				} else {
					queryResults.put("Question", text);
					queryResults.put("Response", queryResult.getFulfillmentText());
				}
			}
		}
		return queryResults;
	}

}
