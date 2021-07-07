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

$(':checkbox').on('change', function(e){
    sendFilterRequest();
});

function showLoadingImage(loadingClass) {
    loadingClass.innerHTML = "<div id=\"loading-image\"><img src=\"/img/loading.gif\" alt=\"Loading...\" /></div>";
}

function hideLoadingImage() {
    $('#loading-image').remove();
}

/*var jq = jQuery.noConflict();
jq(function(){
    jq('#order').on('change',function(){
        alert('changing symbol');
    })});*/

var prizeFundFrom = document.getElementById('prizeFundFrom');
if (prizeFundFrom) {
    prizeFundFrom.addEventListener('blur', (event) => {
        sendFilterRequest();
    }, true)
}
var prizeFundTo = document.getElementById('prizeFundTo');
if(prizeFundTo) {
    prizeFundTo.addEventListener('blur', (event) => {
        sendFilterRequest();
    }, true);
}
var winnersFrom = document.getElementById('winnersFrom');
if(winnersFrom) {
    winnersFrom.addEventListener('blur', (event) => {
        sendFilterRequest();
    }, true);
}
var winnersTo = document.getElementById('winnersTo');
if (winnersTo) {
    winnersTo.addEventListener('blur', (event) => {
        sendFilterRequest();
    }, true);
}
function sendFilterRequest() {
    var urlParams = "?"
    if ($("#prizeFundFrom").val()){
        urlParams += "prizeFundFrom=" + $("#prizeFundFrom").val();
    }
    if ($("#prizeFundTo").val()){
        urlParams += "&prizeFundTo=" + $("#prizeFundTo").val();
    }
    if ($("#winnersFrom").val()){
        urlParams += "&winnersFrom=" + $("#winnersFrom").val();
    }
    if ($("#winnersTo").val()){
        urlParams += "&winnersTo=" + $("#winnersTo").val();
    }
    urlParams += "&order=" + $('#order').find(":selected").val();
    urlParams += "&search=" + encodeURIComponent($('#searchInput').val());
    $(":checkbox").each(function () {
        var ischecked = $(this).is(":checked");
        if (ischecked) {
            urlParams += "&subGovesIds=" + $(this).val();
        }
    });
    var loadingBar = document.getElementsByClassName("loading-class")[0];
    var catalog = document.getElementsByClassName("catalog__list")[0];
    showLoadingImage(loadingBar);
    $.ajax({
        type: "GET",
        url: "/contest/filter"+urlParams,
        dataType: "json",
        encode: true,
    }).done(function (data) {

        var catalogList = "";
        var resultCounter = document.getElementsByClassName("catalog__header-counter")[0];
        resultCounter.innerHTML = data.length + " results";
        jQuery.each(data, function(index, item) {
            catalogList += "<li class=\"catalog-item\" >";
            catalogList += "<a href=\"/contest?id=" + item.id + "\" class=\"catalog-item__wrap\">";
            catalogList += "<div class=\"catalog-item__name\">" + item.name +"</div>";
            catalogList += "<div class=\"catalog-item__sub\">" + item.subGovernance.name +"</div>";
            catalogList += "<div class=\"catalog-item__strong\">" + item.winnersCount +" winners</div>";
            catalogList += "<div class=\"catalog-item__sub\">" + item.prizeFund +" TONs prize fund</div>";
            catalogList += "<div class=\"catalog-item__interval\">" + item.submissionFrom + " - " + item.submissionTo +"</div></a>";
            catalogList += "</li>";
        });
        while (catalog.firstChild) {
            catalog.removeChild(catalog.lastChild);
        }
        hideLoadingImage();
        catalog.innerHTML = catalogList;
    });
}

/*$('#searchResult li').on('click', function(){
    $(this).closest('.js-search').find('.js-search-input').val($(this).text());
    $(this).closest('.js-search-list').slideUp(250);
});*/

function getSearchedContestName(){
    var urlParams = "?search=" + encodeURIComponent($("#searchInput").val());
    var searchResult = document.getElementById("searchResult");
    $.ajax({
        type: "GET",
        url: "/contest/names"+urlParams,
        dataType: "json",
        encode: true,
    }).done(function (data) {
        var searchItems = "";
        jQuery.each(data, function(index, item) {
            searchItems += "<li class=\"search__item\"><a href='/contest?id="+item.id + "'/>"  + item.name + "</li>";
        });
        while (searchResult.firstChild) {
            searchResult.removeChild(searchResult.lastChild);
        }
        searchResult.innerHTML = searchItems;
    });
}

$("#filterForm").submit(function (event) {
    event.preventDefault();
    sendFilterRequest();
});

$("#sortForm").submit(function (event) {
    event.preventDefault();
    sendFilterRequest();
});

$("#searchForm").submit(function (event) {
    event.preventDefault();
    $('.js-search-input').focusout();
    /*$(this).closest('.js-search-list').slideUp(250);*/
    sendFilterRequest();
});
