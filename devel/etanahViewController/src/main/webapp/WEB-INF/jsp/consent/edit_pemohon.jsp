<%--
    Document   : edit_pemohon
    Created on : Nov 10, 2009, 4:23:38 PM
    Author     : muhammad.amin
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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
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

<s:form beanclass="etanah.view.stripes.consent.PihakTurunMilikActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <c:if test="${penerima}"> Maklumat Penerima</c:if>
                <c:if test="${!penerima}">Maklumat Pemohon</c:if>
            </legend>
            <p>
                <label>Nama :</label>
                ${actionBean.pihak.nama}&nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label>Jenis Pengenalan :</label>
                ${actionBean.pihak.jenisPengenalan.nama}&nbsp;
            </p>
            <p>
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>
            <c:if test="${!penerima}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label>Umur :</label>
                        <s:text name="pemohon.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Pekerjaan :</label>
                        <s:text name="pemohon.pekerjaan" size="40"/>
                    </p>
                    <p>
                        <label>Pendapatan Bulanan (RM) :</label>
                        <s:text name="pemohon.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Status Perkahwinan :</label>
                        <s:text name="pemohon.statusKahwin" maxlength="10" size="40"/>
                    </p>
                    <p>
                        <label>Tanggungan :</label>
                        <s:text name="pemohon.tangungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                </c:if>
                <p>
                    <label>Persaudaraan :</label>
                    <s:text name="pemohon.kaitan" size="40"/>
                </p>
            </c:if>
            <c:if test="${penerima}">
                <c:if test="${actionBean.pihak.jenisPengenalan.kod eq 'B' || actionBean.pihak.jenisPengenalan.kod eq 'L' || actionBean.pihak.jenisPengenalan.kod eq 'P' || actionBean.pihak.jenisPengenalan.kod eq 'T' || actionBean.pihak.jenisPengenalan.kod eq 'I'}">
                    <p>
                        <label>Umur :</label>
                        <s:text name="permohonanPihak.umur" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Pekerjaan :</label>
                        <s:text name="permohonanPihak.pekerjaan" size="40"/>
                    </p>
                    <p>
                        <label>Pendapatan Bulanan (RM) :</label>
                        <s:text name="permohonanPihak.pendapatan" size="40" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                    <p>
                        <label>Status Perkahwinan :</label>
                        <s:text name="permohonanPihak.statusKahwin" maxlength="10" size="40"/>
                    </p>
                    <p>
                        <label>Tanggungan :</label>
                        <s:text name="permohonanPihak.tangungan" size="10" maxlength="3" onkeyup="validateNumber(this,this.value);"/>
                    </p>
                </c:if>
                <p>
                    <label>Persaudaraan :</label>
                    <s:text name="permohonanPihak.kaitan" size="40"/>
                </p>
            </c:if>
            <p>
                <label>Alamat Surat-Menyurat :</label>
                <s:text name="pihak.suratAlamat1" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat2" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat3" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihak.suratAlamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="pihak.suratPoskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" >
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <c:if test="${!penerima}">
                    <label>&nbsp;</label>
                    <s:button name="simpanPihak" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
                <c:if test="${penerima}">
                    <label>&nbsp;</label>
                    <s:button name="simpanEditPenerima" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>