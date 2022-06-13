package org.aprendiendo.spring.programador;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.aprendiendo.spring.dto.PaisDto;
import org.aprendiendo.spring.modelo.Pais;
import org.aprendiendo.spring.repositorio.ReposirorioHistorialPais;
import org.aprendiendo.spring.repositorio.RepositorioPais;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Importador {

  private static final Logger logger = LoggerFactory.getLogger(Importador.class);

  private final RepositorioPais repositorioPais;

  private LocalDateTime ultimaEjecucion = null;
  private final HttpSolrClient clienteSolr;

  private final LocalDateTime horaInicializacion;

  private final ReposirorioHistorialPais reposirorioHistorialPais;

  @Autowired
  public Importador(RepositorioPais repositorioPais, HttpSolrClient clienteSolr,
      LocalDateTime horaInicializacion, ReposirorioHistorialPais reposirorioHistorialPais) {
    this.repositorioPais = repositorioPais;
    this.clienteSolr = clienteSolr;
    this.horaInicializacion = horaInicializacion;
    this.reposirorioHistorialPais = reposirorioHistorialPais;
  }

  @Scheduled(cron = "0 0/2 * * * ?")
  public void iniciarImportacion() throws SolrServerException, IOException {
    LocalDateTime ultimaEjecucion = Optional.ofNullable(this.ultimaEjecucion)
        .orElse(horaInicializacion);
    UpdateResponse respuestaSolr = null;
    logger.info("********************Iniciando importacion: " + LocalDateTime.now()
        + "***************************");
    var paisesCreadosActualizados = new HashSet<PaisDto>();
    var paisesEliminados = new HashSet<String>();

    paisesCreadosActualizados.addAll(repositorioPais.encontrarNuevosPaises(ultimaEjecucion)
        .stream()
        .map(this::paisAPaisDto)
        .collect(Collectors.toList()));
    paisesCreadosActualizados.addAll(repositorioPais.encontrarPaisesActualizados(ultimaEjecucion)
        .stream()
        .map(this::paisAPaisDto)
        .collect(Collectors.toList()));
    paisesEliminados.addAll(reposirorioHistorialPais.encontrarPaisesEliminados(ultimaEjecucion)
        .stream()
        .map(historialPaisEliminado -> historialPaisEliminado.getPaisId().toString())
        .collect(Collectors.toList()));

    if (paisesCreadosActualizados.size() > 0) {
      clienteSolr.addBeans(paisesCreadosActualizados);
    }
    if (paisesEliminados.size() > 0) {
      clienteSolr.deleteById(new ArrayList<>(paisesEliminados));
    }

    if (paisesCreadosActualizados.size() > 0 || paisesEliminados.size() > 0) {
      respuestaSolr = clienteSolr.commit();
    }
    logger.info("Documentos Agregados/Actualizados: " + paisesCreadosActualizados.size());
    logger.info("Documentos Borrados: " + paisesEliminados.size());
    Optional.ofNullable(respuestaSolr)
        .ifPresentOrElse((res -> logger.info("Respuesta de solr: " + res.toString())),
            () -> logger.info("No se hizo la peticion a solr"));
    LocalDateTime termino = LocalDateTime.now();
    logger.info(
        "********************Finalizando importacion: " + termino + "***************************");
    this.ultimaEjecucion = termino;

  }

  private PaisDto paisAPaisDto(Pais pais) {
    PaisDto dto = new PaisDto();
    dto.setCapital(pais.getCapital());
    dto.setFechaActualizacion(Date.from(pais.getFechaActualizacion()
        .atZone(ZoneId.systemDefault())
        .toInstant()));
    dto.setId(pais.getId().toString());
    dto.setNombre(pais.getNombre());
    dto.setFechaCreacion(Date.from(pais.getFechaCreacion()
        .atZone(ZoneId.systemDefault())
        .toInstant()));
    dto.setPoblacion(pais.getPoblacion());
    dto.setLenguaje(pais.getLenguaje());

    return dto;

  }
}
