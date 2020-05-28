import { Component, OnInit } from '@angular/core';
import { HttpClientService } from 'src/app/http-client.service';
import { User } from 'src/app/model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  userToLogIn: User;
  constructor(private httpService: HttpClientService, private router: Router) { }

  ngOnInit(): void {
  }
  login(user) {
    this.userToLogIn = new User(user.value.userName, user.value.password);
    this.httpService.signIn(this.userToLogIn)
    .subscribe(response => {
      this.router.navigate(['']);
      // location.reload();
    })    
  }

}
