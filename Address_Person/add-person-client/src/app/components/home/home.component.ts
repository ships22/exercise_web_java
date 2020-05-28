import { Component, OnInit } from '@angular/core';
import { HttpClientService } from 'src/app/http-client.service';
import { Person } from 'src/app/model/person';
import { Address } from 'src/app/model/address';
import { User } from 'src/app/model/user';

import * as jwt_decode from "jwt-decode";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  personList : Person[];
  personToAdd: Person;    
  addressList: Address[];
  isLoggedIn = false;
  isAdmin = false;
  constructor(public httpService: HttpClientService) { }

  ngOnInit(): void {
    this.getAllperson();
    this.getAllAddress();
    
  }

  getAllperson() {
    this.httpService.getAllPerson()
    .subscribe(response => this.personList = response)
  }
  submit(e) {
    //let addressToadd = this.addressList.find(data => data.id == e.value.address);
    personToAdd: Person;
    this.personToAdd = e.value;
    this.personToAdd.address = this.addressList.find(data => data.id == e.value.address);
    
    
    this.httpService.addPerson(this.personToAdd)
    .subscribe()
  }
  getAllAddress() {
    this.httpService.getAllAddress()
    .subscribe(response => this.addressList = response)
  }
  submitAddress(address) {
    this.httpService.addAddress(address.value)
    .subscribe()
  }
  
}
