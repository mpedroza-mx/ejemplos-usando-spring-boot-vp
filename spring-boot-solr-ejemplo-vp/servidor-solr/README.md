* Agregar al ${solr.install.dir}\server\solr\paises\conf\managed-schema esto:
```text
    <!-- PAIS -->
    <field name="nombre" type="string" indexed="true" stored="true" />
    <field name="capital" type="string" indexed="true" stored="true" />
    <field name="poblacion" type="plong" indexed="true" stored="true" />
    <field name="lenguaje" type="string" indexed="true" stored="true"/>
    <field name="fechaCreacion" type="pdate" indexed="true" stored="true" />
    <field name="fechaActualizacion" type="pdate" indexed="true" stored="true" />
```
* Agregar al ${solr.install.dir}\server\solr\paises\conf\solrconfig.xml esto
```text
  <lib dir="${solr.install.dir:../../../..}/dist/" regex="solr-dataimporthandler-.*\.jar" />
  <lib dir="${solr.install.dir:../../../..}/dist/" regex="postgresql-42.4.0.jar" />
  
  <requestHandler name="/dataimport" class="org.apache.solr.handler.dataimport.DataImportHandler">
    <lst name="defaults">
      <str name="config">db-data-config.xml</str>
    </lst>
  </requestHandler>
```

* Copiar db-data-config.xml a ${solr.install.dir}\server\solr\paises\conf\
