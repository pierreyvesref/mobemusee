package fr.miage.orleans.isi.projet.paiementws.DTO;

import fr.miage.orleans.isi.projet.paiementws.entities.BilletMuseeEntity;
import fr.miage.orleans.isi.projet.paiementws.entities.TransactionEntity;
import objects.paiement.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDTO {
    private int idTransaction;
    private Date dateTransaction;
    private double montant;

    MoyenPaiementDTO moyenPaiement;

    List<BilletMuseeDTO> billetsMusee;

    public TransactionDTO() {
    }

    public TransactionDTO(int idTransaction, Date dateTransaction, double montant, MoyenPaiementDTO moyenPaiement, List<BilletMuseeDTO> billetsMusee) {
        this.idTransaction = idTransaction;
        this.dateTransaction = dateTransaction;
        this.montant = montant;
        this.moyenPaiement = moyenPaiement;
        this.billetsMusee = billetsMusee;
    }

    public static TransactionDTO createFromEntity(TransactionEntity entity){
        return new TransactionDTO(entity.getIdTransaction(), entity.getDateTransaction(), entity.getMontant(), MoyenPaiementDTO.createFromEntity(entity.getMoyenPaiement()), TransactionDTO.getListBilletDTOFromEntity(entity.getBilletsMusee()));
    }

    private static List<BilletMuseeDTO> getListBilletDTOFromEntity(List<BilletMuseeEntity> billetsMusee) {
        List<BilletMuseeDTO> list = new ArrayList<>();
        for (BilletMuseeEntity billetMuseeEntity : billetsMusee){
            list.add(BilletMuseeDTO.createFromEntity(billetMuseeEntity));
        }
        return list;
    }

    public static TransactionDTO createFromBasic(Transaction transaction) {
        //return new TransactionDTO(transaction.getIdTransaction(), transaction.getDateTransaction(), transaction.getMontant(), MoyenPaiementDTO.createFromBasic(transaction.getMoyenPaiement()), transaction.getBilletsMusee());
        return null;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
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

    public MoyenPaiementDTO getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiementDTO moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public List<BilletMuseeDTO> getBilletsMusee() {
        return billetsMusee;
    }

    public void setBilletsMusee(List<BilletMuseeDTO> billetsMusee) {
        this.billetsMusee = billetsMusee;
    }


}
