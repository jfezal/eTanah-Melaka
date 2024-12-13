function calculateAge(date, month, year) {

    today = new Date();
    dateStr = today.getDate();
    monthStr = today.getMonth()+1;
    yearStr = today.getFullYear();

    theYear = yearStr - year;
    theMonth = monthStr - month;
    theDate = dateStr - date;

    var days = "";
    var inYear = 0;
    var inMonth = 0;
    var inDay = 0;

    if (monthStr == 0 || monthStr == 2 || monthStr == 4 || monthStr == 6 || monthStr == 7 || monthStr == 9 || monthStr == 11) days = 31;
    if (monthStr == 3 || monthStr == 5 || monthStr == 8 || monthStr == 10) days = 30;
    if (monthStr == 1) days = 28;

    inYear = theYear;

    if (month < monthStr && date > dateStr) {
        inYear = inYear + 1;
        inMonth = theMonth - 1;
    }
    if (month < monthStr && date <= dateStr) {
        inMonth = theMonth;
    }
    else if (month == monthStr && (date < dateStr || date == dateStr)) {
        inMonth = 0;
    }
    else if (month == monthStr && date > dateStr) {
        inMonth = 11;
    }
    else if (month > monthStr && date <= dateStr) {
        inYear = inYear - 1;
        inMonth = 12  - theMonth + 1;
    }
    else if (month > monthStr && date > dateStr) {
        inMonth = 12 - theMonth;
    }
    if (inMonth== '12') {
        inMonth=0;
        inYear = inYear + 1;
    }
    if (date < dateStr) {
        inDay = theDate;
    }
    else if (date == dateStr) {
        inDay = 0;
    }
    else {
        inYear = inYear - 1;
        inDay = days - theDate;
    }
    return inYear;
}