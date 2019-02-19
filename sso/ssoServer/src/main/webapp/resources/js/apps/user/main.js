
require.config(requireConfig);
require(['jquery', 'Vue'], function($, Vue) {

    var app = new Vue({
      el: '#box',
      data: {
        message: 'Welcome user main page'
      }
    });
});
