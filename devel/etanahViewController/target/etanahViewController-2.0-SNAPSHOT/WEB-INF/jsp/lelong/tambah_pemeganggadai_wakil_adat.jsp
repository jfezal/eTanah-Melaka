<%-- 
    Document   : tambah_pemeganggadai_wakil_adat
    Created on : May 12, 2011, 8:00:03 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function save(event, f){
        if(validation()){

        }
        else{
            var id = ${id};
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){

            },'html');
            $('#maklumatWakil' + id, opener.document).text($("#namaWakil").val());
            $('#remove' + id, opener.document).show();

            self.close();
        }
    }
    
    function validation() {
        if($("#namaWakil").val() == ""){
            alert('Sila masukkan " Nama "');
            $("#namaWakil").focus();
            return true;
        }
        if($("#jenisPengenalan").val() == "null"){
            alert('Sila pilih " Jenis Pengenalan "');
            $("#jenisPengenalan").focus();
            return true;
        }
        if($("#nokp1").val() == "" && $("#nokp2").val() == "" ){
            alert('Sila masukkan " No.Kad Pengenalan/No.Pengenalan "');
            $("#noPengenalan").focus();
            return true;
        }

        return false;
    }


    $(document).ready(function() {
        $("#nokp2").hide();
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

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

    function changeNOKP( val ){
        var no = val;
        if(no == 'B'){
            $("#nokp2").hide();
            $("#nokp1").show();
        }else{
            $("#nokp2").show();
            $("#nokp1").hide();
        }
    }


</script>


<s:form beanclass="etanah.view.stripes.lelong.MaklumatKehadiranTanahAdatActionBean" id="maklumat_kehadiran">
    <s:hidden name="idMP" value="${actionBean.idMP}"/>
    <br>
    <fieldset class="aras1">
        <legend>
            Maklumat Wakil
        </legend>
        <div class="subtitle displaytag" >

            <p>
                <label><em>*</em>Nama :</label>
                <s:text id="namaWakil" name="kkk.wakilNama" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><em>*</em>Jenis Pengenalan :</label>
                <s:select id="jenisPengenalan" name="jenis" style="width:139px;" onchange="changeNOKP(this.value);">
                    <s:option value="null">-Sila Pilih-</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label><em>*</em>No.Pengenalan : </label>
                <s:text id="nokp1" name="kkk.wakilNoPengenalan" onkeyup="validateNumber(this,this.value);" style="width:139px;"/>
                <s:text id="nokp2" name="kkk.wakilNoPengenalan" onblur="this.value=this.value.toUpperCase();" style="width:139px;"/>
                <font color="red" size="1">(cth : 550201045678)</font>
            </p>
            <p>
                <label>Wakil Kepada :</label>
                <c:if test="${actionBean.kkk.idPenyerah eq null}">
                    ${actionBean.kkk.hakmilikPihakBerkepentingan.pihak.nama}
                </c:if>
                <c:if test="${actionBean.kkk.idPenyerah ne null}">
                    ${actionBean.peguam.nama}
                </c:if>
                <c:if test="${actionBean.kkk.senaraiRujukan.kod ne null}">
                    ${actionBean.kkk.senaraiRujukan.nama}
                </c:if>
            </p>


            <p align="center"><label></label>
                <br>
                <s:button name="simpanWakil" value="Simpan" class="btn" onclick="save(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </div><br>

    </fieldset>

</s:form>
