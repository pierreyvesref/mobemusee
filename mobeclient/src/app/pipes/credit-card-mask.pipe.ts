import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'creditCardMask'
})
export class CreditCardMaskPipe implements PipeTransform {
  transform(plainCreditCard: number, visibleDigits: number = 4): string {
    //const visibleDigits = 4;
    let plainCreditCardStr = plainCreditCard.toString();
    let maskedSection = plainCreditCardStr.slice(0, -visibleDigits);
    let visibleSection = plainCreditCardStr.slice(-visibleDigits);
    return maskedSection.replace(/./g, '*') + visibleSection;
  }
}