<%-- 
    Document   : edit_pemohon
    Created on : Apr 6, 2010, 6:25:32 PM
    Author     : nursyazwani
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

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.pbtActionBean">

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
                <label>Nombor Pengenalan :</label>
                ${actionBean.pihak.noPengenalan}&nbsp;
            </p>

            <p>
                <label>Alamat :</label>
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
                <s:text name="pihak.suratPoskod" size="40" maxlength="5"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="pihak.suratNegeri.kod" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
             <p>
                    <label>No.Telefon :</label>
                    <s:text name="pihak.noTelefon1" maxlength="11" id="notelefon"  /><font color="red">*</font>

                </p>
                <p>
                    <label>No.Fax :</label>
                    <s:text name="pihak.noTelefon2" maxlength="11" id="notelefon"  /><font color="red">*</font>

                </p>
                <p>
                    <label>Email :</label>
                    <s:text name="pihak.email" size="40" maxlength="100" />

                </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpanPihak" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>
