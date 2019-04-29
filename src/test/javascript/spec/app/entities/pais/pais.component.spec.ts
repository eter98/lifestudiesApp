/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { PaisComponent } from 'app/entities/pais/pais.component';
import { PaisService } from 'app/entities/pais/pais.service';
import { Pais } from 'app/shared/model/pais.model';

describe('Component Tests', () => {
    describe('Pais Management Component', () => {
        let comp: PaisComponent;
        let fixture: ComponentFixture<PaisComponent>;
        let service: PaisService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [PaisComponent],
                providers: []
            })
                .overrideTemplate(PaisComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PaisComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PaisService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Pais(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pais[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
