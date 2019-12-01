import { User } from './user/user';

export class TicketSale {
    id: number;
    barcode: number;
    event: Event;
    price: number;
    seller: User;
}
