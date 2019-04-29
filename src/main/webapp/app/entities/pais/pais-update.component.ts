import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPais } from 'app/shared/model/pais.model';
import { PaisService } from './pais.service';
import { ICiudad } from 'app/shared/model/ciudad.model';
import { CiudadService } from 'app/entities/ciudad';

@Component({
    selector: 'jhi-pais-update',
    templateUrl: './pais-update.component.html'
})
export class PaisUpdateComponent implements OnInit {
    pais: IPais;
    isSaving: boolean;

    ciudads: ICiudad[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected paisService: PaisService,
        protected ciudadService: CiudadService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pais }) => {
            this.pais = pais;
        });
        this.ciudadService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICiudad[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICiudad[]>) => response.body)
            )
            .subscribe((res: ICiudad[]) => (this.ciudads = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.pais.id !== undefined) {
            this.subscribeToSaveResponse(this.paisService.update(this.pais));
        } else {
            this.subscribeToSaveResponse(this.paisService.create(this.pais));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPais>>) {
        result.subscribe((res: HttpResponse<IPais>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
