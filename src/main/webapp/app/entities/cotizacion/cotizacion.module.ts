import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { LifestudiesAppSharedModule } from 'app/shared';
import {
    CotizacionComponent,
    CotizacionDetailComponent,
    CotizacionUpdateComponent,
    CotizacionDeletePopupComponent,
    CotizacionDeleteDialogComponent,
    cotizacionRoute,
    cotizacionPopupRoute
} from './';

const ENTITY_STATES = [...cotizacionRoute, ...cotizacionPopupRoute];

@NgModule({
    imports: [LifestudiesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CotizacionComponent,
        CotizacionDetailComponent,
        CotizacionUpdateComponent,
        CotizacionDeleteDialogComponent,
        CotizacionDeletePopupComponent
    ],
    entryComponents: [CotizacionComponent, CotizacionUpdateComponent, CotizacionDeleteDialogComponent, CotizacionDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LifestudiesAppCotizacionModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
