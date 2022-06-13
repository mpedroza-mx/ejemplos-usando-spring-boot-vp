package org.aprendiendo.spring;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SpringBootApplication
@EnableScheduling
public class SpringBootSolrImportadorDatosApplication {


	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSolrImportadorDatosApplication.class, args);
	}

	@Bean
	public HttpSolrClient clienteSolr(@Value("${solr.cliente.url}") String solrClienteUrl){
		return new HttpSolrClient.Builder(solrClienteUrl).build();
	}
	@Bean
	public LocalDateTime horaInicializacion(){
		return LocalDateTime.now();
	}

}
