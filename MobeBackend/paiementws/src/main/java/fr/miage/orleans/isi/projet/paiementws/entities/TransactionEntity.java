package fr.miage.orleans.isi.projet.paiementws.entities;

import fr.miage.orleans.isi.projet.paiementws.DTO.BilletMuseeDTO;
import fr.miage.orleans.isi.projet.paiementws.DTO.TransactionDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTransaction;
    private Date dateTransaction;
    private double montant;
    @OneToOne
    MoyenPaiementEntity moyenPaiement;
    @OneToMany
    List<BilletMuseeEntity> billetsMusee;


    public static TransactionEntity createFromDTO(TransactionDTO moe) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setDateTransaction(moe.getDateTransaction());
        transactionEntity.setMontant(moe.getMontant());
        transactionEntity.setMoyenPaiement(MoyenPaiementEntity.createFromDTO(moe.getMoyenPaiement()));
        transactionEntity.setBilletsMusee(TransactionEntity.createListBilletMuseeEntityFromDTO(moe.getBilletsMusee()));
        return transactionEntity;
    }

    public static List<BilletMuseeEntity> createListBilletMuseeEntityFromDTO(List<BilletMuseeDTO> listEntree){
        List<BilletMuseeEntity> result = new ArrayList<>();
        for (BilletMuseeDTO bTO: listEntree) {
            result.add(BilletMuseeEntity.createFromDTO(bTO));
        }
        return result;
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

    public MoyenPaiementEntity getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiementEntity moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    public List<BilletMuseeEntity> getBilletsMusee() {
        return billetsMusee;
    }

    public void setBilletsMusee(List<BilletMuseeEntity> billetsMusee) {
        this.billetsMusee = billetsMusee;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "idTransaction=" + idTransaction +
                ", dateTransaction=" + dateTransaction +
                ", montant=" + montant +
                ", moyenPaiement=" + moyenPaiement +
                ", billetsMusee=" + billetsMusee +
                '}';
    }
}
