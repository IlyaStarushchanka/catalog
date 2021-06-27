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

var lastSelectedOption = null;

AuthorChange = function(author) {
    //Blur after change so that clicking again without
    //losing focus re-triggers onfocus.
    author.blur();

    //The rest is whatever you want in the change.
    var tcs = $("span.on_change_times");
    tcs.html(+tcs.html() + 1);
    createSelectItem();
    $("span.selected_index").html(author.prop("selectedIndex"));
    return false;
};

function createSelectItem(){
    var myParent = document.body;

//Create array of options to be added
    var array = ["Volvo","Saab","Mercades","Audi"];

//Create and append select list
    var selectList = document.createElement("select");
    selectList.id = "mySelect";
    myParent.html(selectList);

//Create and append the options
    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        option.value = array[i];
        option.text = array[i];
        selectList.appendChild(option);
    }
}

DDFocus = function(Dd) {
    lastSelectedOption = Dd.prop("selectedIndex");
    Dd.prop("selectedIndex", -1);

    $("span.selected_index").html(Dd.prop("selectedIndex"));
    return false;
};

//On blur, set it back to the value before they clicked
//away without selecting an option.
//
//This is what is typically weird for the user since they
//might click on the dropdown to look at other options,
//realize they didn't what to change anything, and
//click off the dropdown.
DDBlur = function(Dd) {
    if (Dd.prop("selectedIndex") === -1)
        Dd.prop("selectedIndex", lastSelectedOption);

    $("span.selected_index").html(Dd.prop("selectedIndex"));
    return false;
};