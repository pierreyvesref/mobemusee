import { environment } from '../environments/environment';

export enum ServiceUrlNames {
  AUTHENTIFICATION = 'authentification',
  MUSEE = 'musee',
  PAIEMENT = 'paiement',
}

export function buildUrlAuthent(url: string): string {
  return `${environment.protocole}://${environment.localhost}:${environment.port}/${environment.api}/${url}`;
}

export function buildUrlMusee(url: string): string {
  return `${environment.protocole}://${environment.localhost}:${environment.port}/${environment.api}/${url}`;
}

export function buildUrlPaiement(url: string): string {
  return `${environment.protocole}://${environment.localhost}:${environment.port}/${environment.api}/${url}`;
}

