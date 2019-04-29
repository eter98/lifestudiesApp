import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ICotizacion } from 'app/shared/model/cotizacion.model';
import { CotizacionService } from './cotizacion.service';
import { IProgramas } from 'app/shared/model/programas.model';
import { ProgramasService } from 'app/entities/programas';

@Component({
    selector: 'jhi-cotizacion-update',
    templateUrl: './cotizacion-update.component.html'
})
export class CotizacionUpdateComponent implements OnInit {
    cotizacion: ICotizacion;
    isSaving: boolean;

    programas: IProgramas[];
    fechaViajeDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cotizacionService: CotizacionService,
        protected programasService: ProgramasService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cotizacion }) => {
            this.cotizacion = cotizacion;
        });
        this.programasService
            .query({ filter: 'cotizacion-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IProgramas[]>) => mayBeOk.ok),
                map((response: HttpResponse<IProgramas[]>) => response.body)
            )
            .subscribe(
                (res: IProgramas[]) => {
                    if (!this.cotizacion.programas || !this.cotizacion.programas.id) {
                        this.programas = res;
                    } else {
                        this.programasService
                            .find(this.cotizacion.programas.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IProgramas>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IProgramas>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IProgramas) => (this.programas = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cotizacion.id !== undefined) {
            this.subscribeToSaveResponse(this.cotizacionService.update(this.cotizacion));
        } else {
            this.subscribeToSaveResponse(this.cotizacionService.create(this.cotizacion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICotizacion>>) {
        result.subscribe((res: HttpResponse<ICotizacion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackProgramasById(index: number, item: IProgramas) {
        return item.id;
    }
}
