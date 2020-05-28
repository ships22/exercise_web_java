export class User {
    private id: number;
	private userName: string;
	private password: string;
    
    constructor(
        id: number,
        userName: string,
        password: string
    ){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
