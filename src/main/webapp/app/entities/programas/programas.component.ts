import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IProgramas } from 'app/shared/model/programas.model';
import { AccountService } from 'app/core';
import { ProgramasService } from './programas.service';

@Component({
    selector: 'jhi-programas',
    templateUrl: './programas.component.html'
})
export class ProgramasComponent implements OnInit, OnDestroy {
    programas: IProgramas[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected programasService: ProgramasService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.programasService
            .query()
            .pipe(
                filter((res: HttpResponse<IProgramas[]>) => res.ok),
                map((res: HttpResponse<IProgramas[]>) => res.body)
            )
            .subscribe(
                (res: IProgramas[]) => {
                    this.programas = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInProgramas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IProgramas) {
        return item.id;
    }

    registerChangeInProgramas() {
        this.eventSubscriber = this.eventManager.subscribe('programasListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
