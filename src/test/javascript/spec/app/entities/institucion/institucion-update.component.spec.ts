/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { LifestudiesAppTestModule } from '../../../test.module';
import { InstitucionUpdateComponent } from 'app/entities/institucion/institucion-update.component';
import { InstitucionService } from 'app/entities/institucion/institucion.service';
import { Institucion } from 'app/shared/model/institucion.model';

describe('Component Tests', () => {
    describe('Institucion Management Update Component', () => {
        let comp: InstitucionUpdateComponent;
        let fixture: ComponentFixture<InstitucionUpdateComponent>;
        let service: InstitucionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [InstitucionUpdateComponent]
            })
                .overrideTemplate(InstitucionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InstitucionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InstitucionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Institucion(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.institucion = entity;
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
                    const entity = new Institucion();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.institucion = entity;
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
