import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesAppSharedModule } from 'app/shared';
import {
    TipoProgramaComponent,
    TipoProgramaDetailComponent,
    TipoProgramaUpdateComponent,
    TipoProgramaDeletePopupComponent,
    TipoProgramaDeleteDialogComponent,
    tipoProgramaRoute,
    tipoProgramaPopupRoute
} from './';

const ENTITY_STATES = [...tipoProgramaRoute, ...tipoProgramaPopupRoute];

@NgModule({
    imports: [LifestudiesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoProgramaComponent,
        TipoProgramaDetailComponent,
        TipoProgramaUpdateComponent,
        TipoProgramaDeleteDialogComponent,
        TipoProgramaDeletePopupComponent
    ],
    entryComponents: [
        TipoProgramaComponent,
        TipoProgramaUpdateComponent,
        TipoProgramaDeleteDialogComponent,
        TipoProgramaDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesAppTipoProgramaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
