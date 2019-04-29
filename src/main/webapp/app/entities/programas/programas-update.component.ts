import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProgramas } from 'app/shared/model/programas.model';
import { ProgramasService } from './programas.service';
import { IInstitucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from 'app/entities/institucion';
import { ICotizacion } from 'app/shared/model/cotizacion.model';
import { CotizacionService } from 'app/entities/cotizacion';

@Component({
    selector: 'jhi-programas-update',
    templateUrl: './programas-update.component.html'
})
export class ProgramasUpdateComponent implements OnInit {
    programas: IProgramas;
    isSaving: boolean;

    institucions: IInstitucion[];

    cotizacions: ICotizacion[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected programasService: ProgramasService,
        protected institucionService: InstitucionService,
        protected cotizacionService: CotizacionService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ programas }) => {
            this.programas = programas;
        });
        this.institucionService
            .query({ filter: 'programas-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IInstitucion[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInstitucion[]>) => response.body)
            )
            .subscribe(
                (res: IInstitucion[]) => {
                    if (!this.programas.institucion || !this.programas.institucion.id) {
                        this.institucions = res;
                    } else {
                        this.institucionService
                            .find(this.programas.institucion.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IInstitucion>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IInstitucion>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IInstitucion) => (this.institucions = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.cotizacionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICotizacion[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICotizacion[]>) => response.body)
            )
            .subscribe((res: ICotizacion[]) => (this.cotizacions = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.programas.id !== undefined) {
            this.subscribeToSaveResponse(this.programasService.update(this.programas));
        } else {
            this.subscribeToSaveResponse(this.programasService.create(this.programas));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProgramas>>) {
        result.subscribe((res: HttpResponse<IProgramas>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackInstitucionById(index: number, item: IInstitucion) {
        return item.id;
    }

    trackCotizacionById(index: number, item: ICotizacion) {
        return item.id;
    }
}
