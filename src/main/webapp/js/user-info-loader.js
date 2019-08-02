function saveUser(name, email,aboutme,learncategory,sharecategory,skilllevel,school,age){
    UserService userService = UserServiceFactory.getUserService();
    String email = userService.getCurrentUser().getEmail();
    let user = new Object();
    user.name = name;
    user.email = email;
    user.aboutMe = aboutme;
    user.learnCategory = learncategory;
    user.teachCategory = sharecategory;
    user.skillLevel =skilllevel;
    user.school = school;
    user.age = age;
    return user;
}

function submitData(newUser) {
    const params = new URLSearchParams();
    params.append('userData', JSON.stringify(newUser));
    const resultContainer = document.getElementById('result');
    fetch('/user-info', {
        method: 'POST',
        body: params,
        redirect: 'follow'
    }).then(response => response.json())
     .then((results) => {
          //redirect back to personal page
          newUser.email = results.userEmail;
          window.location.href = "/user-page.html?user=" + newUser.email;
      });
}

