/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LifestudiesAppTestModule } from '../../../test.module';
import { InstitucionDetailComponent } from 'app/entities/institucion/institucion-detail.component';
import { Institucion } from 'app/shared/model/institucion.model';

describe('Component Tests', () => {
    describe('Institucion Management Detail Component', () => {
        let comp: InstitucionDetailComponent;
        let fixture: ComponentFixture<InstitucionDetailComponent>;
        const route = ({ data: of({ institucion: new Institucion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [InstitucionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InstitucionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InstitucionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.institucion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
