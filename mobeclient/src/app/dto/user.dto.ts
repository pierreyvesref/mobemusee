import { RoleDTO } from './role.type';
import { MoyenPaiementDTO } from './moyen-paiement.dto';
import { TransactionDTO } from './transaction.dto';

export class UserDTO {
    login: string;
    mdp: string;
    roles: RoleDTO[];
    dateInscription: Date;
    nom: string;
    prenom: string;
    mail: string

    moyenPaiements: MoyenPaiementDTO[];
    transactions: TransactionDTO[];

    constructor(_login: string, _mdp: string, _roles: RoleDTO[], _nom: string, _prenom: string, _mail: string) {
       this.login = _login;
       this.mdp = _mdp;
       this.roles = _roles;
       this.nom = _nom;
       this.prenom = _prenom;
       this.mail = _mail;
   }

}