package com.finance.plutus.currency.schedule;

import com.finance.plutus.currency.model.xsd.DataSet;
import com.finance.plutus.currency.service.CurrencyRateCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/** Plutus Created by Catalin on 10/5/2020 */
@Log4j2
@Component
@RequiredArgsConstructor
public class CurrencyRatesLoader {

  private static final String INITIAL_DELAY_30_MINUTES = "1800000";
  private static final String FIXED_DELAY_12_HOURS = "43200000";
  private static final String BNR_URL = "https://www.bnr.ro/nbrfxrates.xml";
  private static final String[] BNR_HISTORY_URLS = {
    "https://www.bnr.ro/files/xml/years/nbrfxrates2019.xml",
    "https://www.bnr.ro/files/xml/years/nbrfxrates2020.xml"
  };

  private final CurrencyRateCreator currencyRateCreator;

  @SneakyThrows
  @PostConstruct
  public void loadFromPast() {
    RestTemplate restTemplate = createUnSafeTemplate();
    for (String url : BNR_HISTORY_URLS) {
      load(restTemplate, url);
    }
  }

  @SneakyThrows
  @Scheduled(fixedDelayString = FIXED_DELAY_12_HOURS, initialDelayString = INITIAL_DELAY_30_MINUTES)
  public void loadFromBNR() {
    RestTemplate restTemplate = createUnSafeTemplate();
    load(restTemplate, BNR_URL);
  }

  private void load(RestTemplate restTemplate, String url) {
    DataSet source = restTemplate.getForObject(url, DataSet.class);
    if (source != null) {
      currencyRateCreator.create(source.mapCurrencyRatesDto());
      log.info("Currency rates loaded from url {}", url);
    }
  }

  private RestTemplate createUnSafeTemplate()
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    SSLContext sslContext =
        SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    requestFactory.setHttpClient(httpClient);
    return new RestTemplate(requestFactory);
  }
}
