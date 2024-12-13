<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">
    function tutup(a,b,c){
        self.close();
    }
</script>

<s:form beanclass="etanah.view.strata.MaklumatEmel">
    <s:messages/>
    <s:errors/>
    <fieldset class="aras1">
        <legend>Maklumat Agensi</legend>
        <p>
            <label>Kod Agensi :</label><s:text name="kodAgensi.kod" onkeyup="this.value=this.value.toUpperCase();" size="10" maxlength="10" readonly="true"/>
        </p>
        <p>
            <label>Nama Agensi :</label><s:text name="kodAgensi.nama" onkeyup="this.value=this.value.toUpperCase();" size="30" maxlength="100"/>
        </p>
        <p>
            <label>Alamat Emel :</label><s:text name="kodAgensi.emel" class="normal_text" size="30" maxlength="80" />
        </p>
        <p>
            <label>Status :</label>
            <s:select name="kodAgensi.aktif" id="aktif" value = "${actionBean.kodAgensi.aktif}">
                <s:option value="">Pilih ...</s:option>
                <s:option value="Y">Aktif</s:option>
                <s:option value="T">Tidak Aktif</s:option>
            </s:select>
    </p>
    <s:submit name="savepopupKogAgensi" value="Simpan" class="btn" onclick="doSubmit(this.form,this.name,'page_div');" />
</fieldset>
</s:form>

