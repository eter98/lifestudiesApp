package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.TipoPrograma;
import io.github.jhipster.application.repository.TipoProgramaRepository;
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
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TipoProgramaResource REST controller.
 *
 * @see TipoProgramaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class TipoProgramaResourceIntTest {

    private static final String DEFAULT_TIPO_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private TipoProgramaRepository tipoProgramaRepository;

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

    private MockMvc restTipoProgramaMockMvc;

    private TipoPrograma tipoPrograma;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoProgramaResource tipoProgramaResource = new TipoProgramaResource(tipoProgramaRepository);
        this.restTipoProgramaMockMvc = MockMvcBuilders.standaloneSetup(tipoProgramaResource)
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
    public static TipoPrograma createEntity(EntityManager em) {
        TipoPrograma tipoPrograma = new TipoPrograma()
            .tipoNombre(DEFAULT_TIPO_NOMBRE);
        return tipoPrograma;
    }

    @Before
    public void initTest() {
        tipoPrograma = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoPrograma() throws Exception {
        int databaseSizeBeforeCreate = tipoProgramaRepository.findAll().size();

        // Create the TipoPrograma
        restTipoProgramaMockMvc.perform(post("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrograma)))
            .andExpect(status().isCreated());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeCreate + 1);
        TipoPrograma testTipoPrograma = tipoProgramaList.get(tipoProgramaList.size() - 1);
        assertThat(testTipoPrograma.getTipoNombre()).isEqualTo(DEFAULT_TIPO_NOMBRE);
    }

    @Test
    @Transactional
    public void createTipoProgramaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoProgramaRepository.findAll().size();

        // Create the TipoPrograma with an existing ID
        tipoPrograma.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoProgramaMockMvc.perform(post("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrograma)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTipoProgramas() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get all the tipoProgramaList
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoPrograma.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoNombre").value(hasItem(DEFAULT_TIPO_NOMBRE.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        // Get the tipoPrograma
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas/{id}", tipoPrograma.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoPrograma.getId().intValue()))
            .andExpect(jsonPath("$.tipoNombre").value(DEFAULT_TIPO_NOMBRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoPrograma() throws Exception {
        // Get the tipoPrograma
        restTipoProgramaMockMvc.perform(get("/api/tipo-programas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        int databaseSizeBeforeUpdate = tipoProgramaRepository.findAll().size();

        // Update the tipoPrograma
        TipoPrograma updatedTipoPrograma = tipoProgramaRepository.findById(tipoPrograma.getId()).get();
        // Disconnect from session so that the updates on updatedTipoPrograma are not directly saved in db
        em.detach(updatedTipoPrograma);
        updatedTipoPrograma
            .tipoNombre(UPDATED_TIPO_NOMBRE);

        restTipoProgramaMockMvc.perform(put("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTipoPrograma)))
            .andExpect(status().isOk());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeUpdate);
        TipoPrograma testTipoPrograma = tipoProgramaList.get(tipoProgramaList.size() - 1);
        assertThat(testTipoPrograma.getTipoNombre()).isEqualTo(UPDATED_TIPO_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoPrograma() throws Exception {
        int databaseSizeBeforeUpdate = tipoProgramaRepository.findAll().size();

        // Create the TipoPrograma

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoProgramaMockMvc.perform(put("/api/tipo-programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoPrograma)))
            .andExpect(status().isBadRequest());

        // Validate the TipoPrograma in the database
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoPrograma() throws Exception {
        // Initialize the database
        tipoProgramaRepository.saveAndFlush(tipoPrograma);

        int databaseSizeBeforeDelete = tipoProgramaRepository.findAll().size();

        // Delete the tipoPrograma
        restTipoProgramaMockMvc.perform(delete("/api/tipo-programas/{id}", tipoPrograma.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoPrograma> tipoProgramaList = tipoProgramaRepository.findAll();
        assertThat(tipoProgramaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoPrograma.class);
        TipoPrograma tipoPrograma1 = new TipoPrograma();
        tipoPrograma1.setId(1L);
        TipoPrograma tipoPrograma2 = new TipoPrograma();
        tipoPrograma2.setId(tipoPrograma1.getId());
        assertThat(tipoPrograma1).isEqualTo(tipoPrograma2);
        tipoPrograma2.setId(2L);
        assertThat(tipoPrograma1).isNotEqualTo(tipoPrograma2);
        tipoPrograma1.setId(null);
        assertThat(tipoPrograma1).isNotEqualTo(tipoPrograma2);
    }
}
