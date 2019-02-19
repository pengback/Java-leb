define(['jquery', 'commonUtil'], function ($, Util) {

    var dao = {
        post: function (url, data, type) {
            var obj = new Util.ajaxObj(),
                options = $.extend({}, obj.options);
            Util.ajax.post(url, data, type)
                .done(function (data) {
                    data = Util.toObj(data);
                    setTimeout(function () {
                        obj._done && obj._done(data);
                    }, 0);
                })
                .fail(function () {
                    setTimeout(function () {
                        obj._fail && obj._fail();
                    }, 0);
                });
            return obj;
        },
        get: function (url, data, type) {
            var obj = new Util.ajaxObj(),
                options = $.extend({}, obj.options);
            Util.ajax.get(url, data, type)
                .done(function (data) {
                    data = Util.toObj(data);
                    setTimeout(function () {
                        obj._done && obj._done(data);
                    }, 0);
                })
                .fail(function () {
                    setTimeout(function () {
                        obj._fail && obj._fail();
                    }, 0);
                });
            return obj;
        }
    };
    return dao;
});