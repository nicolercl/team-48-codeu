function saveUser(name, email,aboutme,learncategory,sharecategory,skilllevel,school,age,pic, url){
    let user = new Object();
    user.name = name;
    user.email = email;
    user.aboutMe = aboutme;
    user.learnCategory = learncategory;
    user.teachCategory = sharecategory;
    user.skillLevel =skilllevel;
    user.school = school;
    user.age = age;
    user.pic = pic;
    user.url = url;
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
          window.location.href = "/user-page.html?user=" + results.userEmail;
      });
}
