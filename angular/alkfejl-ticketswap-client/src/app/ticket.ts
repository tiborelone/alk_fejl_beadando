import {User} from "./user";

export class Ticket {
    id: number;
    name: string;
    event: Event;
    barcode: number;
    seller: User;
    price: number;
}
