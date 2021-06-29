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

function removeLink(caller) {
    var clickedBtnID = $(caller).attr('id');
    var linkId = document
        .getElementById('linkId_' + clickedBtnID);
    var submissionId = document
        .getElementById('linkSubmissionId');

    $.ajax({
        type : "GET",
        url : "/admin/submissions/link/delete?linkId="+clickedBtnID+"&submissionId="+$(submissionId).val(),
        success : function(response) {
            linkId.parentElement.remove()
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