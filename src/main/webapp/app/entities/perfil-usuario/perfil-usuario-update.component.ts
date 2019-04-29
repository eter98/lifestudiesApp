import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { PerfilUsuarioService } from './perfil-usuario.service';

@Component({
    selector: 'jhi-perfil-usuario-update',
    templateUrl: './perfil-usuario-update.component.html'
})
export class PerfilUsuarioUpdateComponent implements OnInit {
    perfilUsuario: IPerfilUsuario;
    isSaving: boolean;
    fechaNacimientoDp: any;
    fechaInicioDp: any;

    constructor(protected perfilUsuarioService: PerfilUsuarioService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ perfilUsuario }) => {
            this.perfilUsuario = perfilUsuario;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.perfilUsuario.id !== undefined) {
            this.subscribeToSaveResponse(this.perfilUsuarioService.update(this.perfilUsuario));
        } else {
            this.subscribeToSaveResponse(this.perfilUsuarioService.create(this.perfilUsuario));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfilUsuario>>) {
        result.subscribe((res: HttpResponse<IPerfilUsuario>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
