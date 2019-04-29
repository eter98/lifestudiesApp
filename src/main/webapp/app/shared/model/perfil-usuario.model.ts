import { Moment } from 'moment';

export interface IPerfilUsuario {
    id?: number;
    nombre?: string;
    apellido?: string;
    fechaNacimiento?: Moment;
    mail?: string;
    area?: number;
    telefono?: number;
    nivelAcademico?: string;
    areaAcademica?: string;
    terminoAcademico?: number;
    puntajeICFES?: number;
    promedioAcademico?: number;
    dominioIdioma?: boolean;
    idiomas?: string;
    examenIdioma?: boolean;
    examenRealizado?: string;
    puntajeIdioma?: number;
    becaOtorgada?: boolean;
    tipoBeca?: number;
    becaInstitucion?: string;
    grupoSocial?: boolean;
    fundacion?: string;
    monitor?: boolean;
    monitorMateria?: string;
    logrosAcademicos?: string;
    experienciaLaboral?: boolean;
    areaLaboral?: string;
    programarealizar?: number;
    programaArea?: string;
    fechaInicio?: Moment;
    programaEncontrado?: boolean;
    nombrePrograma?: string;
    universidad?: string;
    pais?: number;
    merecedorBeca?: string;
}

export class PerfilUsuario implements IPerfilUsuario {
    constructor(
        public id?: number,
        public nombre?: string,
        public apellido?: string,
        public fechaNacimiento?: Moment,
        public mail?: string,
        public area?: number,
        public telefono?: number,
        public nivelAcademico?: string,
        public areaAcademica?: string,
        public terminoAcademico?: number,
        public puntajeICFES?: number,
        public promedioAcademico?: number,
        public dominioIdioma?: boolean,
        public idiomas?: string,
        public examenIdioma?: boolean,
        public examenRealizado?: string,
        public puntajeIdioma?: number,
        public becaOtorgada?: boolean,
        public tipoBeca?: number,
        public becaInstitucion?: string,
        public grupoSocial?: boolean,
        public fundacion?: string,
        public monitor?: boolean,
        public monitorMateria?: string,
        public logrosAcademicos?: string,
        public experienciaLaboral?: boolean,
        public areaLaboral?: string,
        public programarealizar?: number,
        public programaArea?: string,
        public fechaInicio?: Moment,
        public programaEncontrado?: boolean,
        public nombrePrograma?: string,
        public universidad?: string,
        public pais?: number,
        public merecedorBeca?: string
    ) {
        this.dominioIdioma = this.dominioIdioma || false;
        this.examenIdioma = this.examenIdioma || false;
        this.becaOtorgada = this.becaOtorgada || false;
        this.grupoSocial = this.grupoSocial || false;
        this.monitor = this.monitor || false;
        this.experienciaLaboral = this.experienciaLaboral || false;
        this.programaEncontrado = this.programaEncontrado || false;
    }
}
