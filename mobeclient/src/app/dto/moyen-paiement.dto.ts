import { TypeCarteDTO } from './carte.type';

export class MoyenPaiementDTO {
    public idMoyenpaiement: number;
    public typeCarte: TypeCarteDTO;
    public numCompte: number;
    public dateExpiration: Date;
    public cryptogramme: number;
    public titulaire: string;

    constructor(_typeCarte: TypeCarteDTO, _numCompte: number, _dateExpiration: Date, _cryptogramme: number, _titulaire: string) {
        this.typeCarte = _typeCarte;
        this.numCompte = _numCompte;
        this.dateExpiration = _dateExpiration;
        this.cryptogramme =_cryptogramme;
        this.titulaire =_titulaire;
    }

}