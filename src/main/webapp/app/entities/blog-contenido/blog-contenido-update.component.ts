import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiDataUtils } from 'ng-jhipster';
import { IBlogContenido } from 'app/shared/model/blog-contenido.model';
import { BlogContenidoService } from './blog-contenido.service';

@Component({
    selector: 'jhi-blog-contenido-update',
    templateUrl: './blog-contenido-update.component.html'
})
export class BlogContenidoUpdateComponent implements OnInit {
    blogContenido: IBlogContenido;
    isSaving: boolean;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected blogContenidoService: BlogContenidoService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ blogContenido }) => {
            this.blogContenido = blogContenido;
        });
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
        this.dataUtils.clearInputImage(this.blogContenido, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.blogContenido.id !== undefined) {
            this.subscribeToSaveResponse(this.blogContenidoService.update(this.blogContenido));
        } else {
            this.subscribeToSaveResponse(this.blogContenidoService.create(this.blogContenido));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlogContenido>>) {
        result.subscribe((res: HttpResponse<IBlogContenido>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
