/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { TipoProgramaComponent } from 'app/entities/tipo-programa/tipo-programa.component';
import { TipoProgramaService } from 'app/entities/tipo-programa/tipo-programa.service';
import { TipoPrograma } from 'app/shared/model/tipo-programa.model';

describe('Component Tests', () => {
    describe('TipoPrograma Management Component', () => {
        let comp: TipoProgramaComponent;
        let fixture: ComponentFixture<TipoProgramaComponent>;
        let service: TipoProgramaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [TipoProgramaComponent],
                providers: []
            })
                .overrideTemplate(TipoProgramaComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoProgramaComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoProgramaService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TipoPrograma(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tipoProgramas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
