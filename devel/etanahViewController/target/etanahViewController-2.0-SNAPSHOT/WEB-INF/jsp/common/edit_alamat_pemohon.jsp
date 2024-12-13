<%-- 
    Document   : edit_alamat_pemohon
    Created on : Dec 21, 2009, 7:07:28 PM
    Author     : khairil
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

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){

        $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });


            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                $.unblockUI();
                self.close();
            },'html');
        }

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


</script>
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<s:form beanclass="etanah.view.daftar.PihakKepentinganAction">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>    
            
            <c:if test="${!empty moreThanOneHakmilik}">
                <p>
                    <label>&nbsp;</label>
                    <font color="red" size="2">
                        <input type="checkbox" name="copyToAll" value="1"/>
                        <em>Sila klik jika sama untuk semua hakmilik.</em>
                    </font>
                </p>
            </c:if>

            <p>
                <label>Alamat Lama:</label>
                ${actionBean.hakmilikPihak.alamat1}&nbsp;<s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/><s:hidden name="idHakmilik"/>
                <s:hidden name="alamat1Lama" value="${actionBean.pihak.alamat1}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.hakmilikPihak.alamat2}&nbsp;<s:hidden name="alamat2Lama" value="${actionBean.pihak.alamat2}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.hakmilikPihak.alamat3}&nbsp;<s:hidden name="alamat3Lama" value="${actionBean.pihak.alamat3}"/>
            </p>
            <p>
                <label> Bandar :</label>
                ${actionBean.hakmilikPihak.alamat4}&nbsp;<s:hidden name="alamat4Lama" value="${actionBean.pihak.alamat4}"/>
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.hakmilikPihak.poskod}&nbsp;<s:hidden name="alamat5Lama" value="${actionBean.pihak.poskod}"/>
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.hakmilikPihak.negeri.nama}&nbsp;<s:hidden name="alamat6Lama" value="${actionBean.pihak.negeri.kod}"/>
            </p>

             <p>
                <label>Alamat Baru:</label>
                <s:text name="alamat1" size="40" onkeyup="this.value = this.value.toUpperCase()" value="${alamat1}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat2" size="40" onkeyup="this.value = this.value.toUpperCase()" value="${alamat2}"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat3" size="40" onkeyup="this.value = this.value.toUpperCase()" value="${alamat3}"/>
            </p>            
            <p>
                <label>Poskod :</label>
                <s:text name="alamat5" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);" value="${alamat5}"/>
            </p>
            <p>
                <label> Bandar :</label>
                <s:text name="alamat4" size="40" onkeyup="this.value = this.value.toUpperCase()" value="${alamat4}"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="alamat6" value="${alamat6}">
                    <s:option value="">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <s:hidden name="idPemohon" id="idPemohon" value="${actionBean.pemohon.idPemohon}"/>
                <label>&nbsp;</label>
                <s:button name="saveTukar" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>
