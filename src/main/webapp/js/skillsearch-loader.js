let testUsers = [];

function saveTestUser(email,aboutme,learncategory,sharecategory,skilllevel,school,age,gender){
    let user = new Object();
    user.email = email;
    user.aboutme = aboutme;
    console.log(aboutme);
    user.learnCategory = learncategory;
    user.teachCategory = sharecategory;
    user.skillLevel =skilllevel;
    user.school = school;
    user.age = age;
    user.gender = gender;
    return user;
}


function getSkillSharer(skill) {
    console.log("getSkillSharer");
    //const skill = document.getElementById('text').value;
    console.log(skill);
    const params = new URLSearchParams();
    params.append('skill', skill);
    params.append('testUsers', JSON.stringify(testUsers));
    const resultContainer = document.getElementById('result');
    fetch('/sksrch', {
        method: 'POST',
        body: params})
        .then(response => response.text())
        .then((results) => {
            let userobj = JSON.parse(results);

            schoolMap.clear();
            for(u in userobj)
            {
                if(schoolMap.has(userobj[u].school))
                {
                   schoolMap.set(userobj[u].school,schoolMap.get(userobj[u].school)+1);
                }
                else
                    schoolMap.set(userobj[u].school,1);
            }
           /* for (var [key, value] of schoolMap.entries()) {
                console.log(key + ' = ' + value);
            }*/
            drawGeoChart();
        });
}

