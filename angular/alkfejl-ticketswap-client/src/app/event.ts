import { TicketSale } from "./ticket-sale"
import {TicketWanted} from "./ticket-wanted";

export class Event {
    id: number;
    name: string;
    date: string;
    ticketSale: Array<TicketSale>;
    ticketWanted: Array<TicketWanted>;

}
