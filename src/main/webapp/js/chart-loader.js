/*draw chart function*/
google.charts.load('current', {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart(){
  fetch("/webchart")
      .then((response) => {
        return response.json();
      })
      .then((bothJson) => {
        var learnData = new google.visualization.DataTable();
        var shareData = new google.visualization.DataTable();
        //define columns for the DataTable instance
        learnData.addColumn('string', 'Skill Interested');
        learnData.addColumn('number', 'Number of Users');
        shareData.addColumn('string', 'Skill to Share');
        shareData.addColumn('number', 'Number of Users');
        learnJson = bothJson["learn"];
        shareJson = bothJson["share"];
        for (i = 0; i < learnJson.length; i++) {
          learnRow = [];
          var skill = learnJson[i].skill;
          var number = learnJson[i].number;
          learnRow.push(skill, number);
          learnData.addRow(learnRow);
          shareRow = [];
          skill = shareJson[i].skill;
          number = shareJson[i].number;
          shareRow.push(skill, number);
          shareData.addRow(shareRow);
       }
       var learnOptions = {
           width: 500,
           height: 300
       };
       var shareOptions = {
           width: 500,
           height: 300
       };
       var learnChart = new google.visualization.PieChart(document.getElementById('learn_chart'));
       learnChart.draw(learnData, learnOptions);
       var shareChart = new google.visualization.PieChart(document.getElementById('share_chart'));
       shareChart.draw(shareData, shareOptions);
   })
}