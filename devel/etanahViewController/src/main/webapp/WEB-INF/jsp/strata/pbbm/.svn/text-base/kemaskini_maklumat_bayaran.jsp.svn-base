<%--
    Document   : testing1
    Created on : Dec 16, 2009, 10:42:01 AM
    Author     : wan.fairul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">

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

   
    function save(event, f){
<%--        var item = document.form1.item.value;
        var amaunTuntutan = document.form1.amaunTuntutan.value;


        if ((item == ""))
        {
            alert('Sila masukkan Jenis Bayaran ');
            document.form1.item.focus();
        }
        else if ((amaunTuntutan == ""))
        {
            alert('Sila masukkan Jumlah Bayaran ');
            document.form1.amaunTuntutan.focus();
        }

        else
        {

--%>
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');

        }
    <%--}--%>
</script>

<div class="subtitle">
<s:form  name="form1" beanclass="etanah.view.strata.BayaranUpahUkurActionBean">
        <s:messages/>
        <s:errors/>
         <s:hidden name="idKos" value="${actionBean.idKos}"/>
        <fieldset class="aras1">

            <legend>Kemaskini Maklumat Bayaran</legend>
            <br>
            <p>
                <label>Jenis Bayaran : </label><s:text name="item" class="normal_text" value="${actionBean.item}" /><em>*</em>
            </p>
            <p>
                <label>Jumlah Bayaran (RM): </label><s:text name="amaunTuntutan" value="${actionBean.amaunTuntutan}" onkeyup="validateNumber(this,this.value);"/><em>*</em>
            </p>
            <p>
                <br>
                <label>&nbsp;</label>

                <s:button name="UpdateMaklumatBayaran" id="simpan" value="Kemaskini" class="btn" onclick="save(this.name, this.form);"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

            <br>

        </fieldset>
    </div>

    <br>
</s:form>
