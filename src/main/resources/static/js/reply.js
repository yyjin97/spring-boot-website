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
                    callback(data.replyCnt, data.list);
            }).fail(function (xhr, status, err) {
                if(error)
                    error();
        });
    }

    function remove(rno, replyer, callback, error) {
        $.ajax({
            type: 'delete',
            url: '/replies/' + rno,
            data: JSON.stringify({rno:rno, replyer:replyer}),
            contentType: "application/json; charset=utf-8",
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

    function displayTime(timeValue){
        const now = new Date();
        const dateObj = new Date(timeValue);
        const gap = now.getTime() - dateObj.getTime();

        if(gap < (1000 * 60 * 60 * 24)) {
            const hh = dateObj.getHours();
            const mi = dateObj.getMinutes();
            const ss = dateObj.getSeconds();

            return [ (hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ":" , (ss > 9 ? '' : '0') + ss ].join('');
        } else {
            const yy = dateObj.getFullYear();
            const mm = dateObj.getMonth() + 1;
            const dd = dateObj.getDay();

            return [ yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd ].join('');
        }
    }

    return{
        add:add,
        update:update,
        get:get,
        getList:getList,
        remove:remove,
        displayTime:displayTime
    };
})();