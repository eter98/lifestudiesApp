import { Moment } from 'moment';
import { IProgramas } from 'app/shared/model/programas.model';

export interface ICotizacion {
    id?: number;
    nombre?: string;
    apellido?: string;
    mail?: string;
    areaCode?: string;
    telefono?: string;
    movil?: string;
    nacionalidad?: number;
    area?: string;
    fechaViaje?: Moment;
    programas?: IProgramas;
}

export class Cotizacion implements ICotizacion {
    constructor(
        public id?: number,
        public nombre?: string,
        public apellido?: string,
        public mail?: string,
        public areaCode?: string,
        public telefono?: string,
        public movil?: string,
        public nacionalidad?: number,
        public area?: string,
        public fechaViaje?: Moment,
        public programas?: IProgramas
    ) {}
}
