import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from './ciudad.service';
import { IPais } from 'app/shared/model/pais.model';
import { PaisService } from 'app/entities/pais';
import { IInstitucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from 'app/entities/institucion';

@Component({
    selector: 'jhi-ciudad-update',
    templateUrl: './ciudad-update.component.html'
})
export class CiudadUpdateComponent implements OnInit {
    ciudad: ICiudad;
    isSaving: boolean;

    pais: IPais[];

    institucions: IInstitucion[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected ciudadService: CiudadService,
        protected paisService: PaisService,
        protected institucionService: InstitucionService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ciudad }) => {
            this.ciudad = ciudad;
        });
        this.paisService
            .query({ filter: 'ciudad-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IPais[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPais[]>) => response.body)
            )
            .subscribe(
                (res: IPais[]) => {
                    if (!this.ciudad.pais || !this.ciudad.pais.id) {
                        this.pais = res;
                    } else {
                        this.paisService
                            .find(this.ciudad.pais.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IPais>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IPais>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IPais) => (this.pais = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.institucionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IInstitucion[]>) => mayBeOk.ok),
                map((response: HttpResponse<IInstitucion[]>) => response.body)
            )
            .subscribe((res: IInstitucion[]) => (this.institucions = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ciudad.id !== undefined) {
            this.subscribeToSaveResponse(this.ciudadService.update(this.ciudad));
        } else {
            this.subscribeToSaveResponse(this.ciudadService.create(this.ciudad));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICiudad>>) {
        result.subscribe((res: HttpResponse<ICiudad>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPaisById(index: number, item: IPais) {
        return item.id;
    }

    trackInstitucionById(index: number, item: IInstitucion) {
        return item.id;
    }
}
