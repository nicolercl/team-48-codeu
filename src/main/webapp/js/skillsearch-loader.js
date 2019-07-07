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
        vue.nextisdisable = true;
        vue.isdisable = false;
     }
    else
        vue.nextisdisable = false;
    if(index==0)
        vue.previsdisable = true;
    else
        vue.previsdisable = false;

    vue.card_title="information    "+(index+1)+"/"+carddatanum;
    vue.card_shareskill=infobyschool[index].teachCategory;
    vue.card_skilllevel=infobyschool[index].skillLevel;
    vue.card_email=infobyschool[index].email;
    vue.card_school=infobyschool[index].school;
    vue.card_age=infobyschool[index].age;
    vue.card_name=infobyschool[index].name;
    vue.card_aboutme=infobyschool[index].aboutMe;
}



