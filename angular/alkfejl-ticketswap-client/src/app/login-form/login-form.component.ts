import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  message: string;
  hidePassword = true;

  form = this.fb.group({
    username: ['', [ Validators.required ]],
    password: ['', [ Validators.required ]],
  });

  get username() { return this.form.get('username'); }
  get password() { return this.form.get('password'); }

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
  }

  async onSubmit() {
    try {
      this.message = null;
      await this.authService.login(this.username.value, this.password.value);
      if (this.authService.redirectUrl) {
        this.router.navigate([this.authService.redirectUrl]);
      } else {
        this.router.navigate(['/']);
      }
    } catch (e) {
      this.message = 'Cannot log in!';
    }
  }

}
