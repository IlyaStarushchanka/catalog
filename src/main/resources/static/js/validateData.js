function checkLogin() {

	var userName = $('#nickName').val();

	$.ajax({
		type : "POST",
		url : "/admin/managers/checkUserName",
		data : "userName=" + userName,
		success : function(response) {
			var button = document.getElementsByName("auth");
			var loginMessageDiv = document
					.getElementById('loginexist-error-message');
			if (response) {
				button[0].disabled = true;
				loginMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">The username is already in use</div>";
			} else {
				button[0].disabled = false;
				loginMessageDiv.innerHTML = "";
			}
		},
		error : function(e) {
			alert(e);
		}
	});
}

function validateCourseName(nameValue) {
	var regexpName = /^((([A-Za-z]{1,16}\s*)|([А-Яа-я]{1,16})\s*)){1,6}$/;
	var rightName = true;
	nameMessageDiv = document.getElementById('coursename-error-message');
	if (!regexpName.test(nameValue)) {
		nameMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Название введено некорректно</div>";
		rightName = false;
	} else {
		nameMessageDiv.innerHTML = "";
	}
	return rightName;
}

function validateData() {
	var right = true;
	var password = document.getElementsByName("password");
	if (password.length > 0) {
		var passwordValue = password[0].value;
		if (!validatePassword(passwordValue)) {
			right = false;
		} else {
			var repeatPassword = document
					.getElementsByName("repeatPassword");
			if (repeatPassword.length > 0) {
				var repeatPasswordValue = repeatPassword[0].value;
				if (!validateRepeatPassword(passwordValue, repeatPasswordValue)) {
					right = false;
				}
			}
		}
	}
	return right;
}


function validatePassword(passwordValue) {
	var regexpPassword = /^[\w.,$!]{6,20}$/;
	var rightPassword = true;
	passwordMessageDiv = document.getElementById('password-error-message');
	if (!regexpPassword.test(passwordValue)) {
		passwordMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Password is not correct. It has to have at least 6 symbols</div>";
		rightPassword = false;
	} else {
		passwordMessageDiv.innerHTML = "";
	}
	return rightPassword;
}

function validateRepeatPassword(password, repeatPassword) {
	var rightPassword = true;
	repeatpasswordMessageDiv = document
			.getElementById('repeatpassword-error-message');
	if (password !== repeatPassword) {
		repeatpasswordMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Repeat password is not correct</div>";
		rightPassword = false;
	} else {
		repeatpasswordMessageDiv.innerHTML = "";
	}
	return rightPassword;
}