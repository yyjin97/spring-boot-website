console.log("Reply Module..............");

const replyService = (function () {

    function add(reply, callback, error) {

        $.ajax({
            type: 'post',
            url: '/replies/new',
            data: JSON.stringify(reply),
            contentType: 'application/json; charset=utf-8',
            success: function (result, status, xhr) {
                if(callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, err) {
                if(error) {
                    error(err);
                }
            }
        })
    }

    function update(reply, callback, error) {

        $.ajax({
            type: 'put',
            url: '/replies/' + reply.rno,
            data: JSON.stringify(reply),
            contentType: 'application/json; charset=utf-8',
            success: function (result, status, xhr) {
                if(callback)
                    callback(result);
            },
            error: function (xhr, status, err) {
                if(error)
                    error(err);
            }
        })
    }

    function get(rno, callback, error) {

        $.getJSON("/replies/" + rno, function (result) {
            if(callback)
                callback(result);
        }).fail(function (xhr, status, err) {
            if(error)
                error();
        })
    }

    function getList(param, callback, error) {
        const bno = param.bno;
        const page = param.page || 1;

        $.getJSON("/replies/pages/" + bno + "/" + page,
            function (data) {
                if(callback)
                    callback(data);
            }).fail(function (xhr, status, err) {
                if(error)
                    error();
        });
    }

    function remove(rno, callback, error) {
        $.ajax({
            type: 'delete',
            url: '/replies/' + rno,
            success: function (deleteResult, status, xhr) {
                if(callback)
                    callback(deleteResult);
            },
            error: function (xhr, status, err) {
                if(error)
                    error(err);
            }
        })
    }
    return{
        add:add,
        update:update,
        get:get,
        getList:getList,
        remove:remove
    };
})();