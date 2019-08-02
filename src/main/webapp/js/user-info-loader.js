function saveUser(name, email,aboutme,learncategory,sharecategory,skilllevel,school,age,actUrl,picUrl){
    let user = new Object();
    user.name = name;
    user.email = email;
    user.aboutMe = aboutme;
    user.learnCategory = learncategory;
    user.teachCategory = sharecategory;
    user.skillLevel =skilllevel;
    user.school = school;
    user.age = age;
    user.actUrl = actUrl;
    user.picUrl = picUrl;
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

function fetchProfilePicUrl() {
  fetch('/profilepic-upload-url')
    .then((response) => {
      return response.text();
    })
    .then((profilePicUploadUrl) => {
      //console.log(profilePicUploadUrl)

      // const profilepicForm = document.getElementById('profilepic-form');
      // profilepicForm.action = profilePicUploadUrl;
      // profilepicForm.classList.remove('hidden');
    });
}
