import { Injectable } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Rx';


@Injectable()
export class myService {

    constructor(private http: Http) { }
    findUrl = 'http://192.168.43.179:8080/getPathByCoordinates/-2/-3/Noodles';
    private extractJSON(res: any) {
        return res.json() || {};
    }

    //if service throws error
    private handleError(error: any) {
        let errMsg = (error.message) ? error.message :
            error.status ? `${error.status} - ${error.statusText}` : 'Server error';
        console.error(errMsg); // log to console instead
        return Observable.throw(errMsg);
    }

    getList(): Observable<any> {

        return this.http.get(this.findUrl)
            .map(this.extractJSON)
            .catch(this.handleError);
    }
}