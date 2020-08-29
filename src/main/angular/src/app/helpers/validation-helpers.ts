import { AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { Observable, timer } from 'rxjs';
import { UserService } from '../services/user.service';
import { map, switchMap } from 'rxjs/operators';
import { IResponse } from '../interfaces/response';


export function validateName(userService: UserService): AsyncValidatorFn {
  return (control: AbstractControl): Observable<ValidationErrors | null> => {
    return timer(1000).pipe(switchMap(() => userService.isNameExists(control.value)),
      map((response) => {
        let r = response as unknown as IResponse;
        return r.fields.exists ? { usernameExists: true } : null;
      }));
  }
}

export function validIp(control: AbstractControl): ValidationErrors | null {
  const value: string = control.value || '';
  const valid = value.match('^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$');
  return valid ? null : { validIp: true };
}

export function onlyNumber(control: AbstractControl): ValidationErrors | null {
  if (control.value === null) return null;
  if (isNaN(control.value)) {
      return { onlyNumber: true };
  }

  return null;
}


