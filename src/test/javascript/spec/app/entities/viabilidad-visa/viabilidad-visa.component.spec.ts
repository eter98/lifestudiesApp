/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { ViabilidadVisaComponent } from 'app/entities/viabilidad-visa/viabilidad-visa.component';
import { ViabilidadVisaService } from 'app/entities/viabilidad-visa/viabilidad-visa.service';
import { ViabilidadVisa } from 'app/shared/model/viabilidad-visa.model';

describe('Component Tests', () => {
    describe('ViabilidadVisa Management Component', () => {
        let comp: ViabilidadVisaComponent;
        let fixture: ComponentFixture<ViabilidadVisaComponent>;
        let service: ViabilidadVisaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [ViabilidadVisaComponent],
                providers: []
            })
                .overrideTemplate(ViabilidadVisaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ViabilidadVisaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ViabilidadVisaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ViabilidadVisa(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.viabilidadVisas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
