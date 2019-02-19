
require.config(requireConfig);
require(['jquery', 'Vue', 'commonUtil'], function($, Vue, Util) {

    var app = new Vue({
      el: '#app',
      data: {
        message: 'Hello World'
      },
      methods: {
        
      }
    });
});
