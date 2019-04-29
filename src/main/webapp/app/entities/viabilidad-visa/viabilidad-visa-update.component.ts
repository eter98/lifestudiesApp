import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';
import { ViabilidadVisaService } from './viabilidad-visa.service';

@Component({
    selector: 'jhi-viabilidad-visa-update',
    templateUrl: './viabilidad-visa-update.component.html'
})
export class ViabilidadVisaUpdateComponent implements OnInit {
    viabilidadVisa: IViabilidadVisa;
    isSaving: boolean;
    fechaNacimientoDp: any;
    fechaGadruacionDp: any;

    constructor(protected viabilidadVisaService: ViabilidadVisaService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ viabilidadVisa }) => {
            this.viabilidadVisa = viabilidadVisa;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.viabilidadVisa.id !== undefined) {
            this.subscribeToSaveResponse(this.viabilidadVisaService.update(this.viabilidadVisa));
        } else {
            this.subscribeToSaveResponse(this.viabilidadVisaService.create(this.viabilidadVisa));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IViabilidadVisa>>) {
        result.subscribe((res: HttpResponse<IViabilidadVisa>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
