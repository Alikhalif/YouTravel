import { CanActivateFn, Router } from '@angular/router';
import { UserService } from '../Services/User/user.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const userService: UserService = inject(UserService);
  const router:Router = inject(Router);

  const {roles} = route.data;

  if(roles.includes(userService.getRole())){
    return true;
  }else{
    router.navigate(["/auth/login"]);
    return false;

  }
};
