/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function doUpperCase(id){
    var val = $('#'+id).val().toUpperCase();
    $('#' + id).val(val);
}

/*
 * 
 */

function doValidateAge(ic, id, id2,type){
    var valid = true;
    var v = $('#'+id2).val();    
    if(v == 'B'){
        date = ic.substring(0,6);
        yyyy = date.substring(0,2);
        mm = date.substring(2,4);
        dd = date.substring(4,6);
        if(yyyy < 99 && yyyy > 30){//fixme :
            yyyy = "19" + yyyy;
        }else {
            yyyy = "20" + yyyy;
        }

        //alert(yyyy + ',' + mm + ','+ dd);

        days = new Date();
        gdate = days.getDate();
        gmonth = days.getMonth();
        gyear = days.getFullYear();
        age=gyear-yyyy;
        //alert(gyear + ',' + yyyy + ',' + days.getFullYear());
        if((mm==(gmonth+1))&&(dd<=parseInt(gdate))) {
            age=age
        } else {
            if(mm<=(gmonth)) {
                age=age
            } else {
                age=age-1
            }
        }
        if(type != 'WAR'){
            
            if(age < 18){
                alert('Umur Tidak mencukupi. Mesti 18 tahun dan keatas.');
                $('#'+id).val('');
                $('#'+id).focus();
                valid = false;
            }
        }
//        else{
//            if(age > 18){
//                alert('Umur Penerima Mesti 18 tahun dan kebawah.');
//                $('#'+id).val('');
//                $('#'+id).focus();
//                valid = false;
//            }
//        }
    }    
    return valid;
}

