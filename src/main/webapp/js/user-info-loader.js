function saveUser(name, email,aboutme,learncategory,sharecategory,skilllevel,school,age){
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
    //newUser = saveTestUser();
    const params = new URLSearchParams();
    params.append('userData', JSON.stringify(newUser));
    const resultContainer = document.getElementById('result');
    fetch('/user-info', {
        method: 'POST',
        body: params,
        redirect: 'follow'
    }).then(response => response.text())
     .then((results) => {
          //redirect back to personal page
          //resultContainer.innerText = results;
          window.location.href = "/user-page.html?user=" + newUser.email;
      });
}