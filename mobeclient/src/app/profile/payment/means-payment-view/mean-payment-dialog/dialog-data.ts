import { TypeCarteDTO } from 'src/app/dto/carte.type';

export interface DialogData {
    titulaire: string;
    typeCarte: TypeCarteDTO;
    numCompte: number;
    cryptogramme: number;
    dateExpiration: Date;
}