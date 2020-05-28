import { Component, OnInit } from '@angular/core';
import { HttpClientService } from 'src/app/http-client.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.sass']
})
export class HeaderComponent implements OnInit {

  isLoggedIn = false;
  constructor(public httpService: HttpClientService) { }

  ngOnInit(): void {
   this.isLoggedIn = this.httpService.isLoggedIn;
  }
  
  logout() {
    localStorage.removeItem("token");
    this.isLoggedIn = !this.isLoggedIn;
    location.reload();
  }

}
