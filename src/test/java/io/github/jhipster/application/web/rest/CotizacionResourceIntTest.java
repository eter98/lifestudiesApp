package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.Cotizacion;
import io.github.jhipster.application.repository.CotizacionRepository;
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
 * Test class for the CotizacionResource REST controller.
 *
 * @see CotizacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class CotizacionResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AREA_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIL = "AAAAAAAAAA";
    private static final String UPDATED_MOVIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_NACIONALIDAD = 1;
    private static final Integer UPDATED_NACIONALIDAD = 2;

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_VIAJE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_VIAJE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private CotizacionRepository cotizacionRepository;

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

    private MockMvc restCotizacionMockMvc;

    private Cotizacion cotizacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CotizacionResource cotizacionResource = new CotizacionResource(cotizacionRepository);
        this.restCotizacionMockMvc = MockMvcBuilders.standaloneSetup(cotizacionResource)
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
    public static Cotizacion createEntity(EntityManager em) {
        Cotizacion cotizacion = new Cotizacion()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .mail(DEFAULT_MAIL)
            .areaCode(DEFAULT_AREA_CODE)
            .telefono(DEFAULT_TELEFONO)
            .movil(DEFAULT_MOVIL)
            .nacionalidad(DEFAULT_NACIONALIDAD)
            .area(DEFAULT_AREA)
            .fechaViaje(DEFAULT_FECHA_VIAJE);
        return cotizacion;
    }

    @Before
    public void initTest() {
        cotizacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCotizacion() throws Exception {
        int databaseSizeBeforeCreate = cotizacionRepository.findAll().size();

        // Create the Cotizacion
        restCotizacionMockMvc.perform(post("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cotizacion)))
            .andExpect(status().isCreated());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeCreate + 1);
        Cotizacion testCotizacion = cotizacionList.get(cotizacionList.size() - 1);
        assertThat(testCotizacion.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testCotizacion.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testCotizacion.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testCotizacion.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
        assertThat(testCotizacion.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testCotizacion.getMovil()).isEqualTo(DEFAULT_MOVIL);
        assertThat(testCotizacion.getNacionalidad()).isEqualTo(DEFAULT_NACIONALIDAD);
        assertThat(testCotizacion.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testCotizacion.getFechaViaje()).isEqualTo(DEFAULT_FECHA_VIAJE);
    }

    @Test
    @Transactional
    public void createCotizacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cotizacionRepository.findAll().size();

        // Create the Cotizacion with an existing ID
        cotizacion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCotizacionMockMvc.perform(post("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cotizacion)))
            .andExpect(status().isBadRequest());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCotizacions() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get all the cotizacionList
        restCotizacionMockMvc.perform(get("/api/cotizacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cotizacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].movil").value(hasItem(DEFAULT_MOVIL.toString())))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
            .andExpect(jsonPath("$.[*].fechaViaje").value(hasItem(DEFAULT_FECHA_VIAJE.toString())));
    }
    
    @Test
    @Transactional
    public void getCotizacion() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        // Get the cotizacion
        restCotizacionMockMvc.perform(get("/api/cotizacions/{id}", cotizacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cotizacion.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.areaCode").value(DEFAULT_AREA_CODE.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()))
            .andExpect(jsonPath("$.movil").value(DEFAULT_MOVIL.toString()))
            .andExpect(jsonPath("$.nacionalidad").value(DEFAULT_NACIONALIDAD))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.fechaViaje").value(DEFAULT_FECHA_VIAJE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCotizacion() throws Exception {
        // Get the cotizacion
        restCotizacionMockMvc.perform(get("/api/cotizacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCotizacion() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        int databaseSizeBeforeUpdate = cotizacionRepository.findAll().size();

        // Update the cotizacion
        Cotizacion updatedCotizacion = cotizacionRepository.findById(cotizacion.getId()).get();
        // Disconnect from session so that the updates on updatedCotizacion are not directly saved in db
        em.detach(updatedCotizacion);
        updatedCotizacion
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .mail(UPDATED_MAIL)
            .areaCode(UPDATED_AREA_CODE)
            .telefono(UPDATED_TELEFONO)
            .movil(UPDATED_MOVIL)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .area(UPDATED_AREA)
            .fechaViaje(UPDATED_FECHA_VIAJE);

        restCotizacionMockMvc.perform(put("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCotizacion)))
            .andExpect(status().isOk());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeUpdate);
        Cotizacion testCotizacion = cotizacionList.get(cotizacionList.size() - 1);
        assertThat(testCotizacion.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testCotizacion.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testCotizacion.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testCotizacion.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
        assertThat(testCotizacion.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testCotizacion.getMovil()).isEqualTo(UPDATED_MOVIL);
        assertThat(testCotizacion.getNacionalidad()).isEqualTo(UPDATED_NACIONALIDAD);
        assertThat(testCotizacion.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testCotizacion.getFechaViaje()).isEqualTo(UPDATED_FECHA_VIAJE);
    }

    @Test
    @Transactional
    public void updateNonExistingCotizacion() throws Exception {
        int databaseSizeBeforeUpdate = cotizacionRepository.findAll().size();

        // Create the Cotizacion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCotizacionMockMvc.perform(put("/api/cotizacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cotizacion)))
            .andExpect(status().isBadRequest());

        // Validate the Cotizacion in the database
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCotizacion() throws Exception {
        // Initialize the database
        cotizacionRepository.saveAndFlush(cotizacion);

        int databaseSizeBeforeDelete = cotizacionRepository.findAll().size();

        // Delete the cotizacion
        restCotizacionMockMvc.perform(delete("/api/cotizacions/{id}", cotizacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cotizacion> cotizacionList = cotizacionRepository.findAll();
        assertThat(cotizacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cotizacion.class);
        Cotizacion cotizacion1 = new Cotizacion();
        cotizacion1.setId(1L);
        Cotizacion cotizacion2 = new Cotizacion();
        cotizacion2.setId(cotizacion1.getId());
        assertThat(cotizacion1).isEqualTo(cotizacion2);
        cotizacion2.setId(2L);
        assertThat(cotizacion1).isNotEqualTo(cotizacion2);
        cotizacion1.setId(null);
        assertThat(cotizacion1).isNotEqualTo(cotizacion2);
    }
}
