import { IPais } from 'app/shared/model/pais.model';
import { IInstitucion } from 'app/shared/model/institucion.model';

export interface ICiudad {
    id?: number;
    ciudadNombre?: string;
    pais?: IPais;
    institucion?: IInstitucion;
}

export class Ciudad implements ICiudad {
    constructor(public id?: number, public ciudadNombre?: string, public pais?: IPais, public institucion?: IInstitucion) {}
}
