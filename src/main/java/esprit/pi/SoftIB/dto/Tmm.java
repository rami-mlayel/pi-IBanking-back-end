package esprit.pi.SoftIB.dto;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.Month;

public class Tmm {
    static BigDecimal tmmRate = new BigDecimal(0);
    String url = "https://www.bct.gov.tn/bct/siteprod/tableau_statistique_a.jsp?params=PL203105&la=AN";

    @Retryable(
            maxAttempts = 5, // retrying up to 5 times
            backoff = @Backoff(delay = 3000) // 3s
    )
    public BigDecimal retrieveTmmRate() {
        try {
            Document webPage = Jsoup
                    .connect(url)
                    .get();

            Element table = webPage
                    .getElementsByClass("bct-table-fixed")
                    .get(0);

            Element tbodys = table
                    .select("tbody:not(.bct-hdr-classic)")
                    .get(0);

            LocalDate now = LocalDate.now(); // 2015-11-24
            Month previousMonth = now.minusMonths(1).getMonth(); // 2015-10-24

            String tmmRate = tbodys
                    .select("tr:contains("+ previousMonth + ")")
                    .select("td")
                    .last()
                    .text();

            Tmm.tmmRate = new BigDecimal(tmmRate);

        } catch (HttpStatusException | UnknownHostException e) {
            System.out.println("Problem when trying to connect to the website");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Couldn't get the MONEY MARKET AVERAGE (TMM)");
        }
        return tmmRate;
    }
}
