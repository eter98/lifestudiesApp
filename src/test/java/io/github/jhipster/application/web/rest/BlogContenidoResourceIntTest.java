package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.BlogContenido;
import io.github.jhipster.application.repository.BlogContenidoRepository;
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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Nacionalidadd;
import io.github.jhipster.application.domain.enumeration.Destinod;
import io.github.jhipster.application.domain.enumeration.NivelAcademicod;
/**
 * Test class for the BlogContenidoResource REST controller.
 *
 * @see BlogContenidoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class BlogContenidoResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_CORREO = "AAAAAAAAAA";
    private static final String UPDATED_CORREO = "BBBBBBBBBB";

    private static final Nacionalidadd DEFAULT_NACIONALIDAD = Nacionalidadd.Argentina;
    private static final Nacionalidadd UPDATED_NACIONALIDAD = Nacionalidadd.Bolivia;

    private static final Destinod DEFAULT_PAIS_ESTUDIO = Destinod.ALEMANIA;
    private static final Destinod UPDATED_PAIS_ESTUDIO = Destinod.AUSTRALIA;

    private static final Integer DEFAULT_CALIFICACION_PAIS = 1;
    private static final Integer UPDATED_CALIFICACION_PAIS = 2;

    private static final String DEFAULT_CIUDAD_VIVE = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD_VIVE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICACION_CIUDAD = 1;
    private static final Integer UPDATED_CALIFICACION_CIUDAD = 2;

    private static final NivelAcademicod DEFAULT_PROGRAMA_REALIZADO = NivelAcademicod.PRIMARIA;
    private static final NivelAcademicod UPDATED_PROGRAMA_REALIZADO = NivelAcademicod.SECUNDARIA;

    private static final String DEFAULT_INSTITUCION_ESTUDIO = "AAAAAAAAAA";
    private static final String UPDATED_INSTITUCION_ESTUDIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICACION_INSTITUCION = 1;
    private static final Integer UPDATED_CALIFICACION_INSTITUCION = 2;

    private static final Boolean DEFAULT_AGENCIA_ESTUDIOS = false;
    private static final Boolean UPDATED_AGENCIA_ESTUDIOS = true;

    private static final String DEFAULT_NOMBRE_AGENCIA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_AGENCIA = "BBBBBBBBBB";

    private static final Integer DEFAULT_CALIFICACION_AGENCIA = 1;
    private static final Integer UPDATED_CALIFICACION_AGENCIA = 2;

    private static final Integer DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO = 1;
    private static final Integer UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO = 2;

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENIDO = "AAAAAAAAAA";
    private static final String UPDATED_CONTENIDO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    @Autowired
    private BlogContenidoRepository blogContenidoRepository;

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

    private MockMvc restBlogContenidoMockMvc;

    private BlogContenido blogContenido;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlogContenidoResource blogContenidoResource = new BlogContenidoResource(blogContenidoRepository);
        this.restBlogContenidoMockMvc = MockMvcBuilders.standaloneSetup(blogContenidoResource)
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
    public static BlogContenido createEntity(EntityManager em) {
        BlogContenido blogContenido = new BlogContenido()
            .nombre(DEFAULT_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .correo(DEFAULT_CORREO)
            .nacionalidad(DEFAULT_NACIONALIDAD)
            .paisEstudio(DEFAULT_PAIS_ESTUDIO)
            .calificacionPais(DEFAULT_CALIFICACION_PAIS)
            .ciudadVive(DEFAULT_CIUDAD_VIVE)
            .calificacionCiudad(DEFAULT_CALIFICACION_CIUDAD)
            .programaRealizado(DEFAULT_PROGRAMA_REALIZADO)
            .institucionEstudio(DEFAULT_INSTITUCION_ESTUDIO)
            .calificacionInstitucion(DEFAULT_CALIFICACION_INSTITUCION)
            .agenciaEstudios(DEFAULT_AGENCIA_ESTUDIOS)
            .nombreAgencia(DEFAULT_NOMBRE_AGENCIA)
            .calificacionAgencia(DEFAULT_CALIFICACION_AGENCIA)
            .calificacionExperienciaEstudio(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO)
            .titulo(DEFAULT_TITULO)
            .contenido(DEFAULT_CONTENIDO)
            .imagen(DEFAULT_IMAGEN)
            .imagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE);
        return blogContenido;
    }

    @Before
    public void initTest() {
        blogContenido = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlogContenido() throws Exception {
        int databaseSizeBeforeCreate = blogContenidoRepository.findAll().size();

        // Create the BlogContenido
        restBlogContenidoMockMvc.perform(post("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogContenido)))
            .andExpect(status().isCreated());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeCreate + 1);
        BlogContenido testBlogContenido = blogContenidoList.get(blogContenidoList.size() - 1);
        assertThat(testBlogContenido.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testBlogContenido.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testBlogContenido.getCorreo()).isEqualTo(DEFAULT_CORREO);
        assertThat(testBlogContenido.getNacionalidad()).isEqualTo(DEFAULT_NACIONALIDAD);
        assertThat(testBlogContenido.getPaisEstudio()).isEqualTo(DEFAULT_PAIS_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionPais()).isEqualTo(DEFAULT_CALIFICACION_PAIS);
        assertThat(testBlogContenido.getCiudadVive()).isEqualTo(DEFAULT_CIUDAD_VIVE);
        assertThat(testBlogContenido.getCalificacionCiudad()).isEqualTo(DEFAULT_CALIFICACION_CIUDAD);
        assertThat(testBlogContenido.getProgramaRealizado()).isEqualTo(DEFAULT_PROGRAMA_REALIZADO);
        assertThat(testBlogContenido.getInstitucionEstudio()).isEqualTo(DEFAULT_INSTITUCION_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionInstitucion()).isEqualTo(DEFAULT_CALIFICACION_INSTITUCION);
        assertThat(testBlogContenido.isAgenciaEstudios()).isEqualTo(DEFAULT_AGENCIA_ESTUDIOS);
        assertThat(testBlogContenido.getNombreAgencia()).isEqualTo(DEFAULT_NOMBRE_AGENCIA);
        assertThat(testBlogContenido.getCalificacionAgencia()).isEqualTo(DEFAULT_CALIFICACION_AGENCIA);
        assertThat(testBlogContenido.getCalificacionExperienciaEstudio()).isEqualTo(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO);
        assertThat(testBlogContenido.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testBlogContenido.getContenido()).isEqualTo(DEFAULT_CONTENIDO);
        assertThat(testBlogContenido.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testBlogContenido.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createBlogContenidoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blogContenidoRepository.findAll().size();

        // Create the BlogContenido with an existing ID
        blogContenido.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlogContenidoMockMvc.perform(post("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogContenido)))
            .andExpect(status().isBadRequest());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBlogContenidos() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get all the blogContenidoList
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blogContenido.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO.toString())))
            .andExpect(jsonPath("$.[*].correo").value(hasItem(DEFAULT_CORREO.toString())))
            .andExpect(jsonPath("$.[*].nacionalidad").value(hasItem(DEFAULT_NACIONALIDAD.toString())))
            .andExpect(jsonPath("$.[*].paisEstudio").value(hasItem(DEFAULT_PAIS_ESTUDIO.toString())))
            .andExpect(jsonPath("$.[*].calificacionPais").value(hasItem(DEFAULT_CALIFICACION_PAIS)))
            .andExpect(jsonPath("$.[*].ciudadVive").value(hasItem(DEFAULT_CIUDAD_VIVE.toString())))
            .andExpect(jsonPath("$.[*].calificacionCiudad").value(hasItem(DEFAULT_CALIFICACION_CIUDAD)))
            .andExpect(jsonPath("$.[*].programaRealizado").value(hasItem(DEFAULT_PROGRAMA_REALIZADO.toString())))
            .andExpect(jsonPath("$.[*].institucionEstudio").value(hasItem(DEFAULT_INSTITUCION_ESTUDIO.toString())))
            .andExpect(jsonPath("$.[*].calificacionInstitucion").value(hasItem(DEFAULT_CALIFICACION_INSTITUCION)))
            .andExpect(jsonPath("$.[*].agenciaEstudios").value(hasItem(DEFAULT_AGENCIA_ESTUDIOS.booleanValue())))
            .andExpect(jsonPath("$.[*].nombreAgencia").value(hasItem(DEFAULT_NOMBRE_AGENCIA.toString())))
            .andExpect(jsonPath("$.[*].calificacionAgencia").value(hasItem(DEFAULT_CALIFICACION_AGENCIA)))
            .andExpect(jsonPath("$.[*].calificacionExperienciaEstudio").value(hasItem(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO)))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO.toString())))
            .andExpect(jsonPath("$.[*].contenido").value(hasItem(DEFAULT_CONTENIDO.toString())))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))));
    }
    
    @Test
    @Transactional
    public void getBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        // Get the blogContenido
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos/{id}", blogContenido.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blogContenido.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO.toString()))
            .andExpect(jsonPath("$.correo").value(DEFAULT_CORREO.toString()))
            .andExpect(jsonPath("$.nacionalidad").value(DEFAULT_NACIONALIDAD.toString()))
            .andExpect(jsonPath("$.paisEstudio").value(DEFAULT_PAIS_ESTUDIO.toString()))
            .andExpect(jsonPath("$.calificacionPais").value(DEFAULT_CALIFICACION_PAIS))
            .andExpect(jsonPath("$.ciudadVive").value(DEFAULT_CIUDAD_VIVE.toString()))
            .andExpect(jsonPath("$.calificacionCiudad").value(DEFAULT_CALIFICACION_CIUDAD))
            .andExpect(jsonPath("$.programaRealizado").value(DEFAULT_PROGRAMA_REALIZADO.toString()))
            .andExpect(jsonPath("$.institucionEstudio").value(DEFAULT_INSTITUCION_ESTUDIO.toString()))
            .andExpect(jsonPath("$.calificacionInstitucion").value(DEFAULT_CALIFICACION_INSTITUCION))
            .andExpect(jsonPath("$.agenciaEstudios").value(DEFAULT_AGENCIA_ESTUDIOS.booleanValue()))
            .andExpect(jsonPath("$.nombreAgencia").value(DEFAULT_NOMBRE_AGENCIA.toString()))
            .andExpect(jsonPath("$.calificacionAgencia").value(DEFAULT_CALIFICACION_AGENCIA))
            .andExpect(jsonPath("$.calificacionExperienciaEstudio").value(DEFAULT_CALIFICACION_EXPERIENCIA_ESTUDIO))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO.toString()))
            .andExpect(jsonPath("$.contenido").value(DEFAULT_CONTENIDO.toString()))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)));
    }

    @Test
    @Transactional
    public void getNonExistingBlogContenido() throws Exception {
        // Get the blogContenido
        restBlogContenidoMockMvc.perform(get("/api/blog-contenidos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        int databaseSizeBeforeUpdate = blogContenidoRepository.findAll().size();

        // Update the blogContenido
        BlogContenido updatedBlogContenido = blogContenidoRepository.findById(blogContenido.getId()).get();
        // Disconnect from session so that the updates on updatedBlogContenido are not directly saved in db
        em.detach(updatedBlogContenido);
        updatedBlogContenido
            .nombre(UPDATED_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .correo(UPDATED_CORREO)
            .nacionalidad(UPDATED_NACIONALIDAD)
            .paisEstudio(UPDATED_PAIS_ESTUDIO)
            .calificacionPais(UPDATED_CALIFICACION_PAIS)
            .ciudadVive(UPDATED_CIUDAD_VIVE)
            .calificacionCiudad(UPDATED_CALIFICACION_CIUDAD)
            .programaRealizado(UPDATED_PROGRAMA_REALIZADO)
            .institucionEstudio(UPDATED_INSTITUCION_ESTUDIO)
            .calificacionInstitucion(UPDATED_CALIFICACION_INSTITUCION)
            .agenciaEstudios(UPDATED_AGENCIA_ESTUDIOS)
            .nombreAgencia(UPDATED_NOMBRE_AGENCIA)
            .calificacionAgencia(UPDATED_CALIFICACION_AGENCIA)
            .calificacionExperienciaEstudio(UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO)
            .titulo(UPDATED_TITULO)
            .contenido(UPDATED_CONTENIDO)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE);

        restBlogContenidoMockMvc.perform(put("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlogContenido)))
            .andExpect(status().isOk());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeUpdate);
        BlogContenido testBlogContenido = blogContenidoList.get(blogContenidoList.size() - 1);
        assertThat(testBlogContenido.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testBlogContenido.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testBlogContenido.getCorreo()).isEqualTo(UPDATED_CORREO);
        assertThat(testBlogContenido.getNacionalidad()).isEqualTo(UPDATED_NACIONALIDAD);
        assertThat(testBlogContenido.getPaisEstudio()).isEqualTo(UPDATED_PAIS_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionPais()).isEqualTo(UPDATED_CALIFICACION_PAIS);
        assertThat(testBlogContenido.getCiudadVive()).isEqualTo(UPDATED_CIUDAD_VIVE);
        assertThat(testBlogContenido.getCalificacionCiudad()).isEqualTo(UPDATED_CALIFICACION_CIUDAD);
        assertThat(testBlogContenido.getProgramaRealizado()).isEqualTo(UPDATED_PROGRAMA_REALIZADO);
        assertThat(testBlogContenido.getInstitucionEstudio()).isEqualTo(UPDATED_INSTITUCION_ESTUDIO);
        assertThat(testBlogContenido.getCalificacionInstitucion()).isEqualTo(UPDATED_CALIFICACION_INSTITUCION);
        assertThat(testBlogContenido.isAgenciaEstudios()).isEqualTo(UPDATED_AGENCIA_ESTUDIOS);
        assertThat(testBlogContenido.getNombreAgencia()).isEqualTo(UPDATED_NOMBRE_AGENCIA);
        assertThat(testBlogContenido.getCalificacionAgencia()).isEqualTo(UPDATED_CALIFICACION_AGENCIA);
        assertThat(testBlogContenido.getCalificacionExperienciaEstudio()).isEqualTo(UPDATED_CALIFICACION_EXPERIENCIA_ESTUDIO);
        assertThat(testBlogContenido.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testBlogContenido.getContenido()).isEqualTo(UPDATED_CONTENIDO);
        assertThat(testBlogContenido.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testBlogContenido.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingBlogContenido() throws Exception {
        int databaseSizeBeforeUpdate = blogContenidoRepository.findAll().size();

        // Create the BlogContenido

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBlogContenidoMockMvc.perform(put("/api/blog-contenidos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blogContenido)))
            .andExpect(status().isBadRequest());

        // Validate the BlogContenido in the database
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBlogContenido() throws Exception {
        // Initialize the database
        blogContenidoRepository.saveAndFlush(blogContenido);

        int databaseSizeBeforeDelete = blogContenidoRepository.findAll().size();

        // Delete the blogContenido
        restBlogContenidoMockMvc.perform(delete("/api/blog-contenidos/{id}", blogContenido.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BlogContenido> blogContenidoList = blogContenidoRepository.findAll();
        assertThat(blogContenidoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BlogContenido.class);
        BlogContenido blogContenido1 = new BlogContenido();
        blogContenido1.setId(1L);
        BlogContenido blogContenido2 = new BlogContenido();
        blogContenido2.setId(blogContenido1.getId());
        assertThat(blogContenido1).isEqualTo(blogContenido2);
        blogContenido2.setId(2L);
        assertThat(blogContenido1).isNotEqualTo(blogContenido2);
        blogContenido1.setId(null);
        assertThat(blogContenido1).isNotEqualTo(blogContenido2);
    }
}
