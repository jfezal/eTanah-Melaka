<%-- 
    Document   : laporan_uam_param
    Created on : May 18, 2010, 5:02:51 PM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function ReplaceAll(Source,stringToFind,stringToReplace){
        var temp = Source;
        var index = temp.indexOf(stringToFind);
        while(index != -1){
            temp = temp.replace(stringToFind,stringToReplace);
            index = temp.indexOf(stringToFind);

        }
        return temp;
    }

      

    function doSubmit(f){
        var report = '${actionBean.reportName}';
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var form = $(f).formSerialize();
        var url = "${pageContext.request.contextPath}/uam/peranan?viewReport";
        $.ajax({
            type:"POST",
            url : url,
            data : form,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
                $.unblockUI();
            },
            success : function(data) {
                params  = 'width=1100';
                params += ', height=1000';
                params += ', top=200, left=100';
                params += ', directories=no';
                params += ', location=no';
                params += ', fullscreen=yes';
                params += ', menubar=no';
                params += ', resizable=no';
                params += ', scrollbars=yes';
                params += ', status=no';
                params += ', toolbar=no';
                window.open(data,'PopUp', params);
                $.unblockUI();
            }
        });
    }

    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#'+id).val("");
        }
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function validateYearLength(value){
        var plength = value.length;
        if(plength != 4){
            alert('"Tahun" yang dimasukkan salah.');
            $('#tahun').val("");
            $('#tahun').focus();
        }
    }
</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif"
     width="50" height="50" style="display: none" alt=""/>
<s:form beanclass="etanah.view.uam.AllPerananActionBean">
    <s:hidden name="reportName"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>${actionBean.report}</legend>
            <br>
            <c:set value="${actionBean.reportName}" var="reportname"/>
            <p>
                <c:if test="${!actionBean.melaka}"><label>Cawangan :</label></c:if>                   
                <c:if test="${actionBean.melaka}"><label>Jabatan :</label></c:if>
                <!--                <label>Cawangan :</label>-->
                <s:select name="kod_caw" style="width:145px;">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Peranan :</label>
                <%--<s:select name="kod_peranan" style="width:250px">--%>
                <s:select name="kod_peranan" style="width:370px">
                    <s:option value="" >Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodPeranan}" value="kod" label="nama"/>
                </s:select>
            </p>

            <br>
            <p align="center">
                <s:button name="viewReport" id="simpan" value="Papar" onclick="doSubmit(this.form);"  class="btn"/>&nbsp;
                <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
                <s:button name="" id="close" value="Tutup" onclick="self.close();" class="btn"/>&nbsp;
            </p>
        </fieldset>
    </div>
</s:form>
