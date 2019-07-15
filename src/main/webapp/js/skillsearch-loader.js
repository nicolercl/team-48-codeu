let infobyschool = [];
var userobj;
function getSkillSharer(skill) {
    const params = new URLSearchParams();
    params.append('skill', skill);
    fetch('/sksrch', {
        method: 'POST',
        body: params})
        .then(response => response.text())
        .then((results) => {
            userobj = JSON.parse(results);

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
            drawGeoChart();
        });
}

function getinfobyschool(sch)
{
    infobyschool = [];
    for(u in userobj)
        {
            if(userobj[u].school===sch)
            {
                 infobyschool.push(userobj[u]);
            }
        }
}

function loadcardinfo()
{
     if(index==(carddatanum-1))
     {
        indexVue.nextisdisable = true;
        indexVue.isdisable = false;
     }
    else
         indexVue.nextisdisable = false;
    if(index==0)
         indexVue.previsdisable = true;
    else
         indexVue.previsdisable = false;

     indexVue.card_title="information    "+(index+1)+"/"+carddatanum;
     indexVue.card_shareskill=infobyschool[index].teachCategory;
     indexVue.card_skilllevel=infobyschool[index].skillLevel;
     indexVue.card_email=infobyschool[index].email;
     indexVue.card_school=infobyschool[index].school;
     indexVue.card_age=infobyschool[index].age;
     indexVue.card_name=infobyschool[index].name;
     indexVue.card_aboutme=infobyschool[index].aboutMe;
}



