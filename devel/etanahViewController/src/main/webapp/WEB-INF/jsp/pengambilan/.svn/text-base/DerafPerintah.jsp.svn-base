<%-- 
    Document   : DerafPerintah
    Created on : 26-Oct-2010, 15:17:27
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>


<s:form beanclass="etanah.view.stripes.pengambilan.notifikasiActionBean">
   <s:messages/>
<s:errors/>
<div>
    <fieldset class="aras1">
        <legend align="center">
                <b>Deraf Perintah</b>
            </legend>
    </fieldset>
</div>
            <div class="subtitle">
                   <c:if test="${edit}">

        <p>
            <label for="Maklumat Pengambilan">Nama Penolong Kanan Pendaftar Mahkamah Tinggi :</label>
            <s:text name="Nama" size="30" id="norujukan" />
        </p>

            <p>
                <label>&nbsp;</label>
                <s:button name="savePengambilanInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"  />
               <%-- onclick="save1(this.name, this.form);"--%>
            </p>

      </c:if>
      <c:if test="${!edit}">
         <%-- <p>
            <label for="Maklumat Pengambilan">Jenis Permohonan :</label>Segera&nbsp;
          </p>--%>
          <p>
            <label for="Maklumat Pengambilan">Nama Penolong Kanan Pendaftar Mahkamah Tinggi :</label>TIADA DATA<%--${actionBean.permohonanRujukanLuar.noRujukan}--%>&nbsp;
          </p>
      </c:if>
            </div>
</s:form>

