import { Injectable, Pipe } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as jwt_decode from "jwt-decode";
import { Person } from './model/person';
import { Address } from './model/address';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  private url = "http://localhost:8080/api/";
  isLoggedIn= null;
  user_role: null;
  decoded_token: any = null;
  constructor(private httpClient: HttpClient) { }

  //person -
  getAllPerson(): Observable<Person[]> {
    return this.httpClient.get<Person[]>(this.url + 'person');
  }

  addPerson(person) {
    return this.httpClient.post(this.url + 'add_person', person)
    // .pipe(map((response: Response) => response.json()))
  }

  //address -
  getAllAddress(): Observable<Address[]> {
    return this.httpClient.get<Address[]>(this.url + 'allAddress');
  }

  addAddress(address): Observable<any> {
    return this.httpClient.post(this.url + 'add_address', address);
  }
  signIn(user): Observable<any> {
    return this.httpClient
      .post<any>(this.url + "authenticate", user)
      .pipe(
        map((res) => {
          if (res.jwt) {
            localStorage.setItem("token", res.jwt );
            this.getDecodedAccessToken(res.jwt);
            return res;
          }
        })
      );
  }

  getDecodedAccessToken(token: string): any {
    try {
      // localStorage.getItem("token")
      let dToken = jwt_decode(token);
      if(dToken) {
      this.user_role = dToken.scopes[0].authority;
      this.isLoggedIn = true;
    }
    } catch (Error) {
      return null;
    }
  }
}
