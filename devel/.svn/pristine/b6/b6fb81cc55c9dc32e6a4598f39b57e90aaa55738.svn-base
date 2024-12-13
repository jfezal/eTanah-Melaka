<%--
    Document   : penghantarNotis_popup
    Created on : Jan 3, 2011, 2:51:29 PM
    Author     : mdizzat.mashrom
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript">
    function simpanPihak(event, f){
    <%--        if(validation()){

        }
        else{--%>
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#notis',opener.document).html(data);
                    self.close();
                },'html');
    <%--}--%>
        }

        function validation() {
            if($("#nama").val() == ""){
                alert('Sila masukkan " Nama " terlebih dahulu.');
                $("#nama").focus();
                return true;
            }
            if($("#jenis").val() == ""){
                alert('Sila pilih " Jenis Kad Pengenalan " terlebih dahulu.');
                $("#jenis").focus();
                return true;
            }
            if($("#pengenalan").val() == ""){
                alert('Sila masukkan " No. Kad Pengenalan " terlebih dahulu.');
                $("#pengenalan").focus();
                return true;
            }
            return false;
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

    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.consent.MaklumatNotisActionBean">
    <div class="subtitle">
        <fieldset class="aras1" id="">
            <legend>
                Maklumat Penghantar Notis
            </legend>
            <p>
                <label><font color="red">*</font> Nama : </label>
                <s:text id="nama" name="penghantarNotis.nama" onchange="this.value=this.value.toUpperCase();" style="width:260px;"/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                <s:select id="jenis" name="penghantarNotis.kodJenisPengenalan.kod" style="width:150px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                <s:text id="pengenalan" name="penghantarNotis.noPengenalan" onkeyup="validateNumber(this,this.value);" style="width:150px;"/>&nbsp;
                <font color="red" size="1">(cth : 550201045678)</font>
            </p>
            <div class="content" align="center">
                <s:button name="simpanPenghantarNotis" id="save" value="Simpan" class="btn" onclick="simpanPihak(this.name, this.form);"/>
                <s:button name="" value="Isi Semula" class="btn" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </div>
        </fieldset>
    </div>
</s:form>
