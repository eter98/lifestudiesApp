import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';
import { AccountService } from 'app/core';
import { ViabilidadVisaService } from './viabilidad-visa.service';

@Component({
    selector: 'jhi-viabilidad-visa',
    templateUrl: './viabilidad-visa.component.html'
})
export class ViabilidadVisaComponent implements OnInit, OnDestroy {
    viabilidadVisas: IViabilidadVisa[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected viabilidadVisaService: ViabilidadVisaService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.viabilidadVisaService
            .query()
            .pipe(
                filter((res: HttpResponse<IViabilidadVisa[]>) => res.ok),
                map((res: HttpResponse<IViabilidadVisa[]>) => res.body)
            )
            .subscribe(
                (res: IViabilidadVisa[]) => {
                    this.viabilidadVisas = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInViabilidadVisas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IViabilidadVisa) {
        return item.id;
    }

    registerChangeInViabilidadVisas() {
        this.eventSubscriber = this.eventManager.subscribe('viabilidadVisaListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
