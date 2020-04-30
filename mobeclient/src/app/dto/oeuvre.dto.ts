export class OeuvreDTO {
    public id: number;
    public nomOeuvre: string;
    public dateAchat: Date;
    public prixAchat: number;

    constructor(_id: number, _nomOeuvre: string, _dateAchat: Date, _prixAchat: number) {
        this.id = _id;
        this.nomOeuvre = _nomOeuvre;
        this.dateAchat = _dateAchat;
        this.prixAchat =_prixAchat;
    }
}