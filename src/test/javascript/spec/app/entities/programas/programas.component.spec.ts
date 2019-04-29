/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { ProgramasComponent } from 'app/entities/programas/programas.component';
import { ProgramasService } from 'app/entities/programas/programas.service';
import { Programas } from 'app/shared/model/programas.model';

describe('Component Tests', () => {
    describe('Programas Management Component', () => {
        let comp: ProgramasComponent;
        let fixture: ComponentFixture<ProgramasComponent>;
        let service: ProgramasService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [ProgramasComponent],
                providers: []
            })
                .overrideTemplate(ProgramasComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProgramasComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProgramasService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Programas(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.programas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
