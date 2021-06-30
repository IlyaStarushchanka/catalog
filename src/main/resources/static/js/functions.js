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

$('#author-select').on('change', function() {
    var address =  this.options[this.selectedIndex].id.replace('authorAddress_','');
    var addressInput = document.getElementById("author.chosedFreeTonAddress");
    addressInput.value = address;
});

function beforeSave(){
    var smallDescription = document.getElementById("smallDescription");
    smallDescription.value = document.querySelector('#editorsmall').children[0].innerHTML;
    var bigDescription = document.getElementById("bigDescription");
    bigDescription.value = document.querySelector('#editorbig').children[0].innerHTML;
}

$("#filterForm").submit(function(e) {

    /*e.preventDefault(); // avoid to execute the actual submit of the form.

    var form = $(this);
    var url = form.attr('action');*/


    $(this)
        .find('input[name]')
        .filter(function () {
            return !this.value;
        })
        .prop('name', '');
});
    /*$.ajax({
        type: "POST",
        url: url,
        data: form.serialize(), // serializes the form's elements.
        success: function(data)
        {
            alert(data); // show response from the php script.
        }
    });*/

