package org.aprendiendo.spring;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootSolrClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSolrClienteApplication.class, args);
	}

	@Bean
	public HttpSolrClient clienteSolr(@Value("${solr.cliente.url}") String solrClienteUrl){
		return new HttpSolrClient.Builder(solrClienteUrl).build();
	}
}
