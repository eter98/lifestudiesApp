package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.Programas;
import io.github.jhipster.application.repository.ProgramasRepository;
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

import io.github.jhipster.application.domain.enumeration.Duraciond;
import io.github.jhipster.application.domain.enumeration.TipoProgramad;
/**
 * Test class for the ProgramasResource REST controller.
 *
 * @see ProgramasResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class ProgramasResourceIntTest {

    private static final String DEFAULT_PROGRAMA_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMA_NOMBRE = "BBBBBBBBBB";

    private static final Duraciond DEFAULT_DURACION = Duraciond.UN_MES;
    private static final Duraciond UPDATED_DURACION = Duraciond.DOS_MESES;

    private static final TipoProgramad DEFAULT_TIPO = TipoProgramad.Programas_Idiomas;
    private static final TipoProgramad UPDATED_TIPO = TipoProgramad.Curso_Verano_idiomas;

    @Autowired
    private ProgramasRepository programasRepository;

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

    private MockMvc restProgramasMockMvc;

    private Programas programas;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgramasResource programasResource = new ProgramasResource(programasRepository);
        this.restProgramasMockMvc = MockMvcBuilders.standaloneSetup(programasResource)
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
    public static Programas createEntity(EntityManager em) {
        Programas programas = new Programas()
            .programaNombre(DEFAULT_PROGRAMA_NOMBRE)
            .duracion(DEFAULT_DURACION)
            .tipo(DEFAULT_TIPO);
        return programas;
    }

    @Before
    public void initTest() {
        programas = createEntity(em);
    }

    @Test
    @Transactional
    public void createProgramas() throws Exception {
        int databaseSizeBeforeCreate = programasRepository.findAll().size();

        // Create the Programas
        restProgramasMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programas)))
            .andExpect(status().isCreated());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeCreate + 1);
        Programas testProgramas = programasList.get(programasList.size() - 1);
        assertThat(testProgramas.getProgramaNombre()).isEqualTo(DEFAULT_PROGRAMA_NOMBRE);
        assertThat(testProgramas.getDuracion()).isEqualTo(DEFAULT_DURACION);
        assertThat(testProgramas.getTipo()).isEqualTo(DEFAULT_TIPO);
    }

    @Test
    @Transactional
    public void createProgramasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programasRepository.findAll().size();

        // Create the Programas with an existing ID
        programas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramasMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programas)))
            .andExpect(status().isBadRequest());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProgramas() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get all the programasList
        restProgramasMockMvc.perform(get("/api/programas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programas.getId().intValue())))
            .andExpect(jsonPath("$.[*].programaNombre").value(hasItem(DEFAULT_PROGRAMA_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].duracion").value(hasItem(DEFAULT_DURACION.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())));
    }
    
    @Test
    @Transactional
    public void getProgramas() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        // Get the programas
        restProgramasMockMvc.perform(get("/api/programas/{id}", programas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(programas.getId().intValue()))
            .andExpect(jsonPath("$.programaNombre").value(DEFAULT_PROGRAMA_NOMBRE.toString()))
            .andExpect(jsonPath("$.duracion").value(DEFAULT_DURACION.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProgramas() throws Exception {
        // Get the programas
        restProgramasMockMvc.perform(get("/api/programas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProgramas() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        int databaseSizeBeforeUpdate = programasRepository.findAll().size();

        // Update the programas
        Programas updatedProgramas = programasRepository.findById(programas.getId()).get();
        // Disconnect from session so that the updates on updatedProgramas are not directly saved in db
        em.detach(updatedProgramas);
        updatedProgramas
            .programaNombre(UPDATED_PROGRAMA_NOMBRE)
            .duracion(UPDATED_DURACION)
            .tipo(UPDATED_TIPO);

        restProgramasMockMvc.perform(put("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProgramas)))
            .andExpect(status().isOk());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeUpdate);
        Programas testProgramas = programasList.get(programasList.size() - 1);
        assertThat(testProgramas.getProgramaNombre()).isEqualTo(UPDATED_PROGRAMA_NOMBRE);
        assertThat(testProgramas.getDuracion()).isEqualTo(UPDATED_DURACION);
        assertThat(testProgramas.getTipo()).isEqualTo(UPDATED_TIPO);
    }

    @Test
    @Transactional
    public void updateNonExistingProgramas() throws Exception {
        int databaseSizeBeforeUpdate = programasRepository.findAll().size();

        // Create the Programas

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgramasMockMvc.perform(put("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programas)))
            .andExpect(status().isBadRequest());

        // Validate the Programas in the database
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProgramas() throws Exception {
        // Initialize the database
        programasRepository.saveAndFlush(programas);

        int databaseSizeBeforeDelete = programasRepository.findAll().size();

        // Delete the programas
        restProgramasMockMvc.perform(delete("/api/programas/{id}", programas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Programas> programasList = programasRepository.findAll();
        assertThat(programasList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Programas.class);
        Programas programas1 = new Programas();
        programas1.setId(1L);
        Programas programas2 = new Programas();
        programas2.setId(programas1.getId());
        assertThat(programas1).isEqualTo(programas2);
        programas2.setId(2L);
        assertThat(programas1).isNotEqualTo(programas2);
        programas1.setId(null);
        assertThat(programas1).isNotEqualTo(programas2);
    }
}
