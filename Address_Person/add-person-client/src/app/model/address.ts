export class Address {
    id: number;
	private no: number;
	private street: string;
	private town: string;
	private district: string;
    private country: string;
    
    constructor(
        id: number,
        no: number,
        street: string,
        town: string,
        district: string,
        country: string
    ){
        this.id = id;
        this.no = no;
        this.street = street;
        this.town = town;
        this.district = district;
        this.country = country;
    }
}
