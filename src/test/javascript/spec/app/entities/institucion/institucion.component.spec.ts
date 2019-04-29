/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { InstitucionComponent } from 'app/entities/institucion/institucion.component';
import { InstitucionService } from 'app/entities/institucion/institucion.service';
import { Institucion } from 'app/shared/model/institucion.model';

describe('Component Tests', () => {
    describe('Institucion Management Component', () => {
        let comp: InstitucionComponent;
        let fixture: ComponentFixture<InstitucionComponent>;
        let service: InstitucionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [InstitucionComponent],
                providers: []
            })
                .overrideTemplate(InstitucionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InstitucionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InstitucionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Institucion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.institucions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
