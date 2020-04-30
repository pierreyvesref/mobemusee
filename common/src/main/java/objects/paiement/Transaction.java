package objects.paiement;

import java.util.Date;
import java.util.List;

public class Transaction {

    private int idTransaction;
    private Date dateTransaction;
    private double montant;

    MoyenPaiement moyenPaiement;

    List<BilletMusee> billetsMusee;

    public Transaction(int id, MoyenPaiement moyenPaiement, List<BilletMusee> billetsMusee, double montant) {
        this.idTransaction = id;
        this.dateTransaction = new Date();
        this.moyenPaiement = moyenPaiement;
        this.billetsMusee = billetsMusee;
        this.montant = montant;
    }

    public Transaction() {
    }

    public int getIdTransaction() {
        return this.idTransaction;
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

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public MoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public List<BilletMusee> getBilletsMusee() {
        return billetsMusee;
    }

    public void setBilletsMusee(List<BilletMusee> billetsMusee) {
        this.billetsMusee = billetsMusee;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", dateTransaction=" + dateTransaction +
                ", montant=" + montant +
                ", moyenPaiement=" + moyenPaiement +
                ", billetsmusee=" + billetsMusee +
                '}';
    }
}
