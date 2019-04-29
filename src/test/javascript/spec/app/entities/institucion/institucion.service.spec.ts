/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { InstitucionService } from 'app/entities/institucion/institucion.service';
import { IInstitucion, Institucion } from 'app/shared/model/institucion.model';

describe('Service Tests', () => {
    describe('Institucion Service', () => {
        let injector: TestBed;
        let service: InstitucionService;
        let httpMock: HttpTestingController;
        let elemDefault: IInstitucion;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(InstitucionService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Institucion(
                0,
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
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

            it('should create a Institucion', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Institucion(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Institucion', async () => {
                const returnedFromService = Object.assign(
                    {
                        nombre: 'BBBBBB',
                        tipo: 1,
                        webSite: 'BBBBBB',
                        prefixNombre: 'BBBBBB',
                        nombreResponsable: 'BBBBBB',
                        apellidoResponsable: 'BBBBBB',
                        mail: 'BBBBBB',
                        areaCode: 'BBBBBB',
                        telefono: 'BBBBBB',
                        movil: 'BBBBBB',
                        skype: 'BBBBBB',
                        direccion: 'BBBBBB',
                        direccion2: 'BBBBBB',
                        estado: 'BBBBBB',
                        zipCode: 'BBBBBB',
                        mailCorporativo: 'BBBBBB',
                        movilCorporativo: 'BBBBBB',
                        skypeCorporativo: 'BBBBBB',
                        logo: 'BBBBBB',
                        imagen: 'BBBBBB',
                        imagen2: 'BBBBBB',
                        imagen3: 'BBBBBB',
                        imagen4: 'BBBBBB',
                        imagen5: 'BBBBBB',
                        video: 'BBBBBB',
                        descripcion: 'BBBBBB'
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

            it('should return a list of Institucion', async () => {
                const returnedFromService = Object.assign(
                    {
                        nombre: 'BBBBBB',
                        tipo: 1,
                        webSite: 'BBBBBB',
                        prefixNombre: 'BBBBBB',
                        nombreResponsable: 'BBBBBB',
                        apellidoResponsable: 'BBBBBB',
                        mail: 'BBBBBB',
                        areaCode: 'BBBBBB',
                        telefono: 'BBBBBB',
                        movil: 'BBBBBB',
                        skype: 'BBBBBB',
                        direccion: 'BBBBBB',
                        direccion2: 'BBBBBB',
                        estado: 'BBBBBB',
                        zipCode: 'BBBBBB',
                        mailCorporativo: 'BBBBBB',
                        movilCorporativo: 'BBBBBB',
                        skypeCorporativo: 'BBBBBB',
                        logo: 'BBBBBB',
                        imagen: 'BBBBBB',
                        imagen2: 'BBBBBB',
                        imagen3: 'BBBBBB',
                        imagen4: 'BBBBBB',
                        imagen5: 'BBBBBB',
                        video: 'BBBBBB',
                        descripcion: 'BBBBBB'
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

            it('should delete a Institucion', async () => {
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
