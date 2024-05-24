import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Join } from 'src/app/Model/Join';
import { UserRespo } from 'src/app/Model/UserRespo';
import { GroupService } from 'src/app/Services/Group/group.service';
import { JoinService } from 'src/app/Services/Join/join.service';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-list-group',
  templateUrl: './list-group.component.html',
  styleUrls: ['./list-group.component.css']
})
export class ListGroupComponent implements OnInit{
  ListGroup: any;
  showPopup = false;
  id_group: number = 0;

  join: Join ={
    user_id : 0,

    group_id : 0,
  }

  user: UserRespo | any= {
    firstname : '',

    lastname : '',

    phone : '',

    email: '',

    username: '',

    role : ''
  }

  constructor(private groupService: GroupService,
    private userService: UserService,
    private joinService: JoinService,
    private router: Router,
    private toast: NgToastService ){}

  ngOnInit(){
    this.loadGroup();

  }

  loadGroup(): void{
    this.groupService.findAll().subscribe({
      next : (value) => {
        console.log('Retrieved value' + value);
        this.ListGroup = value
        console.log(this.ListGroup);

      },
      error : (error) => {
        alert("Error while retrieving data!");
        console.log('Error' + error);
      }

    });
  }


  saveJoin(id:number){
    const userJson = this.userService.getUserFromLocalStorage()
    console.log(userJson);
    console.log(id);


    if (userJson && userJson.uid !== undefined) {
      this.join.user_id = userJson.uid;
      this.join.group_id = id;
      console.log(this.join);

      this.joinService.save(this.join).subscribe({
        next: (res: Join) => {
          this.toast.success({detail:"SUCCESS",summary:'Your reservation has been saved successfully',duration:3000});
          console.log(res, ' response');


        },
        error: (err: any) => {
          // this.error = err.error;
          // console.log(err.error.errors, 'errors');
        },
      });
      this.loadGroup();



    } else {
      this.toast.success({detail:"WARN",summary:'Please login first',duration:5000});
      this.router.navigateByUrl('/login')
    }


    this.closePopup();

  }

  togglePopup(id:number, nbrP: number) {
    const userJson = this.userService.getUserFromLocalStorage()
    console.log(userJson);

    if (userJson && userJson.uid !== undefined) {
      if(nbrP > 0){
        this.showPopup = !this.showPopup;
        console.log(id);
        this.id_group = id;
      }else{
        alert("All seats are full")
      }
    } else {
      this.toast.success({detail:"WARN",summary:'Please login first',duration:5000});
      this.router.navigateByUrl('/login')
    }


  }

  closePopup() {
    this.showPopup = false;
  }
}
