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

function validateOldPassword() {
	var password = $('#passwordOld').val();

	$
			.ajax({
				type : "POST",
				url : "checkOldPassword",
				data : "password=" + password,
				success : function(response) {
					var button = document.getElementsByName("auth");
					var passwordMessageDiv = document
							.getElementById('oldpassword-error-message');
					if (!response) {
						button[0].disabled = true;
						passwordMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Неверный пароль</div>";
					} else {
						button[0].disabled = false;
						passwordMessageDiv.innerHTML = "";
					}
				},
				error : function(e) {
					alert(e);
				}
			});
}

function validateQuestionCount(){
	
	var right = true;
	var count = document.getElementsByName("questionCount");
	var countValue = count[0].value;
	if (countValue >=5 && countValue <= 10){
		var dateMessageDiv = document.getElementById('questioncount-error-message');
		dateMessageDiv.innerHTML = "";
		return true;
	} else {
		var dateMessageDiv = document.getElementById('questioncount-error-message');
		dateMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Количество вопросов введено некорректно</div>";
		return false;
	}
	
}

function validateCourse() {
	var right = true;
	var name = document.getElementsByName("nameCourse");
	var nameValue = name[0].value;
	if (!validateCourseName(nameValue)) {
		right = false;
	}
	var start = document.getElementsByName("startDate");
	var end = document.getElementsByName("endDate");
	var startDate = new Date(start[0].value);
	var endDate = new Date(end[0].value);
	if ((startDate >= endDate) || (startDate == "Invalid Date")
			|| (endDate == "Invalid Date")) {
		var dateMessageDiv = document.getElementById('date-error-message');
		dateMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Дата выбрана некорректно</div>";
		right = false;
	} else {
		var dateMessageDiv = document.getElementById('date-error-message');
		dateMessageDiv.innerHTML = "";
	}
	return right;
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

function validateStudentNote() {
	var right = true;
	var name = document.getElementsByName("nameCourse");
	var nameValue = name[0].value;
	if (!validateCourseName(nameValue)) {
		right = false;
	}
	var start = document.getElementsByName("startDate");
	var end = document.getElementsByName("endDate");
	var startDate = new Date(start[0].value);
	var endDate = new Date(end[0].value);
	if ((startDate >= endDate) || (startDate == "Invalid Date")
			|| (endDate == "Invalid Date")) {
		var dateMessageDiv = document.getElementById('date-error-message');
		dateMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Дата выбрана некорректно</div>";
		right = false;
	} else {
		var dateMessageDiv = document.getElementById('date-error-message');
		dateMessageDiv.innerHTML = "";
	}
	return right;
}

function validateNote(noteValue) {
	var regexpNote = /^(10)|[1-9]$/;
	var rightNote = true;
	noteMessageDiv = document.getElementById('note-error-message');
	if (!regexpNote.test(noteValue)) {
		noteMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Оценка введена некорректно</div>";
		rightNote = false;
	} else {
		noteMessageDiv.innerHTML = "";
	}
	return rightNote;
}

function validateData() {
	var right = true;
	var name = document.getElementsByName("name");
	if (name.length > 0) {
		var nameValue = name[0].value;
		if (!validateName(nameValue)) {
			right = false;
		}
	}
	var surname = document.getElementsByName("surname");
	if (surname.length > 0) {
		var surnameValue = surname[0].value;
		if (!validateSurname(surnameValue)) {
			right = false;
		}
	}
	var salary = document.getElementsByName("salary");
	if (salary.length > 0) {
		var salaryValue = salary[0].value;
		if (!validateSalary(salaryValue)) {
			right = false;
		}
	}
	var login = document.getElementsByName("loginPerson");
	if (login.length > 0) {
		var loginValue = login[0].value;
		if (!validateLogin(loginValue)) {
			right = false;
		}
	}
	var email = document.getElementsByName("email");
	if (email.length > 0) {
		var emailValue = email[0].value;
		if (!validateEmail(emailValue)) {
			right = false;
		}
	}

	var phone = document.getElementsByName("phoneNumber");
	if (phone.length > 0) {
		var phoneValue = phone[0].value;
		if (!validatePhone(phoneValue)) {
			right = false;
		}
	}
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

function validateName(nameValue) {
	var regexpName = /^([A-Z][a-z]{1,16})|([А-Я][а-я]{1,16})$/;
	var rightName = true;
	nameMessageDiv = document.getElementById('name-error-message');
	if (!regexpName.test(nameValue)) {
		nameMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Имя введено некорректно</div>";
		rightName = false;
	} else {
		nameMessageDiv.innerHTML = "";
	}
	return rightName;
}

function validateSurname(surnameValue) {
	var regexpSurname = /^([A-Z][a-z]{1,16})|([А-Я][а-я]{1,16})$/;
	var rightSurname = true;
	surnameMessageDiv = document.getElementById('surname-error-message');
	if (!regexpSurname.test(surnameValue)) {
		surnameMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Фамилия введена некорректно</div>";
		rightSurname = false;
	} else {
		surnameMessageDiv.innerHTML = "";
	}
	return rightSurname;
}

function validateSalary(salaryValue) {
	var regexpSalary = /^\d{3,5}[$]$/;
	var rightSalary = true;
	salaryMessageDiv = document.getElementById('salary-error-message');
	if (!regexpSalary.test(salaryValue)) {
		salaryMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Зароботная плата введена некорректно</div>";
		rightSalary = false;
	} else {
		salaryMessageDiv.innerHTML = "";
	}
	return rightSalary;
}

function validateLogin(loginValue) {
	var regexpLogin = /^[\d\w.-]{6,15}$/;
	var rightLogin = true;
	loginMessageDiv = document.getElementById('login-error-message');
	if (!regexpLogin.test(loginValue)) {
		loginMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Логин введен некорректно</div>";
		rightLogin = false;
	} else {
		loginMessageDiv.innerHTML = "";
	}
	return rightLogin;
}

function validateEmail(emailValue) {
	var regexpEmail = /^[A-Za-z\d.]{1,}@[a-z]{2,}.((ru)|(com)|(by))$/;
	var rightEmail = true;
	emailMessageDiv = document.getElementById('email-error-message');
	if (!regexpEmail.test(emailValue)) {
		emailMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Электронный адрес введен некорректно</div>";
		rightEmail = false;
	} else {
		emailMessageDiv.innerHTML = "";
	}
	return rightEmail;
}

function validatePhone(phoneValue) {
	var regexpPhone = /^(\+375)(29|44|33|25)\d{7}$/;
	var rightPhone = true;
	phoneMessageDiv = document.getElementById('phone-error-message');
	if (!regexpPhone.test(phoneValue)) {
		phoneMessageDiv.innerHTML = "<div class=\"alert alert-danger\" role=\"alert\">Номер телефона введен некорректно</div>";
		rightPhone = false;
	} else {
		phoneMessageDiv.innerHTML = "";
	}
	return rightPhone;
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