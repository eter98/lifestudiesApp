import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesAppSharedModule } from 'app/shared';
import {
    InstitucionComponent,
    InstitucionDetailComponent,
    InstitucionUpdateComponent,
    InstitucionDeletePopupComponent,
    InstitucionDeleteDialogComponent,
    institucionRoute,
    institucionPopupRoute
} from './';

const ENTITY_STATES = [...institucionRoute, ...institucionPopupRoute];

@NgModule({
    imports: [LifestudiesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        InstitucionComponent,
        InstitucionDetailComponent,
        InstitucionUpdateComponent,
        InstitucionDeleteDialogComponent,
        InstitucionDeletePopupComponent
    ],
    entryComponents: [InstitucionComponent, InstitucionUpdateComponent, InstitucionDeleteDialogComponent, InstitucionDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesAppInstitucionModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
