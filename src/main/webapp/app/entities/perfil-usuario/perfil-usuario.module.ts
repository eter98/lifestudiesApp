import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesAppSharedModule } from 'app/shared';
import {
    PerfilUsuarioComponent,
    PerfilUsuarioDetailComponent,
    PerfilUsuarioUpdateComponent,
    PerfilUsuarioDeletePopupComponent,
    PerfilUsuarioDeleteDialogComponent,
    perfilUsuarioRoute,
    perfilUsuarioPopupRoute
} from './';

const ENTITY_STATES = [...perfilUsuarioRoute, ...perfilUsuarioPopupRoute];

@NgModule({
    imports: [LifestudiesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PerfilUsuarioComponent,
        PerfilUsuarioDetailComponent,
        PerfilUsuarioUpdateComponent,
        PerfilUsuarioDeleteDialogComponent,
        PerfilUsuarioDeletePopupComponent
    ],
    entryComponents: [
        PerfilUsuarioComponent,
        PerfilUsuarioUpdateComponent,
        PerfilUsuarioDeleteDialogComponent,
        PerfilUsuarioDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesAppPerfilUsuarioModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
