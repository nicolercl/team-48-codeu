//for the tool bar
    var indexVue = new Vue({
         el: '#app',
         data: () => ({
         Home:'/',
             pages: [
             { title: 'My-info', url: '/user-info.html' },
             { title: 'Public Feed', url: '/feed.html' },
             { title: 'Skill Search',url:'/skill-search.html'}
           ],
           search: null,
           select: null,
           userData: [],
         }),
         methods: {

           customFilter (item, queryText, itemText) {
               const name = item.name.toLowerCase()
               const email = item.email.toLowerCase()
               const searchText = queryText.toLowerCase()

               return name.indexOf(searchText) > -1 ||
                 email.indexOf(searchText) > -1
             },
           redirectPersonalPage(){
               window.location.href = "/user-page.html?user=" + this.select
           },
         },
         mounted(){
       	getUserNameEmail();
         }
       })
