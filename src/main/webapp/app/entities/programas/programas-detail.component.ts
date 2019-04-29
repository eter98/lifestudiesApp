import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProgramas } from 'app/shared/model/programas.model';

@Component({
    selector: 'jhi-programas-detail',
    templateUrl: './programas-detail.component.html'
})
export class ProgramasDetailComponent implements OnInit {
    programas: IProgramas;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ programas }) => {
            this.programas = programas;
        });
    }

    previousState() {
        window.history.back();
    }
}
