/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Adds a login or logout link to the page, depending on whether the user is
 * already logged in.
 */
function addLoginOrLogoutLinkToNavigation() {
  fetch('/login-status')
      .then((response) => {
        return response.json();
      })
      .then((loginStatus) => {
        if (loginStatus.isLoggedIn) {
          indexVue.pages.push({ title: 'Your Page',url:'/user-page.html?user=' + loginStatus.username});
          //vue.pages.push({ title: 'Your Page',url:'/user-page.html?user=' + loginStatus.username});
          indexVue.pages.push({ title: 'Logout',url:'/logout'});
          //vue.pages.push({ title: 'Logout',url:'/logout'});
        } else {
          indexVue.pages.push({ title: 'Login',url:'/login'});
          //vue.pages.push({ title: 'Login',url:'/login'});
        }
      });
}

function getUserNameEmail(){
    fetch('/searchBar')
        .then(response => response.text())
        .then((results) => {
            data = JSON.parse(results);
            for (let i=0; i < data.length; i++){
                let obj = data[i];
                indexVue.userData.push(obj);
            }
        });
}