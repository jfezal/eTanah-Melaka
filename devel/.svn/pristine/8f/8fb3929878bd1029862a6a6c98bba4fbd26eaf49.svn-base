<%-- 
    Document   : tambahTarikhSenaraiHitamPembida
    Created on : 13 Mac 2013, 6:06:55 PM
    Author     : Mazurahayati
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<%@ page contentType="text/html" pageEncoding="windows-1252"%>--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<script> 
    var date = new Date();
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    
    function dateValidation(id, value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        //        if (sdate > today){
        //            alert("Tarikh yang dimasukkan tidak sesuai.");
        //            $('#'+id).val("");
        //        }
    }
    $(document).ready(function() {
        $("#simpan").click(function(evt) {
            var mohonId=$('#idPermohonan').val();                      
            var q = $("form").serialize();
            var url = '?' + $(this).attr("name");

            $.post(url, q,function(data) {
   
                self.opener.refreshingPagingFolder(mohonId);
                self.close();
            });
        });
    });

   

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

</script>



<s:form name="pemohon" id="pemohon"  beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="pihak.idPihak"/>

    <s:hidden id="idPermohonan" name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
    <s:hidden id="idPembida" name="idPembida" value="${actionBean.idPembida}"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tarikh Pembida Senarai Hitam  
            </legend>
            <div class="content">

                <p>
                    <label><font color="red">*</font> Nama :</label> 
                        <s:text id="nama" name="pihak.nama" onchange="this.value=this.value.toUpperCase();" readonly="true"/>
                </p>

                <p>
                    <label><font color="red">*</font>Tarikh Mula :</label>
                    <s:text id="datepicker" name="tarikhMula" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>
                <p>
                    <label><font color="red">*</font>Tarikh Tamat :</label>
                    <s:text id="datepicker1" name="tarikhTamat" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.id, this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                    &nbsp;
                </p>

            </div>
        </fieldset>
    </div>
    <div class="content" align="center">
        <p>
            <s:button name="simpanTarikhSenaraiHitam" id="simpan" value="Simpan" class="btn"/>
            <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close(${actionBean.idPembida})"/>
        </p>
    </div><br>

</s:form>


