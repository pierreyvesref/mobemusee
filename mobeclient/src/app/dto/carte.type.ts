//export type TypeCarteDTO = 'CB' | 'VISA' | 'MASTERCARD' | 'VISA_ELECTRON' | 'MAESTRO';

export class TypeCarteDTO {
    public value: string;

    constructor(_value: string) {
        this.value = _value;

    }
}