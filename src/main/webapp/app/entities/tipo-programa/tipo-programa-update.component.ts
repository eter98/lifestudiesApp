import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';
import { TipoProgramaService } from './tipo-programa.service';
import { IProgramas } from 'app/shared/model/programas.model';
import { ProgramasService } from 'app/entities/programas';

@Component({
    selector: 'jhi-tipo-programa-update',
    templateUrl: './tipo-programa-update.component.html'
})
export class TipoProgramaUpdateComponent implements OnInit {
    tipoPrograma: ITipoPrograma;
    isSaving: boolean;

    tipocodigos: IProgramas[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected tipoProgramaService: TipoProgramaService,
        protected programasService: ProgramasService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tipoPrograma }) => {
            this.tipoPrograma = tipoPrograma;
        });
        this.programasService
            .query({ filter: 'tipoprograma-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IProgramas[]>) => mayBeOk.ok),
                map((response: HttpResponse<IProgramas[]>) => response.body)
            )
            .subscribe(
                (res: IProgramas[]) => {
                    if (!this.tipoPrograma.tipoCodigo || !this.tipoPrograma.tipoCodigo.id) {
                        this.tipocodigos = res;
                    } else {
                        this.programasService
                            .find(this.tipoPrograma.tipoCodigo.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IProgramas>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IProgramas>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IProgramas) => (this.tipocodigos = [subRes].concat(res)),
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
        if (this.tipoPrograma.id !== undefined) {
            this.subscribeToSaveResponse(this.tipoProgramaService.update(this.tipoPrograma));
        } else {
            this.subscribeToSaveResponse(this.tipoProgramaService.create(this.tipoPrograma));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoPrograma>>) {
        result.subscribe((res: HttpResponse<ITipoPrograma>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
