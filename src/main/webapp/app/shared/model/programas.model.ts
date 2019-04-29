import { IInstitucion } from 'app/shared/model/institucion.model';
import { ICotizacion } from 'app/shared/model/cotizacion.model';

export const enum Duraciond {
    UN_MES = 'UN_MES',
    DOS_MESES = 'DOS_MESES',
    TRES_MESES = 'TRES_MESES',
    CUATRO_MESES = 'CUATRO_MESES',
    CINCO_MESES = 'CINCO_MESES',
    SEIS_MESES = 'SEIS_MESES',
    SIETE_MESES = 'SIETE_MESES',
    OCHO_MESES = 'OCHO_MESES',
    NUEVE_MESES = 'NUEVE_MESES',
    DIEZ_MESES = 'DIEZ_MESES',
    ONCE_MESES = 'ONCE_MESES',
    DOCE_MESES = 'DOCE_MESES'
}

export const enum TipoProgramad {
    Programas_Idiomas = 'Programas_Idiomas',
    Curso_Verano_idiomas = 'Curso_Verano_idiomas',
    Programa_preparacion_Examenes_idioma = 'Programa_preparacion_Examenes_idioma',
    Programa_Pregrado = 'Programa_Pregrado',
    Programa_Posgrado = 'Programa_Posgrado',
    Programas_Cortos_Ejecutivos = 'Programas_Cortos_Ejecutivos'
}

export interface IProgramas {
    id?: number;
    programaNombre?: string;
    duracion?: Duraciond;
    tipo?: TipoProgramad;
    institucion?: IInstitucion;
    cotizacion?: ICotizacion;
}

export class Programas implements IProgramas {
    constructor(
        public id?: number,
        public programaNombre?: string,
        public duracion?: Duraciond,
        public tipo?: TipoProgramad,
        public institucion?: IInstitucion,
        public cotizacion?: ICotizacion
    ) {}
}
