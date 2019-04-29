import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITipoPrograma } from 'app/shared/model/tipo-programa.model';
import { AccountService } from 'app/core';
import { TipoProgramaService } from './tipo-programa.service';

@Component({
    selector: 'jhi-tipo-programa',
    templateUrl: './tipo-programa.component.html'
})
export class TipoProgramaComponent implements OnInit, OnDestroy {
    tipoProgramas: ITipoPrograma[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected tipoProgramaService: TipoProgramaService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.tipoProgramaService
            .query()
            .pipe(
                filter((res: HttpResponse<ITipoPrograma[]>) => res.ok),
                map((res: HttpResponse<ITipoPrograma[]>) => res.body)
            )
            .subscribe(
                (res: ITipoPrograma[]) => {
                    this.tipoProgramas = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTipoProgramas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITipoPrograma) {
        return item.id;
    }

    registerChangeInTipoProgramas() {
        this.eventSubscriber = this.eventManager.subscribe('tipoProgramaListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
