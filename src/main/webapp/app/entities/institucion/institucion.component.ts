import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IInstitucion } from 'app/shared/model/institucion.model';
import { AccountService } from 'app/core';
import { InstitucionService } from './institucion.service';

@Component({
    selector: 'jhi-institucion',
    templateUrl: './institucion.component.html'
})
export class InstitucionComponent implements OnInit, OnDestroy {
    institucions: IInstitucion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected institucionService: InstitucionService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.institucionService
            .query()
            .pipe(
                filter((res: HttpResponse<IInstitucion[]>) => res.ok),
                map((res: HttpResponse<IInstitucion[]>) => res.body)
            )
            .subscribe(
                (res: IInstitucion[]) => {
                    this.institucions = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInInstitucions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IInstitucion) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInInstitucions() {
        this.eventSubscriber = this.eventManager.subscribe('institucionListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
