import { Component, OnInit } from '@angular/core';
import { HttpClientService } from 'src/app/http-client.service';
import { Person } from 'src/app/model/person';
import { Address } from 'src/app/model/address';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  personList : Person[];
  personToAdd: Person;    
  addressList: Address[];
  constructor(private httpService: HttpClientService) { }

  ngOnInit(): void {
    this.getAllperson();
    this.getAllAddress();
  }

  getAllperson() {
    this.httpService.getAllPerson()
    .subscribe(response => {this.personList = response, console.log('respone' , response);
    })
  }
  submit(e) {
    let addressToadd = this.addressList.find(data => data.id == e.value.address);
    let personToAdd = e.value;
    personToAdd.address = this.addressList.find(data => data.id == e.value.address);
    console.log('test send person to db :', personToAdd);
    
    this.httpService.addPerson(personToAdd)
    .subscribe(response => this.getAllperson())
    
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
