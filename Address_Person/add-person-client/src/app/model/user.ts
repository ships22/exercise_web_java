export class User {
    private id: number
	private userName: string;
	private password: string;
    
    constructor(
        
        userName: string,
        password: string
    ){
        
        this.userName = userName;
        this.password = password;
    }
}
