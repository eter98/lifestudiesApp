import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IBlogContenido } from 'app/shared/model/blog-contenido.model';
import { AccountService } from 'app/core';
import { BlogContenidoService } from './blog-contenido.service';

@Component({
    selector: 'jhi-blog-contenido',
    templateUrl: './blog-contenido.component.html'
})
export class BlogContenidoComponent implements OnInit, OnDestroy {
    blogContenidos: IBlogContenido[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected blogContenidoService: BlogContenidoService,
        protected jhiAlertService: JhiAlertService,
        protected dataUtils: JhiDataUtils,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.blogContenidoService
            .query()
            .pipe(
                filter((res: HttpResponse<IBlogContenido[]>) => res.ok),
                map((res: HttpResponse<IBlogContenido[]>) => res.body)
            )
            .subscribe(
                (res: IBlogContenido[]) => {
                    this.blogContenidos = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBlogContenidos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBlogContenido) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInBlogContenidos() {
        this.eventSubscriber = this.eventManager.subscribe('blogContenidoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
