require.config(requireConfig);
require(['jquery', 'Vue', 'commonUtil', 'commonDao', 'md5'], function ($, Vue, Util, Dao, Md5) {

    var app = new Vue({
        el: '#loginBox',
        data: {
            loginName: '',
            passwd: '',
        },
        methods: {
            doLogin: function (event) {
                debugger;
                $.ajaxSetup({
                    //设置ajax请求结束后的执行动作
                    complete: function (XMLHttpRequest, textStatus) {
                        debugger;
                        // 通过XMLHttpRequest取得响应头，REDIRECT
                        var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向
                        if (redirect == "REDIRECT") {
                            var win = window;
                            while (win != win.top) {
                                win = win.top;
                            }
                            //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
                            // layerAlert(layer, "您的登录信息已超时，请重新登录<p class='ps'>ps:在客户端闲置10分钟后系统会自动登出</p>", '登录超时提示', 5, '', 0, function () {
                            win.location.href = XMLHttpRequest.getResponseHeader("CONTEXTPATH");
                            // })
                        }
                    },
                    type: 'POST'
                });
                var data = {
                    loginName: this.loginName,
                    passwd: Md5(this.passwd),
                    redirectUrl: $('#redirectUrl').val(),
                };
                // Dao.post('/sso/login', data, '')

                $.ajax({
                    url: CONTEXTPATH + '/sso/login',
                    type: 'POST',
                    cache: false,
                    data: data,
                    dataType: 'json',
                }).done(function (d) {
                    d = Util.toObj(d);
                    debugger;
                    if (d.returnFlag) {
                        alert('登录成功!!!' + d.data);
                    } else {
                        alert('登录失败!!!' + d.data);
                    }

                }).fail(function (data) {
                    debugger;
                    console.info('error');
                });

            },
            regist: function (event) {

            }
        }
    });
});