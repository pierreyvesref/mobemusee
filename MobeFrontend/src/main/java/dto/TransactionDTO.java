package dto;

import java.util.Date;
import java.util.List;

public class TransactionDTO {

    private Date dateTransaction;
    private double montant;

    private List<BilletMuseeDTO> billetsMusee;

    private MoyenPaiementDTO moyenPaiement;

    public TransactionDTO() {
    }

    public TransactionDTO(Date dateTransaction, double montant, List<BilletMuseeDTO> billetsMusee, MoyenPaiementDTO moyenPaiement) {
        this.dateTransaction = dateTransaction;
        this.montant = montant;
        this.billetsMusee = billetsMusee;
        this.moyenPaiement = moyenPaiement;
    }

    public TransactionDTO(List<BilletMuseeDTO> billetsMusee) {
        this.billetsMusee = billetsMusee;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public List<BilletMuseeDTO> getBilletsMusee() {
        return billetsMusee;
    }

    public void setBilletsMusee(List<BilletMuseeDTO> billetsMusee) {
        this.billetsMusee = billetsMusee;
    }

    public MoyenPaiementDTO getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiementDTO moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "dateTransaction=" + dateTransaction +
                ", montant=" + montant +
                ", billetsMusee=" + billetsMusee +
                ", moyenPaiement=" + moyenPaiement +
                '}';
    }

}
