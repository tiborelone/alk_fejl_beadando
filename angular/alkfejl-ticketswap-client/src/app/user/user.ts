import { TicketSale } from "../ticket-sale";
import {TicketWanted} from "../ticket-wanted";

export class User {
    id: number;
    username: string;
    password: string;
    name: string;
    selling: Array<TicketSale>;
    buying: Array<TicketWanted>;
    rating: number;
}
