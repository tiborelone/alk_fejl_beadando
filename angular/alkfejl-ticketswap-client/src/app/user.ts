import { Ticket } from "./ticket";

export class User {
    id: number;
    username: string;
    password: string;
    name: string;
    selling: Array<Ticket>;
    rating: number;
}
