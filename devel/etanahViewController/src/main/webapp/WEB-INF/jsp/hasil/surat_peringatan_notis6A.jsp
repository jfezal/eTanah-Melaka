<%-- 
    Document   : surat_peringatan_notis6A
    Created on : Dec 15, 2009, 11:09:53 PM
    Author     : nurfaizati
--%>



<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function blankValidation(){
        var result = true;
        if(($('#idDasar').val() == null || $('#idDasar').val() == "") && ($('#tarikhDasar').val() == null || $('#tarikhDasar').val() == "")){
            alert("Sila isi Nombor Rujukan Dasar dan Tarikh Rujukan Dasar terlebih dahulu.");
            $('#idDasar').focus();
            result = false;
        }else if($('#idDasar').val() == null || $('#idDasar').val() == ""){
            alert("Sila isi Nombor Rujukan Dasar terlebih dahulu.");
            $('#idDasar').focus();
            result = false;
        }else if($('#tarikhDasar').val() == null || $('#tarikhDasar').val() == ""){
            alert("Sila Tarikh Rujukan Dasar terlebih dahulu.");
            $('#tarikhDasar').focus();
            result = false;
        }
        return result;
    }

    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?saveDasar&idDasar="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function dateValidation(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#tarikhDasar').val("");
        }
    }
</script>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.hasil.SuratPeringatanNotis6AActionBean" id="notis">
        <s:errors />
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Kemasukan Dasar Baru
                </legend>
                <p class=instr>
                    <font color="red">PERINGATAN:</font> Sila Masukkan Maklumat Berikut Dan Tekan Butang Simpan Untuk Wujudkan Dasar Yang Baru.</font></em>
                </p>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <p>
                    <label for="No Resit"><font color="red"><em>*</em></font>Nombor Rujukan Dasar :</label>
                    <s:text id="idDasar" name="dasarTuntutanCukai.idDasar" maxlength="30" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label for="No Resit"><font color="red"><em>*</em></font>Tarikh Rujukan Dasar :</label>
                    <s:text id="tarikhDasar" name="tarikhDasar" size="20" maxlength="10" class="datepicker" onchange="dateValidation(this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                </p>
                <p align="right">
                    <s:submit name="saveDasar" value="Simpan" class="btn" onclick="return blankValidation();" />&nbsp;
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('notis');"/>
                </p>
                <%--<table border="0" width="100%">
                    <tr>
                        <td align="right">
                            <s:submit   name="saveDasar" value="Simpan" class="btn" />
                            <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('notis');"/>
                        </td> </tr>
                </table>--%>
            </fieldset>
        </div>
    </s:form>
</div>