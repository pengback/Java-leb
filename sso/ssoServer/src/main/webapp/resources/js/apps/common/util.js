define(['jquery'], function($) {
    var util = {
        testInfo: function() {
            return "This info come from dao";
        },
        ajax: {
            get: function(url, data, type) {
                url = CONTEXTPATH + url;
                 return $.ajax({
                    url: url,
                    type: 'GET',
                    cache: false,
                    data: data,
                    dataType: type || 'json'
                });
            },
            post: function(url, data, type) {
                url = CONTEXTPATH + url;
                return $.ajax({
                    url: url,
                    type: 'POST',
                    cache: false,
                    data: data,
                    dataType: type || 'json'
                });
            }
        },

        /**
         * dao链式调用所用对象
         */
        ajaxObj : function() {
            var obj = {};

            obj.done = function(callback) {
                if (callback) this._done = callback;
                return this;
            };

            obj.fail = function(callback) {
                if (callback) this._fail = callback;
                return this;
            };

            obj.empty = function(callback) {
                if (callback) this._empty = callback;
                return this;
            };

            obj.options = {
                before: function() {},
                after: function() {}
            };

            return obj;
        },

        toObj: function(json) {
            var data = json;
            if (typeof(data) == "string" &&
                (data.indexOf('{') == 0 || data.indexOf('[') == 0)) {
                data = eval('(' + data + ')');
            }
            return data;
        }
    };

    return util;
});