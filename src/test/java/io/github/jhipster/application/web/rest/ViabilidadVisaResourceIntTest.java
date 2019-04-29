package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.ViabilidadVisa;
import io.github.jhipster.application.repository.ViabilidadVisaRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Destinod;
import io.github.jhipster.application.domain.enumeration.TipoProgramad;
import io.github.jhipster.application.domain.enumeration.Duraciond;
import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Generod;
import io.github.jhipster.application.domain.enumeration.EstadoCivild;
import io.github.jhipster.application.domain.enumeration.PersonasCargod;
import io.github.jhipster.application.domain.enumeration.NivelAcademicod;
import io.github.jhipster.application.domain.enumeration.OcupacionActuald;
import io.github.jhipster.application.domain.enumeration.PeridoSupencionEstud;
import io.github.jhipster.application.domain.enumeration.TipoContratod;
import io.github.jhipster.application.domain.enumeration.NivelSalariald;
import io.github.jhipster.application.domain.enumeration.TipoLaborIndependiented;
import io.github.jhipster.application.domain.enumeration.PeridoSupencionEstud;
import io.github.jhipster.application.domain.enumeration.NivelSalariald;
import io.github.jhipster.application.domain.enumeration.PeridoSupencionEstud;
import io.github.jhipster.application.domain.enumeration.QuienFinanciaEstudiosd;
import io.github.jhipster.application.domain.enumeration.Parentescod;
import io.github.jhipster.application.domain.enumeration.NivelSalariald;
/**
 * Test class for the ViabilidadVisaResource REST controller.
 *
 * @see ViabilidadVisaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class ViabilidadVisaResourceIntTest {

    private static final Destinod DEFAULT_DESTINO = Destinod.ALEMANIA;
    private static final Destinod UPDATED_DESTINO = Destinod.AUSTRALIA;

    private static final TipoProgramad DEFAULT_TIPO_PROGRAMA = TipoProgramad.Programas_Idiomas;
    private static final TipoProgramad UPDATED_TIPO_PROGRAMA = TipoProgramad.Curso_Verano_idiomas;

    private static final Duraciond DEFAULT_DURACION = Duraciond.UN_MES;
    private static final Duraciond UPDATED_DURACION = Duraciond.DOS_MESES;

    private static final Nacionalidadd DEFAULT_NACIONALIDAD_PRINCIPAL = Nacionalidadd.Argentina;
    private static final Nacionalidadd UPDATED_NACIONALIDAD_PRINCIPAL = Nacionalidadd.Bolivia;

    private static final Nacionalidadd DEFAULT_OTRA_NACIONALIDAD = Nacionalidadd.Argentina;
    private static final Nacionalidadd UPDATED_OTRA_NACIONALIDAD = Nacionalidadd.Bolivia;

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final Generod DEFAULT_GENERO = Generod.Hombre;
    private static final Generod UPDATED_GENERO = Generod.Mujer;

    private static final EstadoCivild DEFAULT_ESTADO_CIVIL = EstadoCivild.Soltero;
    private static final EstadoCivild UPDATED_ESTADO_CIVIL = EstadoCivild.Casado;

    private static final Boolean DEFAULT_VIAJA_ACOMPANADO = false;
    private static final Boolean UPDATED_VIAJA_ACOMPANADO = true;

    private static final PersonasCargod DEFAULT_PERSONAS_CARGO = PersonasCargod.NINGUNA;
    private static final PersonasCargod UPDATED_PERSONAS_CARGO = PersonasCargod.UNA;

    private static final NivelAcademicod DEFAULT_NIVEL_ACADEMICO = NivelAcademicod.PRIMARIA;
    private static final NivelAcademicod UPDATED_NIVEL_ACADEMICO = NivelAcademicod.SECUNDARIA;

    private static final String DEFAULT_PROFESION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_GADRUACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_GADRUACION = LocalDate.now(ZoneId.systemDefault());

    private static final OcupacionActuald DEFAULT_OCUPACION_ACTUAL = OcupacionActuald.Estudiante;
    private static final OcupacionActuald UPDATED_OCUPACION_ACTUAL = OcupacionActuald.Estudiante_Graduado_sin_Laborar;

    private static final Boolean DEFAULT_CARTA = false;
    private static final Boolean UPDATED_CARTA = true;

    private static final Boolean DEFAULT_SUSPENDIDO_ESTUDIOS = false;
    private static final Boolean UPDATED_SUSPENDIDO_ESTUDIOS = true;

    private static final PeridoSupencionEstud DEFAULT_PERIDO_SUPENCION_ESTU = PeridoSupencionEstud.SEIS_MESES;
    private static final PeridoSupencionEstud UPDATED_PERIDO_SUPENCION_ESTU = PeridoSupencionEstud.UN_ANO;

    private static final String DEFAULT_RAZON_SUSPENCION = "AAAAAAAAAA";
    private static final String UPDATED_RAZON_SUSPENCION = "BBBBBBBBBB";

    private static final TipoContratod DEFAULT_TIPO_CONTRATO = TipoContratod.TERMINO_FIJO;
    private static final TipoContratod UPDATED_TIPO_CONTRATO = TipoContratod.TERMINO_INDEFNIDO;

    private static final Boolean DEFAULT_LICENCIA_LABORAL = false;
    private static final Boolean UPDATED_LICENCIA_LABORAL = true;

    private static final NivelSalariald DEFAULT_NIVEL_SALARIAL = NivelSalariald.MENOS_US600;
    private static final NivelSalariald UPDATED_NIVEL_SALARIAL = NivelSalariald.US700_US1000;

    private static final TipoLaborIndependiented DEFAULT_TIPO_LABOR_INDEPENDIENTE = TipoLaborIndependiented.PROFESIONAL_CUENTA_PROPIA;
    private static final TipoLaborIndependiented UPDATED_TIPO_LABOR_INDEPENDIENTE = TipoLaborIndependiented.ESTABLECIMIENTO_COMERCIO;

    private static final PeridoSupencionEstud DEFAULT_TIEMPO_INDEPENDIENTE = PeridoSupencionEstud.SEIS_MESES;
    private static final PeridoSupencionEstud UPDATED_TIEMPO_INDEPENDIENTE = PeridoSupencionEstud.UN_ANO;

    private static final NivelSalariald DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE = NivelSalariald.MENOS_US600;
    private static final NivelSalariald UPDATED_NIVEL_INGRESOS_INDEPENDIENTE = NivelSalariald.US700_US1000;

    private static final PeridoSupencionEstud DEFAULT_TIEMPO_DESEMPLEADO = PeridoSupencionEstud.SEIS_MESES;
    private static final PeridoSupencionEstud UPDATED_TIEMPO_DESEMPLEADO = PeridoSupencionEstud.UN_ANO;

    private static final QuienFinanciaEstudiosd DEFAULT_QUIEN_FINANCIA_ESTUDIOS = QuienFinanciaEstudiosd.Tu_mismo;
    private static final QuienFinanciaEstudiosd UPDATED_QUIEN_FINANCIA_ESTUDIOS = QuienFinanciaEstudiosd.Un_familiar;

    private static final Parentescod DEFAULT_PARENTESCO = Parentescod.Padre_Madre;
    private static final Parentescod UPDATED_PARENTESCO = Parentescod.Hermano;

    private static final NivelSalariald DEFAULT_PRESUPUESTO_DISPONIBLE = NivelSalariald.MENOS_US600;
    private static final NivelSalariald UPDATED_PRESUPUESTO_DISPONIBLE = NivelSalariald.US700_US1000;

    private static final Boolean DEFAULT_AHORRO_DISPONIBLE = false;
    private static final Boolean UPDATED_AHORRO_DISPONIBLE = true;

    private static final Boolean DEFAULT_IDIOMA = false;
    private static final Boolean UPDATED_IDIOMA = true;

    private static final Boolean DEFAULT_CERTIFICAR_IDIOMA = false;
    private static final Boolean UPDATED_CERTIFICAR_IDIOMA = true;

    private static final String DEFAULT_CERTIFICACION_IDIOMA = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICACION_IDIOMA = "BBBBBBBBBB";

    private static final String DEFAULT_PUNTAJE_CERTIFICACION = "AAAAAAAAAA";
    private static final String UPDATED_PUNTAJE_CERTIFICACION = "BBBBBBBBBB";

    private static final String DEFAULT_SALIDAS_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_SALIDAS_PAIS = "BBBBBBBBBB";

    private static final String DEFAULT_PAISES_VISITADOS = "AAAAAAAAAA";
    private static final String UPDATED_PAISES_VISITADOS = "BBBBBBBBBB";

    private static final String DEFAULT_VISA_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_VISA_PAIS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ESTUDIADO_ANTERIOR = false;
    private static final Boolean UPDATED_ESTUDIADO_ANTERIOR = true;

    private static final Boolean DEFAULT_FUERA_PAIS = false;
    private static final Boolean UPDATED_FUERA_PAIS = true;

    private static final String DEFAULT_PAIS_FUERA = "AAAAAAAAAA";
    private static final String UPDATED_PAIS_FUERA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXTENDER_ESTADIA = false;
    private static final Boolean UPDATED_EXTENDER_ESTADIA = true;

    private static final Boolean DEFAULT_NEGADO_VISA = false;
    private static final Boolean UPDATED_NEGADO_VISA = true;

    private static final String DEFAULT_PAIS_NEGADO = "AAAAAAAAAA";
    private static final String UPDATED_PAIS_NEGADO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FAMILIARES_DESTINO = false;
    private static final Boolean UPDATED_FAMILIARES_DESTINO = true;

    private static final String DEFAULT_ESTATUS_MIGRATORIO = "AAAAAAAAAA";
    private static final String UPDATED_ESTATUS_MIGRATORIO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIOD = "AAAAAAAAAA";
    private static final String UPDATED_APELLIOD = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    @Autowired
    private ViabilidadVisaRepository viabilidadVisaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restViabilidadVisaMockMvc;

    private ViabilidadVisa viabilidadVisa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ViabilidadVisaResource viabilidadVisaResource = new ViabilidadVisaResource(viabilidadVisaRepository);
        this.restViabilidadVisaMockMvc = MockMvcBuilders.standaloneSetup(viabilidadVisaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViabilidadVisa createEntity(EntityManager em) {
        ViabilidadVisa viabilidadVisa = new ViabilidadVisa()
            .destino(DEFAULT_DESTINO)
            .tipoPrograma(DEFAULT_TIPO_PROGRAMA)
            .duracion(DEFAULT_DURACION)
            .nacionalidadPrincipal(DEFAULT_NACIONALIDAD_PRINCIPAL)
            .otraNacionalidad(DEFAULT_OTRA_NACIONALIDAD)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .genero(DEFAULT_GENERO)
            .estadoCivil(DEFAULT_ESTADO_CIVIL)
            .viajaAcompanado(DEFAULT_VIAJA_ACOMPANADO)
            .personasCargo(DEFAULT_PERSONAS_CARGO)
            .nivelAcademico(DEFAULT_NIVEL_ACADEMICO)
            .profesion(DEFAULT_PROFESION)
            .fechaGadruacion(DEFAULT_FECHA_GADRUACION)
            .ocupacionActual(DEFAULT_OCUPACION_ACTUAL)
            .carta(DEFAULT_CARTA)
            .suspendidoEstudios(DEFAULT_SUSPENDIDO_ESTUDIOS)
            .peridoSupencionEstu(DEFAULT_PERIDO_SUPENCION_ESTU)
            .razonSuspencion(DEFAULT_RAZON_SUSPENCION)
            .tipoContrato(DEFAULT_TIPO_CONTRATO)
            .licenciaLaboral(DEFAULT_LICENCIA_LABORAL)
            .nivelSalarial(DEFAULT_NIVEL_SALARIAL)
            .tipoLaborIndependiente(DEFAULT_TIPO_LABOR_INDEPENDIENTE)
            .tiempoIndependiente(DEFAULT_TIEMPO_INDEPENDIENTE)
            .nivelIngresosIndependiente(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE)
            .tiempoDesempleado(DEFAULT_TIEMPO_DESEMPLEADO)
            .quienFinanciaEstudios(DEFAULT_QUIEN_FINANCIA_ESTUDIOS)
            .parentesco(DEFAULT_PARENTESCO)
            .presupuestoDisponible(DEFAULT_PRESUPUESTO_DISPONIBLE)
            .ahorroDisponible(DEFAULT_AHORRO_DISPONIBLE)
            .idioma(DEFAULT_IDIOMA)
            .certificarIdioma(DEFAULT_CERTIFICAR_IDIOMA)
            .certificacionIdioma(DEFAULT_CERTIFICACION_IDIOMA)
            .puntajeCertificacion(DEFAULT_PUNTAJE_CERTIFICACION)
            .salidasPais(DEFAULT_SALIDAS_PAIS)
            .paisesVisitados(DEFAULT_PAISES_VISITADOS)
            .visaPais(DEFAULT_VISA_PAIS)
            .estudiadoAnterior(DEFAULT_ESTUDIADO_ANTERIOR)
            .fueraPais(DEFAULT_FUERA_PAIS)
            .paisFuera(DEFAULT_PAIS_FUERA)
            .extenderEstadia(DEFAULT_EXTENDER_ESTADIA)
            .negadoVisa(DEFAULT_NEGADO_VISA)
            .paisNegado(DEFAULT_PAIS_NEGADO)
            .familiaresDestino(DEFAULT_FAMILIARES_DESTINO)
            .estatusMigratorio(DEFAULT_ESTATUS_MIGRATORIO)
            .nombre(DEFAULT_NOMBRE)
            .apelliod(DEFAULT_APELLIOD)
            .correo(DEFAULT_CORREO);
        return viabilidadVisa;
    }

    @Before
    public void initTest() {
        viabilidadVisa = createEntity(em);
    }

    @Test
    @Transactional
    public void createViabilidadVisa() throws Exception {
        int databaseSizeBeforeCreate = viabilidadVisaRepository.findAll().size();

        // Create the ViabilidadVisa
        restViabilidadVisaMockMvc.perform(post("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(viabilidadVisa)))
            .andExpect(status().isCreated());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeCreate + 1);
        ViabilidadVisa testViabilidadVisa = viabilidadVisaList.get(viabilidadVisaList.size() - 1);
        assertThat(testViabilidadVisa.getDestino()).isEqualTo(DEFAULT_DESTINO);
        assertThat(testViabilidadVisa.getTipoPrograma()).isEqualTo(DEFAULT_TIPO_PROGRAMA);
        assertThat(testViabilidadVisa.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testViabilidadVisa.getNacionalidadPrincipal()).isEqualTo(DEFAULT_NACIONALIDAD_PRINCIPAL);
        assertThat(testViabilidadVisa.getOtraNacionalidad()).isEqualTo(DEFAULT_OTRA_NACIONALIDAD);
        assertThat(testViabilidadVisa.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testViabilidadVisa.getGenero()).isEqualTo(DEFAULT_GENERO);
        assertThat(testViabilidadVisa.getEstadoCivil()).isEqualTo(DEFAULT_ESTADO_CIVIL);
        assertThat(testViabilidadVisa.isViajaAcompanado()).isEqualTo(DEFAULT_VIAJA_ACOMPANADO);
        assertThat(testViabilidadVisa.getPersonasCargo()).isEqualTo(DEFAULT_PERSONAS_CARGO);
        assertThat(testViabilidadVisa.getNivelAcademico()).isEqualTo(DEFAULT_NIVEL_ACADEMICO);
        assertThat(testViabilidadVisa.getProfesion()).isEqualTo(DEFAULT_PROFESION);
        assertThat(testViabilidadVisa.getFechaGadruacion()).isEqualTo(DEFAULT_FECHA_GADRUACION);
        assertThat(testViabilidadVisa.getOcupacionActual()).isEqualTo(DEFAULT_OCUPACION_ACTUAL);
        assertThat(testViabilidadVisa.isCarta()).isEqualTo(DEFAULT_CARTA);
        assertThat(testViabilidadVisa.isSuspendidoEstudios()).isEqualTo(DEFAULT_SUSPENDIDO_ESTUDIOS);
        assertThat(testViabilidadVisa.getPeridoSupencionEstu()).isEqualTo(DEFAULT_PERIDO_SUPENCION_ESTU);
        assertThat(testViabilidadVisa.getRazonSuspencion()).isEqualTo(DEFAULT_RAZON_SUSPENCION);
        assertThat(testViabilidadVisa.getTipoContrato()).isEqualTo(DEFAULT_TIPO_CONTRATO);
        assertThat(testViabilidadVisa.isLicenciaLaboral()).isEqualTo(DEFAULT_LICENCIA_LABORAL);
        assertThat(testViabilidadVisa.getNivelSalarial()).isEqualTo(DEFAULT_NIVEL_SALARIAL);
        assertThat(testViabilidadVisa.getTipoLaborIndependiente()).isEqualTo(DEFAULT_TIPO_LABOR_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoIndependiente()).isEqualTo(DEFAULT_TIEMPO_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getNivelIngresosIndependiente()).isEqualTo(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoDesempleado()).isEqualTo(DEFAULT_TIEMPO_DESEMPLEADO);
        assertThat(testViabilidadVisa.getQuienFinanciaEstudios()).isEqualTo(DEFAULT_QUIEN_FINANCIA_ESTUDIOS);
        assertThat(testViabilidadVisa.getParentesco()).isEqualTo(DEFAULT_PARENTESCO);
        assertThat(testViabilidadVisa.getPresupuestoDisponible()).isEqualTo(DEFAULT_PRESUPUESTO_DISPONIBLE);
        assertThat(testViabilidadVisa.isAhorroDisponible()).isEqualTo(DEFAULT_AHORRO_DISPONIBLE);
        assertThat(testViabilidadVisa.isIdioma()).isEqualTo(DEFAULT_IDIOMA);
        assertThat(testViabilidadVisa.isCertificarIdioma()).isEqualTo(DEFAULT_CERTIFICAR_IDIOMA);
        assertThat(testViabilidadVisa.getCertificacionIdioma()).isEqualTo(DEFAULT_CERTIFICACION_IDIOMA);
        assertThat(testViabilidadVisa.getPuntajeCertificacion()).isEqualTo(DEFAULT_PUNTAJE_CERTIFICACION);
        assertThat(testViabilidadVisa.getSalidasPais()).isEqualTo(DEFAULT_SALIDAS_PAIS);
        assertThat(testViabilidadVisa.getPaisesVisitados()).isEqualTo(DEFAULT_PAISES_VISITADOS);
        assertThat(testViabilidadVisa.getVisaPais()).isEqualTo(DEFAULT_VISA_PAIS);
        assertThat(testViabilidadVisa.isEstudiadoAnterior()).isEqualTo(DEFAULT_ESTUDIADO_ANTERIOR);
        assertThat(testViabilidadVisa.isFueraPais()).isEqualTo(DEFAULT_FUERA_PAIS);
        assertThat(testViabilidadVisa.getPaisFuera()).isEqualTo(DEFAULT_PAIS_FUERA);
        assertThat(testViabilidadVisa.isExtenderEstadia()).isEqualTo(DEFAULT_EXTENDER_ESTADIA);
        assertThat(testViabilidadVisa.isNegadoVisa()).isEqualTo(DEFAULT_NEGADO_VISA);
        assertThat(testViabilidadVisa.getPaisNegado()).isEqualTo(DEFAULT_PAIS_NEGADO);
        assertThat(testViabilidadVisa.isFamiliaresDestino()).isEqualTo(DEFAULT_FAMILIARES_DESTINO);
        assertThat(testViabilidadVisa.getEstatusMigratorio()).isEqualTo(DEFAULT_ESTATUS_MIGRATORIO);
        assertThat(testViabilidadVisa.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testViabilidadVisa.getApelliod()).isEqualTo(DEFAULT_APELLIOD);
        assertThat(testViabilidadVisa.getCorreo()).isEqualTo(DEFAULT_CORREO);
    }

    @Test
    @Transactional
    public void createViabilidadVisaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = viabilidadVisaRepository.findAll().size();

        // Create the ViabilidadVisa with an existing ID
        viabilidadVisa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restViabilidadVisaMockMvc.perform(post("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(viabilidadVisa)))
            .andExpect(status().isBadRequest());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllViabilidadVisas() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get all the viabilidadVisaList
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viabilidadVisa.getId().intValue())))
            .andExpect(jsonPath("$.[*].destino").value(hasItem(DEFAULT_DESTINO.toString())))
            .andExpect(jsonPath("$.[*].tipoPrograma").value(hasItem(DEFAULT_TIPO_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.toString())))
            .andExpect(jsonPath("$.[*].nacionalidadPrincipal").value(hasItem(DEFAULT_NACIONALIDAD_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].otraNacionalidad").value(hasItem(DEFAULT_OTRA_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].genero").value(hasItem(DEFAULT_GENERO.toString())))
            .andExpect(jsonPath("$.[*].estadoCivil").value(hasItem(DEFAULT_ESTADO_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].viajaAcompanado").value(hasItem(DEFAULT_VIAJA_ACOMPANADO.booleanValue())))
            .andExpect(jsonPath("$.[*].personasCargo").value(hasItem(DEFAULT_PERSONAS_CARGO.toString())))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].profesion").value(hasItem(DEFAULT_PROFESION.toString())))
            .andExpect(jsonPath("$.[*].fechaGadruacion").value(hasItem(DEFAULT_FECHA_GADRUACION.toString())))
            .andExpect(jsonPath("$.[*].ocupacionActual").value(hasItem(DEFAULT_OCUPACION_ACTUAL.toString())))
            .andExpect(jsonPath("$.[*].carta").value(hasItem(DEFAULT_CARTA.booleanValue())))
            .andExpect(jsonPath("$.[*].suspendidoEstudios").value(hasItem(DEFAULT_SUSPENDIDO_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].peridoSupencionEstu").value(hasItem(DEFAULT_PERIDO_SUPENCION_ESTU.toString())))
            .andExpect(jsonPath("$.[*].razonSuspencion").value(hasItem(DEFAULT_RAZON_SUSPENCION.toString())))
            .andExpect(jsonPath("$.[*].tipoContrato").value(hasItem(DEFAULT_TIPO_CONTRATO.toString())))
            .andExpect(jsonPath("$.[*].licenciaLaboral").value(hasItem(DEFAULT_LICENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].nivelSalarial").value(hasItem(DEFAULT_NIVEL_SALARIAL.toString())))
            .andExpect(jsonPath("$.[*].tipoLaborIndependiente").value(hasItem(DEFAULT_TIPO_LABOR_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoIndependiente").value(hasItem(DEFAULT_TIEMPO_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].nivelIngresosIndependiente").value(hasItem(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE.toString())))
            .andExpect(jsonPath("$.[*].tiempoDesempleado").value(hasItem(DEFAULT_TIEMPO_DESEMPLEADO.toString())))
            .andExpect(jsonPath("$.[*].quienFinanciaEstudios").value(hasItem(DEFAULT_QUIEN_FINANCIA_ESTUDIOS.toString())))
            .andExpect(jsonPath("$.[*].parentesco").value(hasItem(DEFAULT_PARENTESCO.toString())))
            .andExpect(jsonPath("$.[*].presupuestoDisponible").value(hasItem(DEFAULT_PRESUPUESTO_DISPONIBLE.toString())))
            .andExpect(jsonPath("$.[*].ahorroDisponible").value(hasItem(DEFAULT_AHORRO_DISPONIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].idioma").value(hasItem(DEFAULT_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificarIdioma").value(hasItem(DEFAULT_CERTIFICAR_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].certificacionIdioma").value(hasItem(DEFAULT_CERTIFICACION_IDIOMA.toString())))
            .andExpect(jsonPath("$.[*].puntajeCertificacion").value(hasItem(DEFAULT_PUNTAJE_CERTIFICACION.toString())))
            .andExpect(jsonPath("$.[*].salidasPais").value(hasItem(DEFAULT_SALIDAS_PAIS.toString())))
            .andExpect(jsonPath("$.[*].paisesVisitados").value(hasItem(DEFAULT_PAISES_VISITADOS.toString())))
            .andExpect(jsonPath("$.[*].visaPais").value(hasItem(DEFAULT_VISA_PAIS.toString())))
            .andExpect(jsonPath("$.[*].estudiadoAnterior").value(hasItem(DEFAULT_ESTUDIADO_ANTERIOR.booleanValue())))
            .andExpect(jsonPath("$.[*].fueraPais").value(hasItem(DEFAULT_FUERA_PAIS.booleanValue())))
            .andExpect(jsonPath("$.[*].paisFuera").value(hasItem(DEFAULT_PAIS_FUERA.toString())))
            .andExpect(jsonPath("$.[*].extenderEstadia").value(hasItem(DEFAULT_EXTENDER_ESTADIA.booleanValue())))
            .andExpect(jsonPath("$.[*].negadoVisa").value(hasItem(DEFAULT_NEGADO_VISA.booleanValue())))
            .andExpect(jsonPath("$.[*].paisNegado").value(hasItem(DEFAULT_PAIS_NEGADO.toString())))
            .andExpect(jsonPath("$.[*].familiaresDestino").value(hasItem(DEFAULT_FAMILIARES_DESTINO.booleanValue())))
            .andExpect(jsonPath("$.[*].estatusMigratorio").value(hasItem(DEFAULT_ESTATUS_MIGRATORIO.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apelliod").value(hasItem(DEFAULT_APELLIOD.toString())))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO.toString())));
    }
    
    @Test
    @Transactional
    public void getViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        // Get the viabilidadVisa
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas/{id}", viabilidadVisa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(viabilidadVisa.getId().intValue()))
            .andExpect(jsonPath("$.destino").value(DEFAULT_DESTINO.toString()))
            .andExpect(jsonPath("$.tipoPrograma").value(DEFAULT_TIPO_PROGRAMA.toString()))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION.toString()))
            .andExpect(jsonPath("$.nacionalidadPrincipal").value(DEFAULT_NACIONALIDAD_PRINCIPAL.toString()))
            .andExpect(jsonPath("$.otraNacionalidad").value(DEFAULT_OTRA_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.genero").value(DEFAULT_GENERO.toString()))
            .andExpect(jsonPath("$.estadoCivil").value(DEFAULT_ESTADO_CIVIL.toString()))
            .andExpect(jsonPath("$.viajaAcompanado").value(DEFAULT_VIAJA_ACOMPANADO.booleanValue()))
            .andExpect(jsonPath("$.personasCargo").value(DEFAULT_PERSONAS_CARGO.toString()))
            .andExpect(jsonPath("$.nivelAcademico").value(DEFAULT_NIVEL_ACADEMICO.toString()))
            .andExpect(jsonPath("$.profesion").value(DEFAULT_PROFESION.toString()))
            .andExpect(jsonPath("$.fechaGadruacion").value(DEFAULT_FECHA_GADRUACION.toString()))
            .andExpect(jsonPath("$.ocupacionActual").value(DEFAULT_OCUPACION_ACTUAL.toString()))
            .andExpect(jsonPath("$.carta").value(DEFAULT_CARTA.booleanValue()))
            .andExpect(jsonPath("$.suspendidoEstudios").value(DEFAULT_SUSPENDIDO_ESTUDIOS.booleanValue()))
            .andExpect(jsonPath("$.peridoSupencionEstu").value(DEFAULT_PERIDO_SUPENCION_ESTU.toString()))
            .andExpect(jsonPath("$.razonSuspencion").value(DEFAULT_RAZON_SUSPENCION.toString()))
            .andExpect(jsonPath("$.tipoContrato").value(DEFAULT_TIPO_CONTRATO.toString()))
            .andExpect(jsonPath("$.licenciaLaboral").value(DEFAULT_LICENCIA_LABORAL.booleanValue()))
            .andExpect(jsonPath("$.nivelSalarial").value(DEFAULT_NIVEL_SALARIAL.toString()))
            .andExpect(jsonPath("$.tipoLaborIndependiente").value(DEFAULT_TIPO_LABOR_INDEPENDIENTE.toString()))
            .andExpect(jsonPath("$.tiempoIndependiente").value(DEFAULT_TIEMPO_INDEPENDIENTE.toString()))
            .andExpect(jsonPath("$.nivelIngresosIndependiente").value(DEFAULT_NIVEL_INGRESOS_INDEPENDIENTE.toString()))
            .andExpect(jsonPath("$.tiempoDesempleado").value(DEFAULT_TIEMPO_DESEMPLEADO.toString()))
            .andExpect(jsonPath("$.quienFinanciaEstudios").value(DEFAULT_QUIEN_FINANCIA_ESTUDIOS.toString()))
            .andExpect(jsonPath("$.parentesco").value(DEFAULT_PARENTESCO.toString()))
            .andExpect(jsonPath("$.presupuestoDisponible").value(DEFAULT_PRESUPUESTO_DISPONIBLE.toString()))
            .andExpect(jsonPath("$.ahorroDisponible").value(DEFAULT_AHORRO_DISPONIBLE.booleanValue()))
            .andExpect(jsonPath("$.idioma").value(DEFAULT_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.certificarIdioma").value(DEFAULT_CERTIFICAR_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.certificacionIdioma").value(DEFAULT_CERTIFICACION_IDIOMA.toString()))
            .andExpect(jsonPath("$.puntajeCertificacion").value(DEFAULT_PUNTAJE_CERTIFICACION.toString()))
            .andExpect(jsonPath("$.salidasPais").value(DEFAULT_SALIDAS_PAIS.toString()))
            .andExpect(jsonPath("$.paisesVisitados").value(DEFAULT_PAISES_VISITADOS.toString()))
            .andExpect(jsonPath("$.visaPais").value(DEFAULT_VISA_PAIS.toString()))
            .andExpect(jsonPath("$.estudiadoAnterior").value(DEFAULT_ESTUDIADO_ANTERIOR.booleanValue()))
            .andExpect(jsonPath("$.fueraPais").value(DEFAULT_FUERA_PAIS.booleanValue()))
            .andExpect(jsonPath("$.paisFuera").value(DEFAULT_PAIS_FUERA.toString()))
            .andExpect(jsonPath("$.extenderEstadia").value(DEFAULT_EXTENDER_ESTADIA.booleanValue()))
            .andExpect(jsonPath("$.negadoVisa").value(DEFAULT_NEGADO_VISA.booleanValue()))
            .andExpect(jsonPath("$.paisNegado").value(DEFAULT_PAIS_NEGADO.toString()))
            .andExpect(jsonPath("$.familiaresDestino").value(DEFAULT_FAMILIARES_DESTINO.booleanValue()))
            .andExpect(jsonPath("$.estatusMigratorio").value(DEFAULT_ESTATUS_MIGRATORIO.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apelliod").value(DEFAULT_APELLIOD.toString()))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingViabilidadVisa() throws Exception {
        // Get the viabilidadVisa
        restViabilidadVisaMockMvc.perform(get("/api/viabilidad-visas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        int databaseSizeBeforeUpdate = viabilidadVisaRepository.findAll().size();

        // Update the viabilidadVisa
        ViabilidadVisa updatedViabilidadVisa = viabilidadVisaRepository.findById(viabilidadVisa.getId()).get();
        // Disconnect from session so that the updates on updatedViabilidadVisa are not directly saved in db
        em.detach(updatedViabilidadVisa);
        updatedViabilidadVisa
            .destino(UPDATED_DESTINO)
            .tipoPrograma(UPDATED_TIPO_PROGRAMA)
            .duracion(UPDATED_DURACION)
            .nacionalidadPrincipal(UPDATED_NACIONALIDAD_PRINCIPAL)
            .otraNacionalidad(UPDATED_OTRA_NACIONALIDAD)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .genero(UPDATED_GENERO)
            .estadoCivil(UPDATED_ESTADO_CIVIL)
            .viajaAcompanado(UPDATED_VIAJA_ACOMPANADO)
            .personasCargo(UPDATED_PERSONAS_CARGO)
            .nivelAcademico(UPDATED_NIVEL_ACADEMICO)
            .profesion(UPDATED_PROFESION)
            .fechaGadruacion(UPDATED_FECHA_GADRUACION)
            .ocupacionActual(UPDATED_OCUPACION_ACTUAL)
            .carta(UPDATED_CARTA)
            .suspendidoEstudios(UPDATED_SUSPENDIDO_ESTUDIOS)
            .peridoSupencionEstu(UPDATED_PERIDO_SUPENCION_ESTU)
            .razonSuspencion(UPDATED_RAZON_SUSPENCION)
            .tipoContrato(UPDATED_TIPO_CONTRATO)
            .licenciaLaboral(UPDATED_LICENCIA_LABORAL)
            .nivelSalarial(UPDATED_NIVEL_SALARIAL)
            .tipoLaborIndependiente(UPDATED_TIPO_LABOR_INDEPENDIENTE)
            .tiempoIndependiente(UPDATED_TIEMPO_INDEPENDIENTE)
            .nivelIngresosIndependiente(UPDATED_NIVEL_INGRESOS_INDEPENDIENTE)
            .tiempoDesempleado(UPDATED_TIEMPO_DESEMPLEADO)
            .quienFinanciaEstudios(UPDATED_QUIEN_FINANCIA_ESTUDIOS)
            .parentesco(UPDATED_PARENTESCO)
            .presupuestoDisponible(UPDATED_PRESUPUESTO_DISPONIBLE)
            .ahorroDisponible(UPDATED_AHORRO_DISPONIBLE)
            .idioma(UPDATED_IDIOMA)
            .certificarIdioma(UPDATED_CERTIFICAR_IDIOMA)
            .certificacionIdioma(UPDATED_CERTIFICACION_IDIOMA)
            .puntajeCertificacion(UPDATED_PUNTAJE_CERTIFICACION)
            .salidasPais(UPDATED_SALIDAS_PAIS)
            .paisesVisitados(UPDATED_PAISES_VISITADOS)
            .visaPais(UPDATED_VISA_PAIS)
            .estudiadoAnterior(UPDATED_ESTUDIADO_ANTERIOR)
            .fueraPais(UPDATED_FUERA_PAIS)
            .paisFuera(UPDATED_PAIS_FUERA)
            .extenderEstadia(UPDATED_EXTENDER_ESTADIA)
            .negadoVisa(UPDATED_NEGADO_VISA)
            .paisNegado(UPDATED_PAIS_NEGADO)
            .familiaresDestino(UPDATED_FAMILIARES_DESTINO)
            .estatusMigratorio(UPDATED_ESTATUS_MIGRATORIO)
            .nombre(UPDATED_NOMBRE)
            .apelliod(UPDATED_APELLIOD)
            .correo(UPDATED_CORREO);

        restViabilidadVisaMockMvc.perform(put("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedViabilidadVisa)))
            .andExpect(status().isOk());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeUpdate);
        ViabilidadVisa testViabilidadVisa = viabilidadVisaList.get(viabilidadVisaList.size() - 1);
        assertThat(testViabilidadVisa.getDestino()).isEqualTo(UPDATED_DESTINO);
        assertThat(testViabilidadVisa.getTipoPrograma()).isEqualTo(UPDATED_TIPO_PROGRAMA);
        assertThat(testViabilidadVisa.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testViabilidadVisa.getNacionalidadPrincipal()).isEqualTo(UPDATED_NACIONALIDAD_PRINCIPAL);
        assertThat(testViabilidadVisa.getOtraNacionalidad()).isEqualTo(UPDATED_OTRA_NACIONALIDAD);
        assertThat(testViabilidadVisa.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testViabilidadVisa.getGenero()).isEqualTo(UPDATED_GENERO);
        assertThat(testViabilidadVisa.getEstadoCivil()).isEqualTo(UPDATED_ESTADO_CIVIL);
        assertThat(testViabilidadVisa.isViajaAcompanado()).isEqualTo(UPDATED_VIAJA_ACOMPANADO);
        assertThat(testViabilidadVisa.getPersonasCargo()).isEqualTo(UPDATED_PERSONAS_CARGO);
        assertThat(testViabilidadVisa.getNivelAcademico()).isEqualTo(UPDATED_NIVEL_ACADEMICO);
        assertThat(testViabilidadVisa.getProfesion()).isEqualTo(UPDATED_PROFESION);
        assertThat(testViabilidadVisa.getFechaGadruacion()).isEqualTo(UPDATED_FECHA_GADRUACION);
        assertThat(testViabilidadVisa.getOcupacionActual()).isEqualTo(UPDATED_OCUPACION_ACTUAL);
        assertThat(testViabilidadVisa.isCarta()).isEqualTo(UPDATED_CARTA);
        assertThat(testViabilidadVisa.isSuspendidoEstudios()).isEqualTo(UPDATED_SUSPENDIDO_ESTUDIOS);
        assertThat(testViabilidadVisa.getPeridoSupencionEstu()).isEqualTo(UPDATED_PERIDO_SUPENCION_ESTU);
        assertThat(testViabilidadVisa.getRazonSuspencion()).isEqualTo(UPDATED_RAZON_SUSPENCION);
        assertThat(testViabilidadVisa.getTipoContrato()).isEqualTo(UPDATED_TIPO_CONTRATO);
        assertThat(testViabilidadVisa.isLicenciaLaboral()).isEqualTo(UPDATED_LICENCIA_LABORAL);
        assertThat(testViabilidadVisa.getNivelSalarial()).isEqualTo(UPDATED_NIVEL_SALARIAL);
        assertThat(testViabilidadVisa.getTipoLaborIndependiente()).isEqualTo(UPDATED_TIPO_LABOR_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoIndependiente()).isEqualTo(UPDATED_TIEMPO_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getNivelIngresosIndependiente()).isEqualTo(UPDATED_NIVEL_INGRESOS_INDEPENDIENTE);
        assertThat(testViabilidadVisa.getTiempoDesempleado()).isEqualTo(UPDATED_TIEMPO_DESEMPLEADO);
        assertThat(testViabilidadVisa.getQuienFinanciaEstudios()).isEqualTo(UPDATED_QUIEN_FINANCIA_ESTUDIOS);
        assertThat(testViabilidadVisa.getParentesco()).isEqualTo(UPDATED_PARENTESCO);
        assertThat(testViabilidadVisa.getPresupuestoDisponible()).isEqualTo(UPDATED_PRESUPUESTO_DISPONIBLE);
        assertThat(testViabilidadVisa.isAhorroDisponible()).isEqualTo(UPDATED_AHORRO_DISPONIBLE);
        assertThat(testViabilidadVisa.isIdioma()).isEqualTo(UPDATED_IDIOMA);
        assertThat(testViabilidadVisa.isCertificarIdioma()).isEqualTo(UPDATED_CERTIFICAR_IDIOMA);
        assertThat(testViabilidadVisa.getCertificacionIdioma()).isEqualTo(UPDATED_CERTIFICACION_IDIOMA);
        assertThat(testViabilidadVisa.getPuntajeCertificacion()).isEqualTo(UPDATED_PUNTAJE_CERTIFICACION);
        assertThat(testViabilidadVisa.getSalidasPais()).isEqualTo(UPDATED_SALIDAS_PAIS);
        assertThat(testViabilidadVisa.getPaisesVisitados()).isEqualTo(UPDATED_PAISES_VISITADOS);
        assertThat(testViabilidadVisa.getVisaPais()).isEqualTo(UPDATED_VISA_PAIS);
        assertThat(testViabilidadVisa.isEstudiadoAnterior()).isEqualTo(UPDATED_ESTUDIADO_ANTERIOR);
        assertThat(testViabilidadVisa.isFueraPais()).isEqualTo(UPDATED_FUERA_PAIS);
        assertThat(testViabilidadVisa.getPaisFuera()).isEqualTo(UPDATED_PAIS_FUERA);
        assertThat(testViabilidadVisa.isExtenderEstadia()).isEqualTo(UPDATED_EXTENDER_ESTADIA);
        assertThat(testViabilidadVisa.isNegadoVisa()).isEqualTo(UPDATED_NEGADO_VISA);
        assertThat(testViabilidadVisa.getPaisNegado()).isEqualTo(UPDATED_PAIS_NEGADO);
        assertThat(testViabilidadVisa.isFamiliaresDestino()).isEqualTo(UPDATED_FAMILIARES_DESTINO);
        assertThat(testViabilidadVisa.getEstatusMigratorio()).isEqualTo(UPDATED_ESTATUS_MIGRATORIO);
        assertThat(testViabilidadVisa.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testViabilidadVisa.getApelliod()).isEqualTo(UPDATED_APELLIOD);
        assertThat(testViabilidadVisa.getCorreo()).isEqualTo(UPDATED_CORREO);
    }

    @Test
    @Transactional
    public void updateNonExistingViabilidadVisa() throws Exception {
        int databaseSizeBeforeUpdate = viabilidadVisaRepository.findAll().size();

        // Create the ViabilidadVisa

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViabilidadVisaMockMvc.perform(put("/api/viabilidad-visas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(viabilidadVisa)))
            .andExpect(status().isBadRequest());

        // Validate the ViabilidadVisa in the database
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteViabilidadVisa() throws Exception {
        // Initialize the database
        viabilidadVisaRepository.saveAndFlush(viabilidadVisa);

        int databaseSizeBeforeDelete = viabilidadVisaRepository.findAll().size();

        // Delete the viabilidadVisa
        restViabilidadVisaMockMvc.perform(delete("/api/viabilidad-visas/{id}", viabilidadVisa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ViabilidadVisa> viabilidadVisaList = viabilidadVisaRepository.findAll();
        assertThat(viabilidadVisaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViabilidadVisa.class);
        ViabilidadVisa viabilidadVisa1 = new ViabilidadVisa();
        viabilidadVisa1.setId(1L);
        ViabilidadVisa viabilidadVisa2 = new ViabilidadVisa();
        viabilidadVisa2.setId(viabilidadVisa1.getId());
        assertThat(viabilidadVisa1).isEqualTo(viabilidadVisa2);
        viabilidadVisa2.setId(2L);
        assertThat(viabilidadVisa1).isNotEqualTo(viabilidadVisa2);
        viabilidadVisa1.setId(null);
        assertThat(viabilidadVisa1).isNotEqualTo(viabilidadVisa2);
    }
}
