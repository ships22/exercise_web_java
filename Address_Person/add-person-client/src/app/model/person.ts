import { Address } from './address';
import { User } from './user';

export class Person {
    id: number;
    first_name: string;
	last_name: string;
	email: string;
    phone: string;
    address: Address;
    user: User;

    constructor(id: number, 
        first_name: string,
        last_name: string, 
        email: string, 
        phone: string, 
        address: Address){
         this.id = id; 
         this.first_name =  first_name;
         this.last_name =  last_name;
         this.email =  email;
         this.phone =  phone;
         this.address =  address;
        }
}
