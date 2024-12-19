<%-- 
    Document   : edit_alamat_pemohon
    Created on : Apr 6, 2010, 6:21:57 PM
    Author     : nursyazwani
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
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

<s:form beanclass="etanah.view.stripes.pembangunan.PihakBerkepentinganActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pemohon</legend>

            <p>
                <label>Alamat Lama:</label>
                ${actionBean.pihak.alamat1}&nbsp;<s:hidden name="pihak.idPihak"/>
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat2}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat3}&nbsp;
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.pihak.alamat4}&nbsp;
            </p>
            <p>
                <label>Poskod :</label>
                ${actionBean.pihak.poskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.pihak.negeri.nama}&nbsp;
            </p>

             <p>
                <label>Alamat Baru:</label>
                <s:text name="alamat1" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat2" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat3" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="alamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="alamat5" size="40" maxlength="5"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="alamat6" >
                    <s:option>Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="tukar" id="simpan" value="Simpan" class="btn" onclick="save(this.name, this.form);"/>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>

        </fieldset >
    </div>

</s:form>

