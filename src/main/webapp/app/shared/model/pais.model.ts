import { ICiudad } from 'app/shared/model/ciudad.model';

export interface IPais {
    id?: number;
    paisNombre?: string;
    ciudad?: ICiudad;
}

export class Pais implements IPais {
    constructor(public id?: number, public paisNombre?: string, public ciudad?: ICiudad) {}
}
