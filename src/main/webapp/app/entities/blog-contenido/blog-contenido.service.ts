import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBlogContenido } from 'app/shared/model/blog-contenido.model';

type EntityResponseType = HttpResponse<IBlogContenido>;
type EntityArrayResponseType = HttpResponse<IBlogContenido[]>;

@Injectable({ providedIn: 'root' })
export class BlogContenidoService {
    public resourceUrl = SERVER_API_URL + 'api/blog-contenidos';

    constructor(protected http: HttpClient) {}

    create(blogContenido: IBlogContenido): Observable<EntityResponseType> {
        return this.http.post<IBlogContenido>(this.resourceUrl, blogContenido, { observe: 'response' });
    }

    update(blogContenido: IBlogContenido): Observable<EntityResponseType> {
        return this.http.put<IBlogContenido>(this.resourceUrl, blogContenido, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBlogContenido>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBlogContenido[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
