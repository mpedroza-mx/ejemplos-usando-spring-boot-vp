package org.aprendiendo.spring.controlador;

import java.io.IOException;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.aprendiendo.spring.dto.PaisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolrClienteControlador {

  private final HttpSolrClient clienteSolr;
  @Autowired
  public SolrClienteControlador(HttpSolrClient clienteSolr) {
    this.clienteSolr = clienteSolr;
  }


  /**
   *
   * @param q
   * @param fl
   * @return
   * @throws IOException
   * @throws SolrServerException
   */
  @GetMapping(value = "/busquedas")
  public List<PaisDto> buscarPaises(@RequestParam(value = "q", required = false) String q,
      @RequestParam(value = "fl", required = false) String fl)
      throws IOException, SolrServerException {
    SolrQuery query = new SolrQuery();
    query.set("q", q);
    query.set("fl", fl);
    QueryResponse respuesta = clienteSolr.query(query);

    return respuesta.getBeans(PaisDto.class);
  }

}
