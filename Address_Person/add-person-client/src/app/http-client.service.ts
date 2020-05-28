import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { Person } from './model/person';
import { Address } from './model/address';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  private url = "http://localhost:8080/api/"

  constructor(private httpClient: HttpClient) { }

  //person -
  getAllPerson(): Observable<Person[]> {
    return this.httpClient.get<Person[]>(this.url + 'person');
  }

  addPerson(person) {
    return this.httpClient.post(this.url + 'add_person', person);
  }

  //address -
  getAllAddress(): Observable<Address[]> {
    return this.httpClient.get<Address[]>(this.url + 'allAddress');
  }

  addAddress(address): Observable<any> {
    return this.httpClient.post(this.url + 'add_address', address);
  }
}
