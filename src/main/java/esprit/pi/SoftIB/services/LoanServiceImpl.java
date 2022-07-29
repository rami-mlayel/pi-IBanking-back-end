package esprit.pi.SoftIB.services;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LoanServiceImpl implements ICreditService {

    public JSONObject homeLoan(String type, String sum, String year) throws IOException {
        String urlParameters  = String.format("crediresidence_duree_epargne=%s&amount1=%s&amount2=%s",
                type, sum, year);
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String targetUrl = "https://www.biat.tn/biat/Fr/crediresidence_83_336_Action";

        HttpURLConnection conn = prepareConnexion(targetUrl, postDataLength);

        try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
            wr.flush();
        }

        StringBuffer answer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String averageCollectionYear ="";
        String monthlyPayment ="";
        int i = 1;
        while ((line = reader.readLine()) != null) {
            answer.append(line);
            if (line.contains("id=\"remb_mensuel2\"")) {
                Pattern pattern = Pattern.compile("[0-9]+(?=<)");
                Matcher matcher = pattern.matcher(line.trim());
                matcher.find();
                averageCollectionYear = matcher.group();
                i++;
            } else if (i == 2) {
                monthlyPayment = line.replaceAll("[^0-9]", "");
                i++;
            } else if (i == 3) {
                break;
            }
        }

        reader.close();

        //Output the response
        JSONObject item = new JSONObject();
        item.put("averageCollectionYear", averageCollectionYear);
        item.put("monthlyPayment", monthlyPayment);

        return item;
    }

    public JSONObject autoLoan(String carPrice, String carPower, String sum, String month) throws IOException {
        String urlParameters  = String.format("valeur_vehicule=%s&puissance_fiscale=%s&amount1=%s&amount2=%s",
                carPrice, carPower, sum, month);
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        String targetUrl = "https://www.biat.tn/biat/Fr/crediauto_79_332_Action";

        HttpURLConnection conn = prepareConnexion(targetUrl, postDataLength);

        try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
            wr.flush();
        }

        StringBuffer answer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String monthlyPayment ="";
        int i = 1;
        while ((line = reader.readLine()) != null) {
            answer.append(line);
            if (line.contains("id=\"remb_mensuel\"")) {
                Pattern pattern = Pattern.compile("[0-9]+(?=<)");
                Matcher matcher = pattern.matcher(line.trim());
                matcher.find();
                monthlyPayment = matcher.group();
                break;
            }
        }

        reader.close();

        //Output the response
        JSONObject item = new JSONObject();
        item.put("monthlyPayment", monthlyPayment);

        return item;
    }

    private HttpURLConnection prepareConnexion(String targetUrl, int postDataLength) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setConnectTimeout(180000);
        conn.setReadTimeout(180000);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setRequestProperty("Cookie", "PHPSESSID=0e1qthsjldhqmff4kt6uk6ile1;");
        conn.setUseCaches(false);

        return conn;
    }
}
