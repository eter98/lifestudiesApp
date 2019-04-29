/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { LifestudiesAppTestModule } from '../../../test.module';
import { TipoProgramaUpdateComponent } from 'app/entities/tipo-programa/tipo-programa-update.component';
import { TipoProgramaService } from 'app/entities/tipo-programa/tipo-programa.service';
import { TipoPrograma } from 'app/shared/model/tipo-programa.model';

describe('Component Tests', () => {
    describe('TipoPrograma Management Update Component', () => {
        let comp: TipoProgramaUpdateComponent;
        let fixture: ComponentFixture<TipoProgramaUpdateComponent>;
        let service: TipoProgramaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [TipoProgramaUpdateComponent]
            })
                .overrideTemplate(TipoProgramaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TipoProgramaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoProgramaService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TipoPrograma(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tipoPrograma = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TipoPrograma();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tipoPrograma = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
