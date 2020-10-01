package com.finance.plutus.configuration;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import feign.codec.Encoder;
import feign.form.FormEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/** Plutus Created by catalin.matache on 10/1/2020 */
@Configuration
@RequiredArgsConstructor
public class FormUrlEncodedClientConfiguration {

  private final ObjectFactory<HttpMessageConverters> messageConverters;

  @Bean
  @Primary
  @Scope(SCOPE_PROTOTYPE)
  Encoder feignFormEncoder() {
    return new FormEncoder(new SpringEncoder(this.messageConverters));
  }
}
