import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IInstitucion } from 'app/shared/model/institucion.model';
import { InstitucionService } from './institucion.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad';
import { IProgramas } from 'app/shared/model/programas.model';
import { ProgramasService } from 'app/entities/programas';

@Component({
    selector: 'jhi-institucion-update',
    templateUrl: './institucion-update.component.html'
})
export class InstitucionUpdateComponent implements OnInit {
    institucion: IInstitucion;
    isSaving: boolean;

    ciudads: ICiudad[];

    programas: IProgramas[];

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected institucionService: InstitucionService,
        protected ciudadService: CiudadService,
        protected programasService: ProgramasService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ institucion }) => {
            this.institucion = institucion;
        });
        this.ciudadService
            .query({ filter: 'institucion-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<ICiudad[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICiudad[]>) => response.body)
            )
            .subscribe(
                (res: ICiudad[]) => {
                    if (!this.institucion.ciudad || !this.institucion.ciudad.id) {
                        this.ciudads = res;
                    } else {
                        this.ciudadService
                            .find(this.institucion.ciudad.id)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ICiudad>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ICiudad>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ICiudad) => (this.ciudads = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.programasService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IProgramas[]>) => mayBeOk.ok),
                map((response: HttpResponse<IProgramas[]>) => response.body)
            )
            .subscribe((res: IProgramas[]) => (this.programas = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.institucion, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.institucion.id !== undefined) {
            this.subscribeToSaveResponse(this.institucionService.update(this.institucion));
        } else {
            this.subscribeToSaveResponse(this.institucionService.create(this.institucion));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInstitucion>>) {
        result.subscribe((res: HttpResponse<IInstitucion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCiudadById(index: number, item: ICiudad) {
        return item.id;
    }

    trackProgramasById(index: number, item: IProgramas) {
        return item.id;
    }
}
