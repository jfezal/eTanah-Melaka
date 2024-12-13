<%--
    Document   : popup_agensi
    Created on : Oct 12, 2012, 5:53:11 PM
    Author     : Siti Fariza Hanim
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });
    
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
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

    function validateForm(){
        if(document.form.namaAgensi.value=="")
        {
            alert('Sila masukkan Nama Agensi');
            $('#namaAgensi').focus();
            return false;
        }
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

    function save(event, f){
        alert('Anda pasti untuk simpan');
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');

    }
</script>
<%--<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>--%>
<s:form name="form" id="form" beanclass="etanah.view.penguatkuasaan.AgensiActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <fieldset class="aras1">
            <legend>
                Maklumat Agensi
            </legend>
            <div class="content">
                <p>
                    <label><em>*</em>Nama Agensi :</label>
                    <s:text name="namaAgensi" id="namaAgensi" onkeyup="this.value=this.value.toUpperCase();" size="40" maxlength="100" />
                </p>
                <p>
                    <label>Alamat Agensi :</label>
                    <s:text name="lokasiAgensi" id="lokasiAgensi" onkeyup="this.value=this.value.toUpperCase();" size="40" maxlength="100" />
                </p>
                <p>
                <label>Tarikh Remedi</label>
                <s:text name="tarikhKuatkuasa"  id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy" />&nbsp;
                <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>
                <p>
                    <label>Catatan :</label>
                    <s:textarea name="catatan" id="lokasiAgensi" onkeyup="this.value=this.value.toUpperCase();" cols="50" rows="10" class="normal_text" />
                </p>

                <p><label>&nbsp;</label>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <%--<s:button name="simpanPopup" class="btn" value="Simpan" onclick="doSubmit(this.form, this.name, 'page_div');"/>--%>
                    <%--<s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="simpanPopup" value="Simpan"/>--%>
                    <s:button class="btn"  name="simpanPopup" onclick="if (validateForm())save(this.name,this.form);" value="Simpan"/>
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
                <br>
            </div>
        </fieldset>
    </div>
</s:form>
