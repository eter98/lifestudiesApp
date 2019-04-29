import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICotizacion } from 'app/shared/model/cotizacion.model';
import { AccountService } from 'app/core';
import { CotizacionService } from './cotizacion.service';

@Component({
    selector: 'jhi-cotizacion',
    templateUrl: './cotizacion.component.html'
})
export class CotizacionComponent implements OnInit, OnDestroy {
    cotizacions: ICotizacion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected cotizacionService: CotizacionService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.cotizacionService
            .query()
            .pipe(
                filter((res: HttpResponse<ICotizacion[]>) => res.ok),
                map((res: HttpResponse<ICotizacion[]>) => res.body)
            )
            .subscribe(
                (res: ICotizacion[]) => {
                    this.cotizacions = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCotizacions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICotizacion) {
        return item.id;
    }

    registerChangeInCotizacions() {
        this.eventSubscriber = this.eventManager.subscribe('cotizacionListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
