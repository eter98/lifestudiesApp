/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { BlogContenidoService } from 'app/entities/blog-contenido/blog-contenido.service';
import { IBlogContenido, BlogContenido, Nacionalidadd, Destinod, NivelAcademicod } from 'app/shared/model/blog-contenido.model';

describe('Service Tests', () => {
    describe('BlogContenido Service', () => {
        let injector: TestBed;
        let service: BlogContenidoService;
        let httpMock: HttpTestingController;
        let elemDefault: IBlogContenido;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BlogContenidoService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new BlogContenido(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                Nacionalidadd.Argentina,
                Destinod.ALEMANIA,
                0,
                'AAAAAAA',
                0,
                NivelAcademicod.PRIMARIA,
                'AAAAAAA',
                0,
                false,
                'AAAAAAA',
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA'
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a BlogContenido', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new BlogContenido(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a BlogContenido', async () => {
                const returnedFromService = Object.assign(
                    {
                        nombre: 'BBBBBB',
                        apellido: 'BBBBBB',
                        correo: 'BBBBBB',
                        nacionalidad: 'BBBBBB',
                        paisEstudio: 'BBBBBB',
                        calificacionPais: 1,
                        ciudadVive: 'BBBBBB',
                        calificacionCiudad: 1,
                        programaRealizado: 'BBBBBB',
                        institucionEstudio: 'BBBBBB',
                        calificacionInstitucion: 1,
                        agenciaEstudios: true,
                        nombreAgencia: 'BBBBBB',
                        calificacionAgencia: 1,
                        calificacionExperienciaEstudio: 1,
                        titulo: 'BBBBBB',
                        contenido: 'BBBBBB',
                        imagen: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of BlogContenido', async () => {
                const returnedFromService = Object.assign(
                    {
                        nombre: 'BBBBBB',
                        apellido: 'BBBBBB',
                        correo: 'BBBBBB',
                        nacionalidad: 'BBBBBB',
                        paisEstudio: 'BBBBBB',
                        calificacionPais: 1,
                        ciudadVive: 'BBBBBB',
                        calificacionCiudad: 1,
                        programaRealizado: 'BBBBBB',
                        institucionEstudio: 'BBBBBB',
                        calificacionInstitucion: 1,
                        agenciaEstudios: true,
                        nombreAgencia: 'BBBBBB',
                        calificacionAgencia: 1,
                        calificacionExperienciaEstudio: 1,
                        titulo: 'BBBBBB',
                        contenido: 'BBBBBB',
                        imagen: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a BlogContenido', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
