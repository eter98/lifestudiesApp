import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInstitucion } from 'app/shared/model/institucion.model';

type EntityResponseType = HttpResponse<IInstitucion>;
type EntityArrayResponseType = HttpResponse<IInstitucion[]>;

@Injectable({ providedIn: 'root' })
export class InstitucionService {
    public resourceUrl = SERVER_API_URL + 'api/institucions';

    constructor(protected http: HttpClient) {}

    create(institucion: IInstitucion): Observable<EntityResponseType> {
        return this.http.post<IInstitucion>(this.resourceUrl, institucion, { observe: 'response' });
    }

    update(institucion: IInstitucion): Observable<EntityResponseType> {
        return this.http.put<IInstitucion>(this.resourceUrl, institucion, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInstitucion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInstitucion[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
