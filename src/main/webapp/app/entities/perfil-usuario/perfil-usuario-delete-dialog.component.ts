import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPerfilUsuario } from 'app/shared/model/perfil-usuario.model';
import { PerfilUsuarioService } from './perfil-usuario.service';

@Component({
    selector: 'jhi-perfil-usuario-delete-dialog',
    templateUrl: './perfil-usuario-delete-dialog.component.html'
})
export class PerfilUsuarioDeleteDialogComponent {
    perfilUsuario: IPerfilUsuario;

    constructor(
        protected perfilUsuarioService: PerfilUsuarioService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.perfilUsuarioService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'perfilUsuarioListModification',
                content: 'Deleted an perfilUsuario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-perfil-usuario-delete-popup',
    template: ''
})
export class PerfilUsuarioDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ perfilUsuario }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PerfilUsuarioDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.perfilUsuario = perfilUsuario;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/perfil-usuario', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/perfil-usuario', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
