/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LifestudiesAppTestModule } from '../../../test.module';
import { BlogContenidoComponent } from 'app/entities/blog-contenido/blog-contenido.component';
import { BlogContenidoService } from 'app/entities/blog-contenido/blog-contenido.service';
import { BlogContenido } from 'app/shared/model/blog-contenido.model';

describe('Component Tests', () => {
    describe('BlogContenido Management Component', () => {
        let comp: BlogContenidoComponent;
        let fixture: ComponentFixture<BlogContenidoComponent>;
        let service: BlogContenidoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LifestudiesAppTestModule],
                declarations: [BlogContenidoComponent],
                providers: []
            })
                .overrideTemplate(BlogContenidoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BlogContenidoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlogContenidoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new BlogContenido(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.blogContenidos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
