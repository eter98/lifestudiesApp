import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { AccountService } from 'app/core';
import { PerfilUsuarioService } from './perfil-usuario.service';

@Component({
    selector: 'jhi-perfil-usuario',
    templateUrl: './perfil-usuario.component.html'
})
export class PerfilUsuarioComponent implements OnInit, OnDestroy {
    perfilUsuarios: IPerfilUsuario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected perfilUsuarioService: PerfilUsuarioService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.perfilUsuarioService
            .query()
            .pipe(
                filter((res: HttpResponse<IPerfilUsuario[]>) => res.ok),
                map((res: HttpResponse<IPerfilUsuario[]>) => res.body)
            )
            .subscribe(
                (res: IPerfilUsuario[]) => {
                    this.perfilUsuarios = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPerfilUsuarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPerfilUsuario) {
        return item.id;
    }

    registerChangeInPerfilUsuarios() {
        this.eventSubscriber = this.eventManager.subscribe('perfilUsuarioListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
