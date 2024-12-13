/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var $dialog;
var myID;
var isHide = true;
function dialogInit(title){
    $dialog = $('<div id=dialog></div>')
    .html('loading')
    .dialog({
        autoOpen: false,
        title: '.:eTanah -' + title + ':.',
        resizable: false,
        //draggable: false,
        width:550
    });
}

function dialog(id, url){
    myID = id;
    $dialog.html('<div class="loader"></div>loading ...');    
    var position = $('#' + id).offset();
    cnTop = position.top;
    cnLeft = position.left;
    offsetHeight = document.getElementById(id).offsetHeight;
    cnTop +=offsetHeight;

    //$dialog.dialog('open');
    $dialog.dialog({
        position:[cnLeft,cnTop]
    });
    //var url = '${pageContext.request.contextPath}/common/carian_hakmilik?';   
    
    if(url == '' || url == undefined){
        alert('Parameter tidak cukup.');
        return false;
    }
    $dialog.dialog('open');
    isHide = false;
    $.ajax({
        url:url,
        success:function(data){
            //alert(data);
            $dialog.html(data);            
        },
        error:function(data){
            $dialog.html('Terdapat masalah teknikal pada sistem.');
        }
    });
    $('#'+id).focus();
    $(document).mousedown(function(event) {
        var $target = $(event.target);
        //alert($target.parents('.ui-dialog').length);
        //alert(!$target.is('#' + id));
            if(($target.parents(".ui-dialog").length == 0) && !$target.is('#' + id)){
                $dialog.dialog('close');
                isHide = true;
            }                
        });
    return false;
}

function copyToTextBox(val){
    $('#' + myID).val(val);
    $('#'+myID).focus();
     $('#'+myID).change();
    isHide = true;
    $dialog.dialog('close');
}

function closeDialog(){
    if(!isHide){
        isHide = true;
        $dialog.dialog('close');
    }
}
