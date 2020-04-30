import { TypeTarifDTO } from './tarif.type';

export class BilletMuseeDTO {

    public keyQrCode: string;
    public dateVisite: Date;
    public isValide: boolean;
    public typeTarif: TypeTarifDTO;

    constructor(_dateVisite: Date, _typeTarif: TypeTarifDTO) {
        this.dateVisite = _dateVisite;
        this.typeTarif = _typeTarif;
    }

}