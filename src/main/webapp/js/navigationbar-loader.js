//for the tool bar
    new Vue({ el: '#app',
     data: () => ({
        items: [
          { title: 'Home', icon: 'dashboard', url: '/' },
          { title: 'My-info', icon: 'edit', url: '/user-info.html' },
          { title: 'Public Feed', icon: 'question_answer', url: '/feed.html' }
        ],
        drawer: {
            open : false,
        }
      }),
      methods: {
        toggleDrawer(){
            this.drawer.open = !this.drawer.open
        }
      }
    })
