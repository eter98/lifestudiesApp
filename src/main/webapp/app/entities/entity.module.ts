import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'institucion',
                loadChildren: './institucion/institucion.module#LifestudiesAppInstitucionModule'
            },
            {
                path: 'cotizacion',
                loadChildren: './cotizacion/cotizacion.module#LifestudiesAppCotizacionModule'
            },
            {
                path: 'perfil-usuario',
                loadChildren: './perfil-usuario/perfil-usuario.module#LifestudiesAppPerfilUsuarioModule'
            },
            {
                path: 'pais',
                loadChildren: './pais/pais.module#LifestudiesAppPaisModule'
            },
            {
                path: 'ciudad',
                loadChildren: './ciudad/ciudad.module#LifestudiesAppCiudadModule'
            },
            {
                path: 'tipo-programa',
                loadChildren: './tipo-programa/tipo-programa.module#LifestudiesAppTipoProgramaModule'
            },
            {
                path: 'programas',
                loadChildren: './programas/programas.module#LifestudiesAppProgramasModule'
            },
            {
                path: 'viabilidad-visa',
                loadChildren: './viabilidad-visa/viabilidad-visa.module#LifestudiesAppViabilidadVisaModule'
            },
            {
                path: 'blog-contenido',
                loadChildren: './blog-contenido/blog-contenido.module#LifestudiesAppBlogContenidoModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesAppEntityModule {}
