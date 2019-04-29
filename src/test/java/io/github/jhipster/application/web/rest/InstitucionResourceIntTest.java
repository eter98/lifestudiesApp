package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.LifestudiesApp;

import io.github.jhipster.application.domain.Institucion;
import io.github.jhipster.application.repository.InstitucionRepository;
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

/**
 * Test class for the InstitucionResource REST controller.
 *
 * @see InstitucionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LifestudiesApp.class)
public class InstitucionResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TIPO = 1;
    private static final Integer UPDATED_TIPO = 2;

    private static final String DEFAULT_WEB_SITE = "AAAAAAAAAA";
    private static final String UPDATED_WEB_SITE = "BBBBBBBBBB";

    private static final String DEFAULT_PREFIX_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_PREFIX_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO_RESPONSABLE = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO_RESPONSABLE = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_AREA_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AREA_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIL = "AAAAAAAAAA";
    private static final String UPDATED_MOVIL = "BBBBBBBBBB";

    private static final String DEFAULT_SKYPE = "AAAAAAAAAA";
    private static final String UPDATED_SKYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION_2 = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MAIL_CORPORATIVO = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_CORPORATIVO = "BBBBBBBBBB";

    private static final String DEFAULT_MOVIL_CORPORATIVO = "AAAAAAAAAA";
    private static final String UPDATED_MOVIL_CORPORATIVO = "BBBBBBBBBB";

    private static final String DEFAULT_SKYPE_CORPORATIVO = "AAAAAAAAAA";
    private static final String UPDATED_SKYPE_CORPORATIVO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_2 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_2 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_2_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_2_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_3 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_3 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_3_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_3_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_4 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_4 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_4_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_4_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_IMAGEN_5 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGEN_5 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGEN_5_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGEN_5_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_VIDEO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_VIDEO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_VIDEO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_VIDEO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private InstitucionRepository institucionRepository;

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

    private MockMvc restInstitucionMockMvc;

    private Institucion institucion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InstitucionResource institucionResource = new InstitucionResource(institucionRepository);
        this.restInstitucionMockMvc = MockMvcBuilders.standaloneSetup(institucionResource)
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
    public static Institucion createEntity(EntityManager em) {
        Institucion institucion = new Institucion()
            .nombre(DEFAULT_NOMBRE)
            .tipo(DEFAULT_TIPO)
            .webSite(DEFAULT_WEB_SITE)
            .prefixNombre(DEFAULT_PREFIX_NOMBRE)
            .nombreResponsable(DEFAULT_NOMBRE_RESPONSABLE)
            .apellidoResponsable(DEFAULT_APELLIDO_RESPONSABLE)
            .mail(DEFAULT_MAIL)
            .areaCode(DEFAULT_AREA_CODE)
            .telefono(DEFAULT_TELEFONO)
            .movil(DEFAULT_MOVIL)
            .skype(DEFAULT_SKYPE)
            .direccion(DEFAULT_DIRECCION)
            .direccion2(DEFAULT_DIRECCION_2)
            .estado(DEFAULT_ESTADO)
            .zipCode(DEFAULT_ZIP_CODE)
            .mailCorporativo(DEFAULT_MAIL_CORPORATIVO)
            .movilCorporativo(DEFAULT_MOVIL_CORPORATIVO)
            .skypeCorporativo(DEFAULT_SKYPE_CORPORATIVO)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE)
            .imagen(DEFAULT_IMAGEN)
            .imagenContentType(DEFAULT_IMAGEN_CONTENT_TYPE)
            .imagen2(DEFAULT_IMAGEN_2)
            .imagen2ContentType(DEFAULT_IMAGEN_2_CONTENT_TYPE)
            .imagen3(DEFAULT_IMAGEN_3)
            .imagen3ContentType(DEFAULT_IMAGEN_3_CONTENT_TYPE)
            .imagen4(DEFAULT_IMAGEN_4)
            .imagen4ContentType(DEFAULT_IMAGEN_4_CONTENT_TYPE)
            .imagen5(DEFAULT_IMAGEN_5)
            .imagen5ContentType(DEFAULT_IMAGEN_5_CONTENT_TYPE)
            .video(DEFAULT_VIDEO)
            .videoContentType(DEFAULT_VIDEO_CONTENT_TYPE)
            .descripcion(DEFAULT_DESCRIPCION);
        return institucion;
    }

    @Before
    public void initTest() {
        institucion = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstitucion() throws Exception {
        int databaseSizeBeforeCreate = institucionRepository.findAll().size();

        // Create the Institucion
        restInstitucionMockMvc.perform(post("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(institucion)))
            .andExpect(status().isCreated());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeCreate + 1);
        Institucion testInstitucion = institucionList.get(institucionList.size() - 1);
        assertThat(testInstitucion.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testInstitucion.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testInstitucion.getWebSite()).isEqualTo(DEFAULT_WEB_SITE);
        assertThat(testInstitucion.getPrefixNombre()).isEqualTo(DEFAULT_PREFIX_NOMBRE);
        assertThat(testInstitucion.getNombreResponsable()).isEqualTo(DEFAULT_NOMBRE_RESPONSABLE);
        assertThat(testInstitucion.getApellidoResponsable()).isEqualTo(DEFAULT_APELLIDO_RESPONSABLE);
        assertThat(testInstitucion.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testInstitucion.getAreaCode()).isEqualTo(DEFAULT_AREA_CODE);
        assertThat(testInstitucion.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testInstitucion.getMovil()).isEqualTo(DEFAULT_MOVIL);
        assertThat(testInstitucion.getSkype()).isEqualTo(DEFAULT_SKYPE);
        assertThat(testInstitucion.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testInstitucion.getDireccion2()).isEqualTo(DEFAULT_DIRECCION_2);
        assertThat(testInstitucion.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testInstitucion.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testInstitucion.getMailCorporativo()).isEqualTo(DEFAULT_MAIL_CORPORATIVO);
        assertThat(testInstitucion.getMovilCorporativo()).isEqualTo(DEFAULT_MOVIL_CORPORATIVO);
        assertThat(testInstitucion.getSkypeCorporativo()).isEqualTo(DEFAULT_SKYPE_CORPORATIVO);
        assertThat(testInstitucion.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testInstitucion.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testInstitucion.getImagenContentType()).isEqualTo(DEFAULT_IMAGEN_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen2()).isEqualTo(DEFAULT_IMAGEN_2);
        assertThat(testInstitucion.getImagen2ContentType()).isEqualTo(DEFAULT_IMAGEN_2_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen3()).isEqualTo(DEFAULT_IMAGEN_3);
        assertThat(testInstitucion.getImagen3ContentType()).isEqualTo(DEFAULT_IMAGEN_3_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen4()).isEqualTo(DEFAULT_IMAGEN_4);
        assertThat(testInstitucion.getImagen4ContentType()).isEqualTo(DEFAULT_IMAGEN_4_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen5()).isEqualTo(DEFAULT_IMAGEN_5);
        assertThat(testInstitucion.getImagen5ContentType()).isEqualTo(DEFAULT_IMAGEN_5_CONTENT_TYPE);
        assertThat(testInstitucion.getVideo()).isEqualTo(DEFAULT_VIDEO);
        assertThat(testInstitucion.getVideoContentType()).isEqualTo(DEFAULT_VIDEO_CONTENT_TYPE);
        assertThat(testInstitucion.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createInstitucionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = institucionRepository.findAll().size();

        // Create the Institucion with an existing ID
        institucion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstitucionMockMvc.perform(post("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(institucion)))
            .andExpect(status().isBadRequest());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInstitucions() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get all the institucionList
        restInstitucionMockMvc.perform(get("/api/institucions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(institucion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO)))
            .andExpect(jsonPath("$.[*].webSite").value(hasItem(DEFAULT_WEB_SITE.toString())))
            .andExpect(jsonPath("$.[*].prefixNombre").value(hasItem(DEFAULT_PREFIX_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].nombreResponsable").value(hasItem(DEFAULT_NOMBRE_RESPONSABLE.toString())))
            .andExpect(jsonPath("$.[*].apellidoResponsable").value(hasItem(DEFAULT_APELLIDO_RESPONSABLE.toString())))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL.toString())))
            .andExpect(jsonPath("$.[*].areaCode").value(hasItem(DEFAULT_AREA_CODE.toString())))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO.toString())))
            .andExpect(jsonPath("$.[*].movil").value(hasItem(DEFAULT_MOVIL.toString())))
            .andExpect(jsonPath("$.[*].skype").value(hasItem(DEFAULT_SKYPE.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].direccion2").value(hasItem(DEFAULT_DIRECCION_2.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.toString())))
            .andExpect(jsonPath("$.[*].mailCorporativo").value(hasItem(DEFAULT_MAIL_CORPORATIVO.toString())))
            .andExpect(jsonPath("$.[*].movilCorporativo").value(hasItem(DEFAULT_MOVIL_CORPORATIVO.toString())))
            .andExpect(jsonPath("$.[*].skypeCorporativo").value(hasItem(DEFAULT_SKYPE_CORPORATIVO.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].imagenContentType").value(hasItem(DEFAULT_IMAGEN_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN))))
            .andExpect(jsonPath("$.[*].imagen2ContentType").value(hasItem(DEFAULT_IMAGEN_2_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen2").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_2))))
            .andExpect(jsonPath("$.[*].imagen3ContentType").value(hasItem(DEFAULT_IMAGEN_3_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen3").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_3))))
            .andExpect(jsonPath("$.[*].imagen4ContentType").value(hasItem(DEFAULT_IMAGEN_4_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen4").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_4))))
            .andExpect(jsonPath("$.[*].imagen5ContentType").value(hasItem(DEFAULT_IMAGEN_5_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imagen5").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGEN_5))))
            .andExpect(jsonPath("$.[*].videoContentType").value(hasItem(DEFAULT_VIDEO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].video").value(hasItem(Base64Utils.encodeToString(DEFAULT_VIDEO))))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())));
    }
    
    @Test
    @Transactional
    public void getInstitucion() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        // Get the institucion
        restInstitucionMockMvc.perform(get("/api/institucions/{id}", institucion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(institucion.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO))
            .andExpect(jsonPath("$.webSite").value(DEFAULT_WEB_SITE.toString()))
            .andExpect(jsonPath("$.prefixNombre").value(DEFAULT_PREFIX_NOMBRE.toString()))
            .andExpect(jsonPath("$.nombreResponsable").value(DEFAULT_NOMBRE_RESPONSABLE.toString()))
            .andExpect(jsonPath("$.apellidoResponsable").value(DEFAULT_APELLIDO_RESPONSABLE.toString()))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL.toString()))
            .andExpect(jsonPath("$.areaCode").value(DEFAULT_AREA_CODE.toString()))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO.toString()))
            .andExpect(jsonPath("$.movil").value(DEFAULT_MOVIL.toString()))
            .andExpect(jsonPath("$.skype").value(DEFAULT_SKYPE.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.direccion2").value(DEFAULT_DIRECCION_2.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.toString()))
            .andExpect(jsonPath("$.mailCorporativo").value(DEFAULT_MAIL_CORPORATIVO.toString()))
            .andExpect(jsonPath("$.movilCorporativo").value(DEFAULT_MOVIL_CORPORATIVO.toString()))
            .andExpect(jsonPath("$.skypeCorporativo").value(DEFAULT_SKYPE_CORPORATIVO.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.imagenContentType").value(DEFAULT_IMAGEN_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen").value(Base64Utils.encodeToString(DEFAULT_IMAGEN)))
            .andExpect(jsonPath("$.imagen2ContentType").value(DEFAULT_IMAGEN_2_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen2").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_2)))
            .andExpect(jsonPath("$.imagen3ContentType").value(DEFAULT_IMAGEN_3_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen3").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_3)))
            .andExpect(jsonPath("$.imagen4ContentType").value(DEFAULT_IMAGEN_4_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen4").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_4)))
            .andExpect(jsonPath("$.imagen5ContentType").value(DEFAULT_IMAGEN_5_CONTENT_TYPE))
            .andExpect(jsonPath("$.imagen5").value(Base64Utils.encodeToString(DEFAULT_IMAGEN_5)))
            .andExpect(jsonPath("$.videoContentType").value(DEFAULT_VIDEO_CONTENT_TYPE))
            .andExpect(jsonPath("$.video").value(Base64Utils.encodeToString(DEFAULT_VIDEO)))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInstitucion() throws Exception {
        // Get the institucion
        restInstitucionMockMvc.perform(get("/api/institucions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstitucion() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        int databaseSizeBeforeUpdate = institucionRepository.findAll().size();

        // Update the institucion
        Institucion updatedInstitucion = institucionRepository.findById(institucion.getId()).get();
        // Disconnect from session so that the updates on updatedInstitucion are not directly saved in db
        em.detach(updatedInstitucion);
        updatedInstitucion
            .nombre(UPDATED_NOMBRE)
            .tipo(UPDATED_TIPO)
            .webSite(UPDATED_WEB_SITE)
            .prefixNombre(UPDATED_PREFIX_NOMBRE)
            .nombreResponsable(UPDATED_NOMBRE_RESPONSABLE)
            .apellidoResponsable(UPDATED_APELLIDO_RESPONSABLE)
            .mail(UPDATED_MAIL)
            .areaCode(UPDATED_AREA_CODE)
            .telefono(UPDATED_TELEFONO)
            .movil(UPDATED_MOVIL)
            .skype(UPDATED_SKYPE)
            .direccion(UPDATED_DIRECCION)
            .direccion2(UPDATED_DIRECCION_2)
            .estado(UPDATED_ESTADO)
            .zipCode(UPDATED_ZIP_CODE)
            .mailCorporativo(UPDATED_MAIL_CORPORATIVO)
            .movilCorporativo(UPDATED_MOVIL_CORPORATIVO)
            .skypeCorporativo(UPDATED_SKYPE_CORPORATIVO)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .imagen(UPDATED_IMAGEN)
            .imagenContentType(UPDATED_IMAGEN_CONTENT_TYPE)
            .imagen2(UPDATED_IMAGEN_2)
            .imagen2ContentType(UPDATED_IMAGEN_2_CONTENT_TYPE)
            .imagen3(UPDATED_IMAGEN_3)
            .imagen3ContentType(UPDATED_IMAGEN_3_CONTENT_TYPE)
            .imagen4(UPDATED_IMAGEN_4)
            .imagen4ContentType(UPDATED_IMAGEN_4_CONTENT_TYPE)
            .imagen5(UPDATED_IMAGEN_5)
            .imagen5ContentType(UPDATED_IMAGEN_5_CONTENT_TYPE)
            .video(UPDATED_VIDEO)
            .videoContentType(UPDATED_VIDEO_CONTENT_TYPE)
            .descripcion(UPDATED_DESCRIPCION);

        restInstitucionMockMvc.perform(put("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstitucion)))
            .andExpect(status().isOk());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeUpdate);
        Institucion testInstitucion = institucionList.get(institucionList.size() - 1);
        assertThat(testInstitucion.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testInstitucion.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testInstitucion.getWebSite()).isEqualTo(UPDATED_WEB_SITE);
        assertThat(testInstitucion.getPrefixNombre()).isEqualTo(UPDATED_PREFIX_NOMBRE);
        assertThat(testInstitucion.getNombreResponsable()).isEqualTo(UPDATED_NOMBRE_RESPONSABLE);
        assertThat(testInstitucion.getApellidoResponsable()).isEqualTo(UPDATED_APELLIDO_RESPONSABLE);
        assertThat(testInstitucion.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testInstitucion.getAreaCode()).isEqualTo(UPDATED_AREA_CODE);
        assertThat(testInstitucion.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testInstitucion.getMovil()).isEqualTo(UPDATED_MOVIL);
        assertThat(testInstitucion.getSkype()).isEqualTo(UPDATED_SKYPE);
        assertThat(testInstitucion.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testInstitucion.getDireccion2()).isEqualTo(UPDATED_DIRECCION_2);
        assertThat(testInstitucion.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testInstitucion.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testInstitucion.getMailCorporativo()).isEqualTo(UPDATED_MAIL_CORPORATIVO);
        assertThat(testInstitucion.getMovilCorporativo()).isEqualTo(UPDATED_MOVIL_CORPORATIVO);
        assertThat(testInstitucion.getSkypeCorporativo()).isEqualTo(UPDATED_SKYPE_CORPORATIVO);
        assertThat(testInstitucion.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testInstitucion.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testInstitucion.getImagenContentType()).isEqualTo(UPDATED_IMAGEN_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen2()).isEqualTo(UPDATED_IMAGEN_2);
        assertThat(testInstitucion.getImagen2ContentType()).isEqualTo(UPDATED_IMAGEN_2_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen3()).isEqualTo(UPDATED_IMAGEN_3);
        assertThat(testInstitucion.getImagen3ContentType()).isEqualTo(UPDATED_IMAGEN_3_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen4()).isEqualTo(UPDATED_IMAGEN_4);
        assertThat(testInstitucion.getImagen4ContentType()).isEqualTo(UPDATED_IMAGEN_4_CONTENT_TYPE);
        assertThat(testInstitucion.getImagen5()).isEqualTo(UPDATED_IMAGEN_5);
        assertThat(testInstitucion.getImagen5ContentType()).isEqualTo(UPDATED_IMAGEN_5_CONTENT_TYPE);
        assertThat(testInstitucion.getVideo()).isEqualTo(UPDATED_VIDEO);
        assertThat(testInstitucion.getVideoContentType()).isEqualTo(UPDATED_VIDEO_CONTENT_TYPE);
        assertThat(testInstitucion.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingInstitucion() throws Exception {
        int databaseSizeBeforeUpdate = institucionRepository.findAll().size();

        // Create the Institucion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstitucionMockMvc.perform(put("/api/institucions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(institucion)))
            .andExpect(status().isBadRequest());

        // Validate the Institucion in the database
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstitucion() throws Exception {
        // Initialize the database
        institucionRepository.saveAndFlush(institucion);

        int databaseSizeBeforeDelete = institucionRepository.findAll().size();

        // Delete the institucion
        restInstitucionMockMvc.perform(delete("/api/institucions/{id}", institucion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Institucion> institucionList = institucionRepository.findAll();
        assertThat(institucionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Institucion.class);
        Institucion institucion1 = new Institucion();
        institucion1.setId(1L);
        Institucion institucion2 = new Institucion();
        institucion2.setId(institucion1.getId());
        assertThat(institucion1).isEqualTo(institucion2);
        institucion2.setId(2L);
        assertThat(institucion1).isNotEqualTo(institucion2);
        institucion1.setId(null);
        assertThat(institucion1).isNotEqualTo(institucion2);
    }
}
