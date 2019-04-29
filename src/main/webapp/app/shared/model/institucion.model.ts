import { ICiudad } from 'app/shared/model/ciudad.model';
import { IProgramas } from 'app/shared/model/programas.model';

export interface IInstitucion {
    id?: number;
    nombre?: string;
    tipo?: number;
    webSite?: string;
    prefixNombre?: string;
    nombreResponsable?: string;
    apellidoResponsable?: string;
    mail?: string;
    areaCode?: string;
    telefono?: string;
    movil?: string;
    skype?: string;
    direccion?: string;
    direccion2?: string;
    estado?: string;
    zipCode?: string;
    mailCorporativo?: string;
    movilCorporativo?: string;
    skypeCorporativo?: string;
    logoContentType?: string;
    logo?: any;
    imagenContentType?: string;
    imagen?: any;
    imagen2ContentType?: string;
    imagen2?: any;
    imagen3ContentType?: string;
    imagen3?: any;
    imagen4ContentType?: string;
    imagen4?: any;
    imagen5ContentType?: string;
    imagen5?: any;
    videoContentType?: string;
    video?: any;
    descripcion?: any;
    ciudad?: ICiudad;
    programas?: IProgramas;
}

export class Institucion implements IInstitucion {
    constructor(
        public id?: number,
        public nombre?: string,
        public tipo?: number,
        public webSite?: string,
        public prefixNombre?: string,
        public nombreResponsable?: string,
        public apellidoResponsable?: string,
        public mail?: string,
        public areaCode?: string,
        public telefono?: string,
        public movil?: string,
        public skype?: string,
        public direccion?: string,
        public direccion2?: string,
        public estado?: string,
        public zipCode?: string,
        public mailCorporativo?: string,
        public movilCorporativo?: string,
        public skypeCorporativo?: string,
        public logoContentType?: string,
        public logo?: any,
        public imagenContentType?: string,
        public imagen?: any,
        public imagen2ContentType?: string,
        public imagen2?: any,
        public imagen3ContentType?: string,
        public imagen3?: any,
        public imagen4ContentType?: string,
        public imagen4?: any,
        public imagen5ContentType?: string,
        public imagen5?: any,
        public videoContentType?: string,
        public video?: any,
        public descripcion?: any,
        public ciudad?: ICiudad,
        public programas?: IProgramas
    ) {}
}
