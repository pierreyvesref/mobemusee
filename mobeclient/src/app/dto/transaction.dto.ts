import { BilletMuseeDTO } from './billet-musee.dto';
import { MoyenPaiementDTO } from './moyen-paiement.dto';

export class TransactionDTO {
    public idTransaction: number;
    public dateTransaction: Date;
    public montant: number;
    public billetsMusee: BilletMuseeDTO[];
    public moyenPaiement: MoyenPaiementDTO;

    constructor(_billetsMusee: BilletMuseeDTO[]) {
        this.billetsMusee = _billetsMusee;
    }

}