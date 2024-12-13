function popupWindow(action, attr) {

	var newWin = window.open(action, 'popWindow', attr+',location=no,resizable=yes,scrollbars=yes,dependant=yes,titlebar=no,status=no');

	newWin.focus;

}



function popupWindow2(action, attr) {

	var newWin = window.open(action, 'popWindow2', attr+',location=no,resizable=yes,scrollbars=yes,dependant=yes,titlebar=no,status=no');

	newWin.focus;

}



function switchChildCheckboxes(pcheckboxes, flag) {

	checkboxes = eval("document.forms[0]."+pcheckboxes); 

	if (checkboxes.checked==true || checkboxes.checked==false)

		checkboxes.checked = flag;

	else {

		for (i=0;i<checkboxes.length;i++) {

			checkboxes[i].checked = flag;

		}

	}

}

