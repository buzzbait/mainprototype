/**
 * 
 */


function callAjax(type,url,jsonData, callback){
	$.ajax({
        type: type,
        contentType: "application/json",
        url: url,
        data: jsonData,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
        	callback(data);
        },
        error: function (e) {
        	//공통오류 처리
        	console.log(e);
        }
    });
	
}