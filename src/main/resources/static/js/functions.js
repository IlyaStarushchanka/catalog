function getCurrentUser() {
    $.ajax({
        type : "GET",
        url : "/admin/currentUserName",
        success : function(response) {
            var currentUser = document
                .getElementById('currentUser');
            if (response) {
                currentUser.innerHTML = " " + response;
            }
        },
        error : function(e) {
            alert(e);
        }
    });
}

function removeFile(caller) {
    var clickedBtnID = $(caller).attr('id');
    var fileId = document
        .getElementById('fileId_' + clickedBtnID);

    $.ajax({
        type : "GET",
        url : "/admin/files/delete?id="+clickedBtnID,
        success : function(response) {
            fileId.parentElement.remove()
        },
        error : function(e) {

        }
    });
}

function addFreeTonAddress(){
    var additionalInput = "<input  type=\"text\" name=\"freeTonAddresses\" class=\"form-control input-rounded\" id=\"freeTonAddresses\">";
    var div = document.getElementsByClassName("freeTonAddress");
    $(div).append(additionalInput);
}