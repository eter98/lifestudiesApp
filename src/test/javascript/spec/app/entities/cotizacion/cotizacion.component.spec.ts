/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { CotizacionComponent } from 'app/entities/cotizacion/cotizacion.component';
import { CotizacionService } from 'app/entities/cotizacion/cotizacion.service';
import { Cotizacion } from 'app/shared/model/cotizacion.model';

describe('Component Tests', () => {
    describe('Cotizacion Management Component', () => {
        let comp: CotizacionComponent;
        let fixture: ComponentFixture<CotizacionComponent>;
        let service: CotizacionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [CotizacionComponent],
                providers: []
            })
                .overrideTemplate(CotizacionComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CotizacionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CotizacionService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Cotizacion(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.cotizacions[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
