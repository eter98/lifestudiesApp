export const enum Nacionalidadd {
    Argentina = 'Argentina',
    Bolivia = 'Bolivia',
    Brasil = 'Brasil',
    Chile = 'Chile',
    Colombia = 'Colombia',
    Costa_Rica = 'Costa_Rica',
    Ecuador = 'Ecuador',
    Guatemala = 'Guatemala',
    Honduras = 'Honduras',
    Mexico = 'Mexico',
    Nicaragua = 'Nicaragua',
    Panama = 'Panama',
    Paraguay = 'Paraguay',
    Peru = 'Peru',
    Uruguay = 'Uruguay',
    Venezuela = 'Venezuela'
}

export const enum Destinod {
    ALEMANIA = 'ALEMANIA',
    AUSTRALIA = 'AUSTRALIA',
    CANADA = 'CANADA',
    ESPANA = 'ESPANA',
    ESTADOS_UNIDOS = 'ESTADOS_UNIDOS',
    FRANCIA = 'FRANCIA',
    INGLATERRA = 'INGLATERRA',
    IRLANDA = 'IRLANDA',
    ITALIA = 'ITALIA',
    JAPON = 'JAPON',
    MALTA = 'MALTA',
    NUEVA_ZELANDA = 'NUEVA_ZELANDA',
    SUDAFRICA = 'SUDAFRICA',
    SUIZA = 'SUIZA'
}

export const enum NivelAcademicod {
    PRIMARIA = 'PRIMARIA',
    SECUNDARIA = 'SECUNDARIA',
    PREGRADO = 'PREGRADO',
    POSGRADO = 'POSGRADO',
    DOCTORADO = 'DOCTORADO'
}

export interface IBlogContenido {
    id?: number;
    nombre?: string;
    apellido?: string;
    correo?: string;
    nacionalidad?: Nacionalidadd;
    paisEstudio?: Destinod;
    calificacionPais?: number;
    ciudadVive?: string;
    calificacionCiudad?: number;
    programaRealizado?: NivelAcademicod;
    institucionEstudio?: string;
    calificacionInstitucion?: number;
    agenciaEstudios?: boolean;
    nombreAgencia?: string;
    calificacionAgencia?: number;
    calificacionExperienciaEstudio?: number;
    titulo?: string;
    contenido?: any;
    imagenContentType?: string;
    imagen?: any;
}

export class BlogContenido implements IBlogContenido {
    constructor(
        public id?: number,
        public nombre?: string,
        public apellido?: string,
        public correo?: string,
        public nacionalidad?: Nacionalidadd,
        public paisEstudio?: Destinod,
        public calificacionPais?: number,
        public ciudadVive?: string,
        public calificacionCiudad?: number,
        public programaRealizado?: NivelAcademicod,
        public institucionEstudio?: string,
        public calificacionInstitucion?: number,
        public agenciaEstudios?: boolean,
        public nombreAgencia?: string,
        public calificacionAgencia?: number,
        public calificacionExperienciaEstudio?: number,
        public titulo?: string,
        public contenido?: any,
        public imagenContentType?: string,
        public imagen?: any
    ) {
        this.agenciaEstudios = this.agenciaEstudios || false;
    }
}
