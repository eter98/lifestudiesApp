import { IProgramas } from 'app/shared/model/programas.model';

export interface ITipoPrograma {
    id?: number;
    tipoNombre?: string;
    tipoCodigo?: IProgramas;
}

export class TipoPrograma implements ITipoPrograma {
    constructor(public id?: number, public tipoNombre?: string, public tipoCodigo?: IProgramas) {}
}
