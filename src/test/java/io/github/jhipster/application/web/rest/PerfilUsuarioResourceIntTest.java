package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.PerfilUsuario;
import io.github.jhipster.application.repository.PerfilUsuarioRepository;
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

/**
 * Test class for the PerfilUsuarioResource REST controller.
 *
 * @see PerfilUsuarioResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class PerfilUsuarioResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_NACIMIENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_NACIMIENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_AREA = 1;
    private static final Integer UPDATED_AREA = 2;

    private static final Integer DEFAULT_TELEFONO = 1;
    private static final Integer UPDATED_TELEFONO = 2;

    private static final String DEFAULT_NIVEL_ACADEMICO = "AAAAAAAAAA";
    private static final String UPDATED_NIVEL_ACADEMICO = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_ACADEMICA = "AAAAAAAAAA";
    private static final String UPDATED_AREA_ACADEMICA = "BBBBBBBBBB";

    private static final Integer DEFAULT_TERMINO_ACADEMICO = 1;
    private static final Integer UPDATED_TERMINO_ACADEMICO = 2;

    private static final Integer DEFAULT_PUNTAJE_ICFES = 1;
    private static final Integer UPDATED_PUNTAJE_ICFES = 2;

    private static final Integer DEFAULT_PROMEDIO_ACADEMICO = 1;
    private static final Integer UPDATED_PROMEDIO_ACADEMICO = 2;

    private static final Boolean DEFAULT_DOMINIO_IDIOMA = false;
    private static final Boolean UPDATED_DOMINIO_IDIOMA = true;

    private static final String DEFAULT_IDIOMAS = "AAAAAAAAAA";
    private static final String UPDATED_IDIOMAS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXAMEN_IDIOMA = false;
    private static final Boolean UPDATED_EXAMEN_IDIOMA = true;

    private static final String DEFAULT_EXAMEN_REALIZADO = "AAAAAAAAAA";
    private static final String UPDATED_EXAMEN_REALIZADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_PUNTAJE_IDIOMA = 1;
    private static final Integer UPDATED_PUNTAJE_IDIOMA = 2;

    private static final Boolean DEFAULT_BECA_OTORGADA = false;
    private static final Boolean UPDATED_BECA_OTORGADA = true;

    private static final Integer DEFAULT_TIPO_BECA = 1;
    private static final Integer UPDATED_TIPO_BECA = 2;

    private static final String DEFAULT_BECA_INSTITUCION = "AAAAAAAAAA";
    private static final String UPDATED_BECA_INSTITUCION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_GRUPO_SOCIAL = false;
    private static final Boolean UPDATED_GRUPO_SOCIAL = true;

    private static final String DEFAULT_FUNDACION = "AAAAAAAAAA";
    private static final String UPDATED_FUNDACION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MONITOR = false;
    private static final Boolean UPDATED_MONITOR = true;

    private static final String DEFAULT_MONITOR_MATERIA = "AAAAAAAAAA";
    private static final String UPDATED_MONITOR_MATERIA = "BBBBBBBBBB";

    private static final String DEFAULT_LOGROS_ACADEMICOS = "AAAAAAAAAA";
    private static final String UPDATED_LOGROS_ACADEMICOS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXPERIENCIA_LABORAL = false;
    private static final Boolean UPDATED_EXPERIENCIA_LABORAL = true;

    private static final String DEFAULT_AREA_LABORAL = "AAAAAAAAAA";
    private static final String UPDATED_AREA_LABORAL = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROGRAMAREALIZAR = 1;
    private static final Integer UPDATED_PROGRAMAREALIZAR = 2;

    private static final String DEFAULT_PROGRAMA_AREA = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_AREA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_PROGRAMA_ENCONTRADO = false;
    private static final Boolean UPDATED_PROGRAMA_ENCONTRADO = true;

    private static final String DEFAULT_NOMBRE_PROGRAMA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_PROGRAMA = "BBBBBBBBBB";

    private static final String DEFAULT_UNIVERSIDAD = "AAAAAAAAAA";
    private static final String UPDATED_UNIVERSIDAD = "BBBBBBBBBB";

    private static final Integer DEFAULT_PAIS = 1;
    private static final Integer UPDATED_PAIS = 2;

    private static final String DEFAULT_MERECEDOR_BECA = "AAAAAAAAAA";
    private static final String UPDATED_MERECEDOR_BECA = "BBBBBBBBBB";

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

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

    private MockMvc restPerfilUsuarioMockMvc;

    private PerfilUsuario perfilUsuario;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PerfilUsuarioResource perfilUsuarioResource = new PerfilUsuarioResource(perfilUsuarioRepository);
        this.restPerfilUsuarioMockMvc = MockMvcBuilders.standaloneSetup(perfilUsuarioResource)
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
    public static PerfilUsuario createEntity(EntityManager em) {
        PerfilUsuario perfilUsuario = new PerfilUsuario()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .fechaNacimiento(DEFAULT_FECHA_NACIMIENTO)
            .mail(DEFAULT_MAIL)
            .area(DEFAULT_AREA)
            .telefono(DEFAULT_TELEFONO)
            .nivelAcademico(DEFAULT_NIVEL_ACADEMICO)
            .areaAcademica(DEFAULT_AREA_ACADEMICA)
            .terminoAcademico(DEFAULT_TERMINO_ACADEMICO)
            .puntajeICFES(DEFAULT_PUNTAJE_ICFES)
            .promedioAcademico(DEFAULT_PROMEDIO_ACADEMICO)
            .dominioIdioma(DEFAULT_DOMINIO_IDIOMA)
            .idiomas(DEFAULT_IDIOMAS)
            .examenIdioma(DEFAULT_EXAMEN_IDIOMA)
            .examenRealizado(DEFAULT_EXAMEN_REALIZADO)
            .puntajeIdioma(DEFAULT_PUNTAJE_IDIOMA)
            .becaOtorgada(DEFAULT_BECA_OTORGADA)
            .tipoBeca(DEFAULT_TIPO_BECA)
            .becaInstitucion(DEFAULT_BECA_INSTITUCION)
            .grupoSocial(DEFAULT_GRUPO_SOCIAL)
            .fundacion(DEFAULT_FUNDACION)
            .monitor(DEFAULT_MONITOR)
            .monitorMateria(DEFAULT_MONITOR_MATERIA)
            .logrosAcademicos(DEFAULT_LOGROS_ACADEMICOS)
            .experienciaLaboral(DEFAULT_EXPERIENCIA_LABORAL)
            .areaLaboral(DEFAULT_AREA_LABORAL)
            .programarealizar(DEFAULT_PROGRAMAREALIZAR)
            .programaArea(DEFAULT_PROGRAMA_AREA)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .programaEncontrado(DEFAULT_PROGRAMA_ENCONTRADO)
            .nombrePrograma(DEFAULT_NOMBRE_PROGRAMA)
            .universidad(DEFAULT_UNIVERSIDAD)
            .pais(DEFAULT_PAIS)
            .merecedorBeca(DEFAULT_MERECEDOR_BECA);
        return perfilUsuario;
    }

    @Before
    public void initTest() {
        perfilUsuario = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerfilUsuario() throws Exception {
        int databaseSizeBeforeCreate = perfilUsuarioRepository.findAll().size();

        // Create the PerfilUsuario
        restPerfilUsuarioMockMvc.perform(post("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilUsuario)))
            .andExpect(status().isCreated());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeCreate + 1);
        PerfilUsuario testPerfilUsuario = perfilUsuarioList.get(perfilUsuarioList.size() - 1);
        assertThat(testPerfilUsuario.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPerfilUsuario.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testPerfilUsuario.getFechaNacimiento()).isEqualTo(DEFAULT_FECHA_NACIMIENTO);
        assertThat(testPerfilUsuario.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testPerfilUsuario.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testPerfilUsuario.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testPerfilUsuario.getNivelAcademico()).isEqualTo(DEFAULT_NIVEL_ACADEMICO);
        assertThat(testPerfilUsuario.getAreaAcademica()).isEqualTo(DEFAULT_AREA_ACADEMICA);
        assertThat(testPerfilUsuario.getTerminoAcademico()).isEqualTo(DEFAULT_TERMINO_ACADEMICO);
        assertThat(testPerfilUsuario.getPuntajeICFES()).isEqualTo(DEFAULT_PUNTAJE_ICFES);
        assertThat(testPerfilUsuario.getPromedioAcademico()).isEqualTo(DEFAULT_PROMEDIO_ACADEMICO);
        assertThat(testPerfilUsuario.isDominioIdioma()).isEqualTo(DEFAULT_DOMINIO_IDIOMA);
        assertThat(testPerfilUsuario.getIdiomas()).isEqualTo(DEFAULT_IDIOMAS);
        assertThat(testPerfilUsuario.isExamenIdioma()).isEqualTo(DEFAULT_EXAMEN_IDIOMA);
        assertThat(testPerfilUsuario.getExamenRealizado()).isEqualTo(DEFAULT_EXAMEN_REALIZADO);
        assertThat(testPerfilUsuario.getPuntajeIdioma()).isEqualTo(DEFAULT_PUNTAJE_IDIOMA);
        assertThat(testPerfilUsuario.isBecaOtorgada()).isEqualTo(DEFAULT_BECA_OTORGADA);
        assertThat(testPerfilUsuario.getTipoBeca()).isEqualTo(DEFAULT_TIPO_BECA);
        assertThat(testPerfilUsuario.getBecaInstitucion()).isEqualTo(DEFAULT_BECA_INSTITUCION);
        assertThat(testPerfilUsuario.isGrupoSocial()).isEqualTo(DEFAULT_GRUPO_SOCIAL);
        assertThat(testPerfilUsuario.getFundacion()).isEqualTo(DEFAULT_FUNDACION);
        assertThat(testPerfilUsuario.isMonitor()).isEqualTo(DEFAULT_MONITOR);
        assertThat(testPerfilUsuario.getMonitorMateria()).isEqualTo(DEFAULT_MONITOR_MATERIA);
        assertThat(testPerfilUsuario.getLogrosAcademicos()).isEqualTo(DEFAULT_LOGROS_ACADEMICOS);
        assertThat(testPerfilUsuario.isExperienciaLaboral()).isEqualTo(DEFAULT_EXPERIENCIA_LABORAL);
        assertThat(testPerfilUsuario.getAreaLaboral()).isEqualTo(DEFAULT_AREA_LABORAL);
        assertThat(testPerfilUsuario.getProgramarealizar()).isEqualTo(DEFAULT_PROGRAMAREALIZAR);
        assertThat(testPerfilUsuario.getProgramaArea()).isEqualTo(DEFAULT_PROGRAMA_AREA);
        assertThat(testPerfilUsuario.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testPerfilUsuario.isProgramaEncontrado()).isEqualTo(DEFAULT_PROGRAMA_ENCONTRADO);
        assertThat(testPerfilUsuario.getNombrePrograma()).isEqualTo(DEFAULT_NOMBRE_PROGRAMA);
        assertThat(testPerfilUsuario.getUniversidad()).isEqualTo(DEFAULT_UNIVERSIDAD);
        assertThat(testPerfilUsuario.getPais()).isEqualTo(DEFAULT_PAIS);
        assertThat(testPerfilUsuario.getMerecedorBeca()).isEqualTo(DEFAULT_MERECEDOR_BECA);
    }

    @Test
    @Transactional
    public void createPerfilUsuarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perfilUsuarioRepository.findAll().size();

        // Create the PerfilUsuario with an existing ID
        perfilUsuario.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerfilUsuarioMockMvc.perform(post("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilUsuario)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPerfilUsuarios() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get all the perfilUsuarioList
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilUsuario.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].fechaNacimiento").value(hasItem(DEFAULT_FECHA_NACIMIENTO.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].nivelAcademico").value(hasItem(DEFAULT_NIVEL_ACADEMICO.toString())))
            .andExpect(jsonPath("$.[*].areaAcademica").value(hasItem(DEFAULT_AREA_ACADEMICA.toString())))
            .andExpect(jsonPath("$.[*].terminoAcademico").value(hasItem(DEFAULT_TERMINO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].puntajeICFES").value(hasItem(DEFAULT_PUNTAJE_ICFES)))
            .andExpect(jsonPath("$.[*].promedioAcademico").value(hasItem(DEFAULT_PROMEDIO_ACADEMICO)))
            .andExpect(jsonPath("$.[*].dominioIdioma").value(hasItem(DEFAULT_DOMINIO_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].idiomas").value(hasItem(DEFAULT_IDIOMAS.toString())))
            .andExpect(jsonPath("$.[*].examenIdioma").value(hasItem(DEFAULT_EXAMEN_IDIOMA.booleanValue())))
            .andExpect(jsonPath("$.[*].examenRealizado").value(hasItem(DEFAULT_EXAMEN_REALIZADO.toString())))
            .andExpect(jsonPath("$.[*].puntajeIdioma").value(hasItem(DEFAULT_PUNTAJE_IDIOMA)))
            .andExpect(jsonPath("$.[*].becaOtorgada").value(hasItem(DEFAULT_BECA_OTORGADA.booleanValue())))
            .andExpect(jsonPath("$.[*].tipoBeca").value(hasItem(DEFAULT_TIPO_BECA)))
            .andExpect(jsonPath("$.[*].becaInstitucion").value(hasItem(DEFAULT_BECA_INSTITUCION.toString())))
            .andExpect(jsonPath("$.[*].grupoSocial").value(hasItem(DEFAULT_GRUPO_SOCIAL.booleanValue())))
            .andExpect(jsonPath("$.[*].fundacion").value(hasItem(DEFAULT_FUNDACION.toString())))
            .andExpect(jsonPath("$.[*].monitor").value(hasItem(DEFAULT_MONITOR.booleanValue())))
            .andExpect(jsonPath("$.[*].monitorMateria").value(hasItem(DEFAULT_MONITOR_MATERIA.toString())))
            .andExpect(jsonPath("$.[*].logrosAcademicos").value(hasItem(DEFAULT_LOGROS_ACADEMICOS.toString())))
            .andExpect(jsonPath("$.[*].experienciaLaboral").value(hasItem(DEFAULT_EXPERIENCIA_LABORAL.booleanValue())))
            .andExpect(jsonPath("$.[*].areaLaboral").value(hasItem(DEFAULT_AREA_LABORAL.toString())))
            .andExpect(jsonPath("$.[*].programarealizar").value(hasItem(DEFAULT_PROGRAMAREALIZAR)))
            .andExpect(jsonPath("$.[*].programaArea").value(hasItem(DEFAULT_PROGRAMA_AREA.toString())))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].programaEncontrado").value(hasItem(DEFAULT_PROGRAMA_ENCONTRADO.booleanValue())))
            .andExpect(jsonPath("$.[*].nombrePrograma").value(hasItem(DEFAULT_NOMBRE_PROGRAMA.toString())))
            .andExpect(jsonPath("$.[*].universidad").value(hasItem(DEFAULT_UNIVERSIDAD.toString())))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].merecedorBeca").value(hasItem(DEFAULT_MERECEDOR_BECA.toString())));
    }
    
    @Test
    @Transactional
    public void getPerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        // Get the perfilUsuario
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios/{id}", perfilUsuario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perfilUsuario.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.fechaNacimiento").value(DEFAULT_FECHA_NACIMIENTO.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.nivelAcademico").value(DEFAULT_NIVEL_ACADEMICO.toString()))
            .andExpect(jsonPath("$.areaAcademica").value(DEFAULT_AREA_ACADEMICA.toString()))
            .andExpect(jsonPath("$.terminoAcademico").value(DEFAULT_TERMINO_ACADEMICO))
            .andExpect(jsonPath("$.puntajeICFES").value(DEFAULT_PUNTAJE_ICFES))
            .andExpect(jsonPath("$.promedioAcademico").value(DEFAULT_PROMEDIO_ACADEMICO))
            .andExpect(jsonPath("$.dominioIdioma").value(DEFAULT_DOMINIO_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.idiomas").value(DEFAULT_IDIOMAS.toString()))
            .andExpect(jsonPath("$.examenIdioma").value(DEFAULT_EXAMEN_IDIOMA.booleanValue()))
            .andExpect(jsonPath("$.examenRealizado").value(DEFAULT_EXAMEN_REALIZADO.toString()))
            .andExpect(jsonPath("$.puntajeIdioma").value(DEFAULT_PUNTAJE_IDIOMA))
            .andExpect(jsonPath("$.becaOtorgada").value(DEFAULT_BECA_OTORGADA.booleanValue()))
            .andExpect(jsonPath("$.tipoBeca").value(DEFAULT_TIPO_BECA))
            .andExpect(jsonPath("$.becaInstitucion").value(DEFAULT_BECA_INSTITUCION.toString()))
            .andExpect(jsonPath("$.grupoSocial").value(DEFAULT_GRUPO_SOCIAL.booleanValue()))
            .andExpect(jsonPath("$.fundacion").value(DEFAULT_FUNDACION.toString()))
            .andExpect(jsonPath("$.monitor").value(DEFAULT_MONITOR.booleanValue()))
            .andExpect(jsonPath("$.monitorMateria").value(DEFAULT_MONITOR_MATERIA.toString()))
            .andExpect(jsonPath("$.logrosAcademicos").value(DEFAULT_LOGROS_ACADEMICOS.toString()))
            .andExpect(jsonPath("$.experienciaLaboral").value(DEFAULT_EXPERIENCIA_LABORAL.booleanValue()))
            .andExpect(jsonPath("$.areaLaboral").value(DEFAULT_AREA_LABORAL.toString()))
            .andExpect(jsonPath("$.programarealizar").value(DEFAULT_PROGRAMAREALIZAR))
            .andExpect(jsonPath("$.programaArea").value(DEFAULT_PROGRAMA_AREA.toString()))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.programaEncontrado").value(DEFAULT_PROGRAMA_ENCONTRADO.booleanValue()))
            .andExpect(jsonPath("$.nombrePrograma").value(DEFAULT_NOMBRE_PROGRAMA.toString()))
            .andExpect(jsonPath("$.universidad").value(DEFAULT_UNIVERSIDAD.toString()))
            .andExpect(jsonPath("$.pais").value(DEFAULT_PAIS))
            .andExpect(jsonPath("$.merecedorBeca").value(DEFAULT_MERECEDOR_BECA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPerfilUsuario() throws Exception {
        // Get the perfilUsuario
        restPerfilUsuarioMockMvc.perform(get("/api/perfil-usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        int databaseSizeBeforeUpdate = perfilUsuarioRepository.findAll().size();

        // Update the perfilUsuario
        PerfilUsuario updatedPerfilUsuario = perfilUsuarioRepository.findById(perfilUsuario.getId()).get();
        // Disconnect from session so that the updates on updatedPerfilUsuario are not directly saved in db
        em.detach(updatedPerfilUsuario);
        updatedPerfilUsuario
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .fechaNacimiento(UPDATED_FECHA_NACIMIENTO)
            .mail(UPDATED_MAIL)
            .area(UPDATED_AREA)
            .telefono(UPDATED_TELEFONO)
            .nivelAcademico(UPDATED_NIVEL_ACADEMICO)
            .areaAcademica(UPDATED_AREA_ACADEMICA)
            .terminoAcademico(UPDATED_TERMINO_ACADEMICO)
            .puntajeICFES(UPDATED_PUNTAJE_ICFES)
            .promedioAcademico(UPDATED_PROMEDIO_ACADEMICO)
            .dominioIdioma(UPDATED_DOMINIO_IDIOMA)
            .idiomas(UPDATED_IDIOMAS)
            .examenIdioma(UPDATED_EXAMEN_IDIOMA)
            .examenRealizado(UPDATED_EXAMEN_REALIZADO)
            .puntajeIdioma(UPDATED_PUNTAJE_IDIOMA)
            .becaOtorgada(UPDATED_BECA_OTORGADA)
            .tipoBeca(UPDATED_TIPO_BECA)
            .becaInstitucion(UPDATED_BECA_INSTITUCION)
            .grupoSocial(UPDATED_GRUPO_SOCIAL)
            .fundacion(UPDATED_FUNDACION)
            .monitor(UPDATED_MONITOR)
            .monitorMateria(UPDATED_MONITOR_MATERIA)
            .logrosAcademicos(UPDATED_LOGROS_ACADEMICOS)
            .experienciaLaboral(UPDATED_EXPERIENCIA_LABORAL)
            .areaLaboral(UPDATED_AREA_LABORAL)
            .programarealizar(UPDATED_PROGRAMAREALIZAR)
            .programaArea(UPDATED_PROGRAMA_AREA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .programaEncontrado(UPDATED_PROGRAMA_ENCONTRADO)
            .nombrePrograma(UPDATED_NOMBRE_PROGRAMA)
            .universidad(UPDATED_UNIVERSIDAD)
            .pais(UPDATED_PAIS)
            .merecedorBeca(UPDATED_MERECEDOR_BECA);

        restPerfilUsuarioMockMvc.perform(put("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerfilUsuario)))
            .andExpect(status().isOk());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeUpdate);
        PerfilUsuario testPerfilUsuario = perfilUsuarioList.get(perfilUsuarioList.size() - 1);
        assertThat(testPerfilUsuario.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPerfilUsuario.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testPerfilUsuario.getFechaNacimiento()).isEqualTo(UPDATED_FECHA_NACIMIENTO);
        assertThat(testPerfilUsuario.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testPerfilUsuario.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testPerfilUsuario.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testPerfilUsuario.getNivelAcademico()).isEqualTo(UPDATED_NIVEL_ACADEMICO);
        assertThat(testPerfilUsuario.getAreaAcademica()).isEqualTo(UPDATED_AREA_ACADEMICA);
        assertThat(testPerfilUsuario.getTerminoAcademico()).isEqualTo(UPDATED_TERMINO_ACADEMICO);
        assertThat(testPerfilUsuario.getPuntajeICFES()).isEqualTo(UPDATED_PUNTAJE_ICFES);
        assertThat(testPerfilUsuario.getPromedioAcademico()).isEqualTo(UPDATED_PROMEDIO_ACADEMICO);
        assertThat(testPerfilUsuario.isDominioIdioma()).isEqualTo(UPDATED_DOMINIO_IDIOMA);
        assertThat(testPerfilUsuario.getIdiomas()).isEqualTo(UPDATED_IDIOMAS);
        assertThat(testPerfilUsuario.isExamenIdioma()).isEqualTo(UPDATED_EXAMEN_IDIOMA);
        assertThat(testPerfilUsuario.getExamenRealizado()).isEqualTo(UPDATED_EXAMEN_REALIZADO);
        assertThat(testPerfilUsuario.getPuntajeIdioma()).isEqualTo(UPDATED_PUNTAJE_IDIOMA);
        assertThat(testPerfilUsuario.isBecaOtorgada()).isEqualTo(UPDATED_BECA_OTORGADA);
        assertThat(testPerfilUsuario.getTipoBeca()).isEqualTo(UPDATED_TIPO_BECA);
        assertThat(testPerfilUsuario.getBecaInstitucion()).isEqualTo(UPDATED_BECA_INSTITUCION);
        assertThat(testPerfilUsuario.isGrupoSocial()).isEqualTo(UPDATED_GRUPO_SOCIAL);
        assertThat(testPerfilUsuario.getFundacion()).isEqualTo(UPDATED_FUNDACION);
        assertThat(testPerfilUsuario.isMonitor()).isEqualTo(UPDATED_MONITOR);
        assertThat(testPerfilUsuario.getMonitorMateria()).isEqualTo(UPDATED_MONITOR_MATERIA);
        assertThat(testPerfilUsuario.getLogrosAcademicos()).isEqualTo(UPDATED_LOGROS_ACADEMICOS);
        assertThat(testPerfilUsuario.isExperienciaLaboral()).isEqualTo(UPDATED_EXPERIENCIA_LABORAL);
        assertThat(testPerfilUsuario.getAreaLaboral()).isEqualTo(UPDATED_AREA_LABORAL);
        assertThat(testPerfilUsuario.getProgramarealizar()).isEqualTo(UPDATED_PROGRAMAREALIZAR);
        assertThat(testPerfilUsuario.getProgramaArea()).isEqualTo(UPDATED_PROGRAMA_AREA);
        assertThat(testPerfilUsuario.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testPerfilUsuario.isProgramaEncontrado()).isEqualTo(UPDATED_PROGRAMA_ENCONTRADO);
        assertThat(testPerfilUsuario.getNombrePrograma()).isEqualTo(UPDATED_NOMBRE_PROGRAMA);
        assertThat(testPerfilUsuario.getUniversidad()).isEqualTo(UPDATED_UNIVERSIDAD);
        assertThat(testPerfilUsuario.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testPerfilUsuario.getMerecedorBeca()).isEqualTo(UPDATED_MERECEDOR_BECA);
    }

    @Test
    @Transactional
    public void updateNonExistingPerfilUsuario() throws Exception {
        int databaseSizeBeforeUpdate = perfilUsuarioRepository.findAll().size();

        // Create the PerfilUsuario

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerfilUsuarioMockMvc.perform(put("/api/perfil-usuarios")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilUsuario)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilUsuario in the database
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerfilUsuario() throws Exception {
        // Initialize the database
        perfilUsuarioRepository.saveAndFlush(perfilUsuario);

        int databaseSizeBeforeDelete = perfilUsuarioRepository.findAll().size();

        // Delete the perfilUsuario
        restPerfilUsuarioMockMvc.perform(delete("/api/perfil-usuarios/{id}", perfilUsuario.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PerfilUsuario> perfilUsuarioList = perfilUsuarioRepository.findAll();
        assertThat(perfilUsuarioList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerfilUsuario.class);
        PerfilUsuario perfilUsuario1 = new PerfilUsuario();
        perfilUsuario1.setId(1L);
        PerfilUsuario perfilUsuario2 = new PerfilUsuario();
        perfilUsuario2.setId(perfilUsuario1.getId());
        assertThat(perfilUsuario1).isEqualTo(perfilUsuario2);
        perfilUsuario2.setId(2L);
        assertThat(perfilUsuario1).isNotEqualTo(perfilUsuario2);
        perfilUsuario1.setId(null);
        assertThat(perfilUsuario1).isNotEqualTo(perfilUsuario2);
    }
}
