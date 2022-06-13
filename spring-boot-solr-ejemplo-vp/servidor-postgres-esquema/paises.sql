--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3
-- Dumped by pg_dump version 14.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Updata database timezone to use UTC instead of local timezone
--
ALTER DATABASE paises SET timezone TO 'UTC';

--
-- Name: guardar_pais_borrado(); Type: FUNCTION; Schema: public; Owner: root
--

CREATE FUNCTION public.guardar_pais_borrado() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    INSERT INTO historial_pais_eliminado
        (pais_id,
        nombre,
        capital,
        poblacion,
        lenguaje,
        fecha_creacion,
        fecha_actualizacion
        )
    VALUES
        (OLD.id,
            OLD.nombre,
            OLD.capital,
            OLD.poblacion,
            OLD.lenguaje,
            OLD.fecha_creacion,
            OLD.fecha_actualizacion
                );
    RETURN OLD;
END;

$$;


ALTER FUNCTION public.guardar_pais_borrado() OWNER TO root;

--
-- Name: modificar_fecha_actualizacion(); Type: FUNCTION; Schema: public; Owner: root
--

CREATE FUNCTION public.modificar_fecha_actualizacion() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
     NEW.fecha_actualizacion = CURRENT_TIMESTAMP;
     RETURN NEW;
END;

$$;


ALTER FUNCTION public.modificar_fecha_actualizacion() OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: historial_pais_eliminado; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.historial_pais_eliminado (
    id integer NOT NULL,
    pais_id integer NOT NULL,
    nombre character varying(200),
    capital character varying(200),
    lenguaje character varying(200),
    poblacion bigint,
    fecha_creacion timestamp with time zone,
    fecha_actualizacion timestamp with time zone,
    fecha_eliminacion timestamp with time zone DEFAULT now()
);


ALTER TABLE public.historial_pais_eliminado OWNER TO root;

--
-- Name: historial_pais_eliminado_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

ALTER TABLE public.historial_pais_eliminado ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.historial_pais_eliminado_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: pais; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.pais (
    id integer NOT NULL,
    nombre character varying(200),
    capital character varying(200),
    poblacion bigint,
    lenguaje character varying(200),
    fecha_creacion timestamp with time zone DEFAULT now(),
    fecha_actualizacion timestamp with time zone DEFAULT now()
);


ALTER TABLE public.pais OWNER TO root;

--
-- Name: pais_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

ALTER TABLE public.pais ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pais_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: historial_pais_eliminado; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.historial_pais_eliminado (id, pais_id, nombre, capital, lenguaje, poblacion, fecha_creacion, fecha_actualizacion, fecha_eliminacion) FROM stdin;
\.


--
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.pais (id, nombre, capital, poblacion, lenguaje) FROM stdin;
1	Mexico	Ciudad de Mexico	128649565	Español
2	Colombia	Bogota	51049498	Español
3	Brasil	Brasilia	212216052	Portugués
4	Chile	Santiago de Chile	19678310	Español
5	Argentina	Buenos Aires	45195777	Español
6	Uruguay	Montevideo	3518552	Español
7	Canada	Ottawa	38246108	Inglés y Francés
8	Estados Unidos de America	Washington D.C.	331449281	Inglés
9	Rusia	Moscu	144790234	Ruso
10	Italia	Roma	60542215	Italiano
11	Alemania	Berlín	83149300	Alemán
12	Francia	París	67407241	Francés
13	Japón	Tokio	127094745	Japonés
14	Egipto	El Cairo	101400000	Árabe
15	Marruecos	Rabat	37157380	Árabe
\.


--
-- Name: historial_pais_eliminado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.historial_pais_eliminado_id_seq', 1, false);


--
-- Name: pais_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.pais_id_seq', 15, true);


--
-- Name: historial_pais_eliminado historial_pais_eliminado_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.historial_pais_eliminado
    ADD CONSTRAINT historial_pais_eliminado_pkey PRIMARY KEY (id);


--
-- Name: pais pais_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id);


--
-- Name: pais antes_de_actualizar_pais; Type: TRIGGER; Schema: public; Owner: root
--

CREATE TRIGGER antes_de_actualizar_pais BEFORE UPDATE ON public.pais FOR EACH ROW EXECUTE FUNCTION public.modificar_fecha_actualizacion();


--
-- Name: pais antes_de_borrar_pais; Type: TRIGGER; Schema: public; Owner: root
--

CREATE TRIGGER antes_de_borrar_pais AFTER DELETE ON public.pais FOR EACH ROW EXECUTE FUNCTION public.guardar_pais_borrado();


--
-- PostgreSQL database dump complete
--

