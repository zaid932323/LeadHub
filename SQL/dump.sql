--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: leads; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.leads (
    id bigint NOT NULL,
    dob timestamp(6) without time zone NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    gender character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    lead_id integer NOT NULL,
    middle_name character varying(255),
    mobile_number character varying(255) NOT NULL
);


ALTER TABLE public.leads OWNER TO postgres;

--
-- Name: leads_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.leads_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.leads_seq OWNER TO postgres;

--
-- Data for Name: leads; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.leads (id, dob, email, first_name, gender, last_name, lead_id, middle_name, mobile_number) FROM stdin;
1	2000-06-09 05:30:00	v@gmail.com	Vineet	Male	KV	5678		8877887788
4	2000-06-09 05:30:00	vs@gmail.com	Vineet	Male	KV	5679		8877887788
52	2000-06-09 05:30:00	m@gmail.com	Vineet	Male	KV	5670		9007865698
102	2000-06-09 05:30:00	zaid@gmail.com	zaid	Male	siddiqui	5689		8169179187
104	1999-06-09 05:30:00	vinu@gmail.com	bushra	FeMale	siddiqui	5688	zainab	7039162608
202	1999-06-09 05:30:00	binod@gmail.com	bushra	FeMale	siddiqui	98766	zainab	4567982354
203	1999-06-09 05:30:00	vinud@gmail.com	bushra	FeMale	siddiqui	565	zainab	4567982354
\.


--
-- Name: leads_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.leads_seq', 251, true);


--
-- Name: leads leads_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT leads_pkey PRIMARY KEY (id);


--
-- Name: leads uk_5dw9kmt497rahmbxg530pqers; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT uk_5dw9kmt497rahmbxg530pqers UNIQUE (email);


--
-- Name: leads uk_hu5upumtow1hpqtwqkjtt2cxq; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.leads
    ADD CONSTRAINT uk_hu5upumtow1hpqtwqkjtt2cxq UNIQUE (lead_id);


--
-- PostgreSQL database dump complete
--

